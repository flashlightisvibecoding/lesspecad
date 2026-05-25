package com.example.data

import android.content.Context
import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AdBlocker {
    private const val TAG = "AdBlocker"

    // Thread-safe atomic state flag to prevent duplicate rule compilation passes
    @Volatile
    private var isInitialized = false

    // Curated high-performance fallback database to guarantee immediate, pre-compiled cold launch defenses
    private val staticHosts = hashSetOf(
        // Google Ad & Analytics Networks
        "doubleclick.net",
        "ad.doubleclick.net",
        "googleads.g.doubleclick.net",
        "googlesyndication.com",
        "pagead2.googlesyndication.com",
        "tpl.googlesyndication.com",
        "adservice.google.com",
        "adservice.google.com.tr",
        "analytics.google.com",
        "google-analytics.com",
        "www.google-analytics.com",
        "googletagmanager.com",
        "www.googletagmanager.com",
        "googletagservices.com",

        // Major Ad Exchange and Tracking Platforms
        "adnxs.com",        // AppNexus
        "adnow.com",        // AdNow
        "adroll.com",       // AdRoll
        "adform.net",       // AdForm
        "adsrvr.org",       // The Trade Desk
        "adtech.de",        // AdTech
        "adtechadvertising.com",
        "taboola.com",      // Taboola
        "outbrain.com",     // Outbrain
        "outbrainimg.com",
        "popads.net",       // PopAds
        "popunder.net",     // PopUnder
        "propellerads.com", // PropellerAds
        "exoclick.com",     // ExoClick
        "mgid.com",         // MGID
        "addthis.com",      // AddThis
        "quantserve.com",   // Quantcast
        "scorecardresearch.com", // Comscore
        "hotjar.com",       // Hotjar
        "rubiconproject.com", // Rubicon Project
        "pubmatic.com",     // PubMatic
        "criteo.com",       // Criteo
        "static.criteo.net",
        "openx.net",        // OpenX
        "openx.com",
        "casalemedia.com",  // Index Exchange
        "indexww.com",
        "smartadserver.com", // Smart AdServer
        "yieldmo.com",      // Yieldmo
        "sovrn.com",        // Sovrn
        "optimizely.com",   // Optimizely
        "amplitude.com",    // Amplitude
        "mixpanel.com",     // Mixpanel
        "segment.io",       // Segment
        "segment.com",
        "facebook.net",     // Facebook Pixel
        "connect.facebook.net",
        "coinhive.com",     // Cryptocurrency miner
        "adcolony.com",     // Mobile Advertising
        "applovin.com",     // AppLovin
        "unity3d.com",      // Unity Ads
        "chartboost.com",   // Chartboost
        "flurry.com",       // Flurry
        "crazyegg.com",     // Crazy Egg
        "clicky.com",       // Clicky Web Analytics
        "yandex.ru/clck",   // Yandex Click element tracker
        "pixel.wp.com",     // Wordpress Pixel
        "adnxs-simple.com",
        "adkernel.com",
        "adthrive.com"
    )

    // Stream-safe active host set loaded in parallel from external local assets (O(1) lookups)
    private val dynamicHosts = ConcurrentHashMap.newKeySet<String>()

    // Core keyword filters for inline asset paths
    private val blockedKeywords = arrayOf(
        "/ads/",
        "/advert/",
        "advertising",
        "popunder",
        "popbox",
        "/ad-delivery/",
        "sponsored",
        "promotions",
        "telemetry",
        "crashlytics",
        "/adserver",
        "/adsystem",
        "/pixel",
        "ads.js",
        "prebid.js",
        "track.js",
        "analytics.js",
        "click-track"
    )

    /**
     * Reads, cleans, and registers the complete database of filter rules dynamically in a non-blocking way.
     */
    suspend fun loadCustomHosts(context: Context) {
        if (isInitialized) return
        withContext(Dispatchers.IO) {
            try {
                context.assets.open("adblock_hosts.txt").use { inputStream ->
                    BufferedReader(InputStreamReader(inputStream)).use { reader ->
                        var line = reader.readLine()
                        while (line != null) {
                            val trimmed = line.trim()
                            if (trimmed.isNotEmpty() && !trimmed.startsWith("#") && !trimmed.startsWith("//")) {
                                dynamicHosts.add(trimmed.lowercase())
                            }
                            line = reader.readLine()
                        }
                    }
                }
                isInitialized = true
                Log.d(TAG, "Successfully loaded expanded list from assets. Total hosts registered: ${dynamicHosts.size}")
            } catch (e: Exception) {
                Log.e(TAG, "Error loading adblock rules from assets, falling back to static rules", e)
            }
        }
    }

    /**
     * Extracts and validates the hostname from a given URL string.
     */
    private fun getHost(url: String): String? {
        return try {
            val uri = Uri.parse(url)
            uri.host?.lowercase()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Highly optimized ad checking mechanism with thread-safe, multi-stage lookup pipelines.
     */
    fun isAdUrl(url: String): Boolean {
        if (url.isEmpty() || url == "about:blank") return false
        val lowerUrl = url.lowercase()

        val host = getHost(lowerUrl)
        if (host != null) {
            // 1. Double check exact block in our dynamic memory database or fallback static mapping
            if (dynamicHosts.contains(host) || staticHosts.contains(host)) {
                Log.d(TAG, "[Blocked] Exact Hostname Match: $host")
                return true
            }

            // 2. Trailing/parent subdomain matching checking tree
            var tempHost: String = host
            while (tempHost.contains(".")) {
                if (dynamicHosts.contains(tempHost) || staticHosts.contains(tempHost)) {
                    Log.d(TAG, "[Blocked] Subdomain Match: Key ($tempHost) matching original host ($host)")
                    return true
                }
                val dotIndex = tempHost.indexOf('.')
                if (dotIndex != -1 && dotIndex < tempHost.length - 1) {
                    tempHost = tempHost.substring(dotIndex + 1)
                } else {
                    break
                }
            }
        }

        // 3. Fallback path, frames, script and query scanning
        for (keyword in blockedKeywords) {
            if (lowerUrl.contains(keyword)) {
                Log.d(TAG, "[Blocked] Keyword Match: '$keyword' in URL domain/path")
                return true
            }
        }

        return false
    }
}
