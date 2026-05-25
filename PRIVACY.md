# Privacy Policy — Lesspecad Browser

_Last updated: 2026-05-25_

Lesspecad is built around a simple idea: **a browser shouldn't know more about you than it needs to**. This document explains, in plain language, exactly what the app does and does not do with your data.

If anything here is unclear or you spot a discrepancy between this document and the code, please open an issue.

---

## TL;DR

- ❌ No analytics, no telemetry, no crash reporting sent off-device.
- ❌ No accounts. No sign-up. No cloud sync.
- ❌ No advertising SDKs.
- ✅ All your data (bookmarks, history, tabs, settings) stays in a local SQLite database on your device.
- ✅ The only network connections Lesspecad makes are the ones **you** initiate by opening a website or running a search.
- ✅ Backup and "sync" happen via a manually-exported JSON file you control — never through our servers (we don't have any).

---

## 1. What data Lesspecad stores

All of the following is stored **locally on your device only**, in an app-private SQLite database (Room):

| Data | Purpose |
|---|---|
| Bookmarks | So you can save and revisit pages |
| Browsing history | So you can find pages you visited |
| Open tabs and tab groups | So tabs survive app restarts |
| Download history | So you can see and re-open past downloads |
| User preferences (theme, language, default search engine, etc.) | So the app remembers your settings |
| Extension / userscript configuration | So your enabled scripts persist |

You can delete any of this at any time from the in-app settings, or by clearing the app's storage from Android system settings.

**Incognito mode**: while incognito, none of the above is written to disk. Closing the incognito session discards it.

---

## 2. What data Lesspecad does **not** collect

- ❌ No analytics or usage statistics
- ❌ No crash reports sent to remote servers
- ❌ No device identifiers (advertising ID, IMEI, MAC, etc.)
- ❌ No location data
- ❌ No contacts, calendar, microphone, or camera access
- ❌ No account or login system

We don't operate any backend servers that collect data from the app. There is nowhere for us to send your data to, even if we wanted to.

---

## 3. Permissions requested

Lesspecad currently requests only the following Android permissions:

| Permission | Why |
|---|---|
| `INTERNET` | Required to load web pages — this is what a browser does |
| `ACCESS_NETWORK_STATE` | To detect whether you are online or offline and adjust UI accordingly |

That's it. No storage permission is requested; downloads use the system Storage Access Framework, which lets _you_ pick where files go.

### Cleartext traffic (HTTP)

The manifest enables `usesCleartextTraffic="true"`. This allows the browser to load plain HTTP sites (in addition to HTTPS), because some legacy sites still require it. Whether you visit an HTTPS site or an HTTP site is your choice — Lesspecad does not downgrade secure connections.

---

## 4. Network connections Lesspecad makes

The **only** network requests the app makes are those caused by your direct actions:

1. **Loading a webpage** — when you tap a link or enter a URL, the WebView fetches that page. Lesspecad does not add any extra requests to your browsing.
2. **Search queries** — when you type into the address bar, the query is sent to your selected search engine.

### Search engines

The default search engine is **DuckDuckGo**. You can change it from settings to one of:

- DuckDuckGo (default)
- Google
- Bing
- Ecosia

Once you pick a search engine, Lesspecad sends your queries directly to that provider over HTTPS. Each of these providers has its own privacy policy — Lesspecad has no insight into and no control over what they do with your queries. If privacy matters to you, DuckDuckGo or Ecosia are generally the friendlier choices.

---

## 5. Ad and tracker blocking

Lesspecad ships an "Block Ads" toggle in onboarding and settings. The ad-blocking engine is **under active development**; the toggle currently controls in-app behavior but the full filter-list pipeline (including which lists are used and how they are updated) is not finalized yet.

When the implementation is finalized, this document will be updated to describe:

- Which filter lists are bundled and which are fetched
- The exact URLs the app contacts to update lists
- The update frequency

Until that work lands, please treat the ad-blocker as an in-progress feature.

---

## 6. Backup and "sync"

Lesspecad's backup feature is **cloudless**:

- You can export your data (bookmarks, tabs, groups, settings, extension configs) into a single JSON file from settings.
- You can import that JSON file on another device.
- The file lives wherever you put it — your local storage, your own cloud drive, a USB stick, anywhere you choose.
- Lesspecad never uploads this file anywhere on its own.

---

## 7. Extensions and user scripts

Lesspecad supports lightweight in-app extensions and user-injected JavaScript. Anything an extension does runs with the same privileges as the page it is injected into. If you install a third-party script, you are trusting that script — review the source before enabling.

---

## 8. Children

Lesspecad is a general-purpose browser and does not knowingly collect personal information from anyone, including children. Parents should supervise browser use, as the web itself contains content that is not appropriate for all ages.

---

## 9. Source code and verification

Lesspecad is open source under the **Mozilla Public License 2.0**. You can read the entire source code at:

<https://github.com/flisvibing/lesspecad>

If anything in this document doesn't match what the code does, the code is the source of truth — please file an issue.

---

## 10. Changes to this policy

If this policy changes, the "Last updated" date at the top will change and the diff will be visible in the repository's git history. Material changes (e.g. new permissions, new network destinations) will be called out in release notes.

---

## 11. Contact

For privacy-related questions or to report a concern, open an issue on the GitHub repository:

<https://github.com/flisvibing/lesspecad/issues>
