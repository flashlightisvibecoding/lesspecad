package com.example

import com.example.data.AdBlocker
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class AdBlockerTest {

    @Test
    fun testExactHostBlocking() {
        // DoubleClick should be blocked
        assertTrue(AdBlocker.isAdUrl("https://doubleclick.net"))
        assertTrue(AdBlocker.isAdUrl("http://doubleclick.net/some-path"))
        
        // Taboola should be blocked
        assertTrue(AdBlocker.isAdUrl("https://taboola.com"))
    }

    @Test
    fun testSubdomainMatching() {
        // Any subdomains under DoubleClick should also be blocked
        assertTrue(AdBlocker.isAdUrl("https://ads.doubleclick.net"))
        assertTrue(AdBlocker.isAdUrl("https://nested.subdomain.doubleclick.net/pixel.gif"))
        
        // Any subdomains under GoogleSyndication
        assertTrue(AdBlocker.isAdUrl("https://pagead2.googlesyndication.com/script.js"))
    }

    @Test
    fun testKeywordPathMatching() {
        // Typical advertising keyword fragments in path
        assertTrue(AdBlocker.isAdUrl("https://some-clean-domain.com/ads/banner.jpg"))
        assertTrue(AdBlocker.isAdUrl("https://some-clean-domain.com/assets/advertising/injector.js"))
        assertTrue(AdBlocker.isAdUrl("https://some-clean-domain.com/js/prebid.js"))
    }

    @Test
    fun testPermittedUrls() {
        // Wikipedia should NOT be blocked
        assertFalse(AdBlocker.isAdUrl("https://en.wikipedia.org/wiki/Main_Page"))
        
        // DuckDuckGo searches should NOT be blocked
        assertFalse(AdBlocker.isAdUrl("https://duckduckgo.com/?q=android+development"))
        
        // Clean paths with keywords but not in a suspicious layout
        assertFalse(AdBlocker.isAdUrl("https://github.com/flisvibing/lesspecad"))
        
        // Blank pages
        assertFalse(AdBlocker.isAdUrl("about:blank"))
        assertFalse(AdBlocker.isAdUrl(""))
    }
}
