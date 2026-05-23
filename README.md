# 🌟 Lesspecad Browser

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android Platform" />
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin Language" />
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose" />
  <img src="https://img.shields.io/badge/Database-Room%20DB-FFA000?style=for-the-badge&logo=sqlite&logoColor=white" alt="Room DB" />
</p>

**Lesspecad**, Android cihazlar için geliştirilmiş; sadeliği, yüksek gizliliği, reklam engellelemeyi ve genişletilebilirliği tek bir çatı altında toplayan **yeni nesil, ultra-hafif ve modern** bir web tarayıcı uygulamasıdır. 

Material Design 3 felsefesine sadık kalınarak tamamen **Jetpack Compose** ile yazılmıştır.

---

## ✨ Öne Çıkan Özellikler

### 🛡️ Gelişmiş Gizlilik ve Dahili Reklam Engelleyici
- **AdBlocker:** Harici bir araca veya eklentiye ihtiyaç duymadan reklamları ve izleyicileri engeller.
- **İz Bırakmayan Gizli Mod:** Çerez yönetimi ve web geçmişi kaydı olmadan tamamen gizlilik odaklı sörf deneyimi.
- **Güvenli Çerez Yönetimi:** Tek oturumluk ve özelleştirilebilir çerez kontrolleri.

### 🧩 Eklenti ve Kullanıcı Script Desteği
- Tarayıcıyı kendi isteklerinize göre uyarlamanızı sağlayan dahili **Eklenti Yönetim Sistemi**.
- Web sayfalarına özel Javascript enjekte edebilen yapıyla dinamik özellikler kazandırabilme.

### 📚 Gelişmiş Sekme ve Grup Yönetimi (Tab Groups)
- **Görsel Sekme Yöneticisi:** Açık olan tüm sayfalarınızı şık kart tasarımlarıyla izleyin.
- **Sekme Gruplama (Tab Groups):** Sekmelerinizi kategorilere ayırın, özel renkler ve isimler tanımlayarak çalışma düzeninizi koruyun.

### 📖 Özel Okuma Modu (Reader Mode)
- Makale, blog yazısı ve haberleri okurken sayfadaki tüm reklam, görsel karmaşası ve yan menüleri kaldırarak sadece içeriğe odaklanmanızı sağlayan temiz arayüz.

### 🎨 Renk Paletleri ve Özelleştirilebilir Temalar
- **Material 3 Dynamic System:** Cihazınızın sistem renk akortlarıyla uyumlu dinamik renkler.
- **Arayüz Renk Tonları:** Tarayıcı genelinde geçerli olan modern ve göz yormayan özel vurgu (Accent) renkleri seçeneği.

### 💾 Akıllı Yedekleme & Geri Yükleme
- Yer imlerinizi, geçmişinizi ve etkin eklentilerinizi tek tıkla **JSON formatında dışa aktarın (Export)** veya önceki yedeklerinizi kolayca **içeri aktarın (Import)**.

---

## 🛠️ Teknik Altyapı ve Mimari

Uygulamanın mimarisi, Android ekosisteminin en güncel ve kararlı kütüphaneleriyle **MVVM (Model-View-ViewModel)** prensibine uygun olarak inşa edilmiştir:

- **Jetpack Compose:** Bildirimsel (Declarative) UI ile kusursuz geçiş animasyonları ve responsive arayüz.
- **Android View Integration (`AndroidView`):** Android WebView'ın, Jetpack Compose yaşam döngüsüyle tam senkronize ve bellek sızıntısı yapmayacak şekilde (`webViewPool` mimarisiyle) yönetilmesi.
- **Room Database:** Yer imleri, arama geçmişi, aktif sekmeler, sekme grupları ve indirilenler veritabanının yerel olarak güvenli saklanması.
- **Flow & Coroutines:** Asenkron veri akışlarının (StateFlow, SharedFlow) ui-katmanıyla reaktif entegrasyonu.
- **Edge-to-Edge:** Navigasyon ve durum çubuklarıyla tamamen bütünleşik, modern çerçevesiz tasarım.

---

## 🚀 Proje Kurulumu ve Derleme

Projeyi yerel makinenizde çalıştırmak ve derlemek oldukça basittir.

### Gereksinimler
- **JDK 17** veya üzeri
- **Android Studio Jellyfish** (veya daha yeni bir sürüm)
- **Gradle 8.0+**

### CLI ile Derleme adımları

1. **Depoyu klonlayın:**
   ```bash
   git clone https://github.com/lesspecad/browser.git
   cd browser
   ```

2. **Geliştirici (Debug) APK Dosyasını Derleyin:**
   ```bash
   gradle assembleDebug
   ```

3. **Birim (Unit) Testlerini Çalıştırın:**
   ```bash
   gradle test
   ```

---

## 📦 Paket Bilgisi

Uygulama benzersiz ve profesyonel paket kimliğiyle derlenmektedir:
- **Application ID / Paket Adı:** `com.lesspecad.browser`

---

## 📝 Lisans

Bu proje **ISC Lisansı** altında lisanslanmıştır. Daha fazla bilgi edinmek için lisans şartlarını inceleyebilirsiniz.

---

<p align="center">
  <i>Lesspecad ile dijital dünyayı özgürce ve güvenle keşfedin.</i>
</p>
