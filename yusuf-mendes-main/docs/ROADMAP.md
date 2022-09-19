# Giriş

Merhaba geliştirici, bu döküman senin en kısa sürede en iyi biçimde Android framework’ünü öğrenebilmen için oluşturuldu.

Kısaca bahsetmek gerekirse döküman, öğrenmen gereken konu başlıkları, bu konu başlıklarını öğrenebileceğin linkler ve tamamlaman gereken görevlerden oluşuyor. Bu görevlere ek olarak belli konu başlığı sayısından sonra konu başlıklarını birleştirdiğimiz daha karmaşık görevler seni bekliyor olacak.

Bu dokümanın amacı konu başlıkları üzerine durmak değil, sadece öğrenme sürecinde bir yol gösterici rolü üstlenmek. Her konu başlığında o konu detaylı bir şekilde araştırılmalı ve mutlaka bir uygulama ortaya konulmalı.

Uygulama aşamasında şu şekilde bir yol izlenebilir. Aslında tek bir temel uygulamamız olur ve bütün denemeler ve örnekler bu uygulama üzerinde yapılır. Bu uygulama bir anasayfa ve bu anasayfa üzerinde her bir örneğe giden butonları içerir. Her örnek eklendiğinde bir buton eklenmelidir ve o örnek için yeni bir sayfa oluşturulmalıdır. Bu sayede proje içeriği büyüdükçe daha temiz ve anlaşılır kod nasıl yazılır bunun için bir fikir oluşabilir.

# Öğrenme Metodolojisi Üzerine

Bu bölümde kısaca öğrenmenin mantığı üzerinde duracağım. Öğrenme söz konusu dökümanın en önemli kısmı aslında. Burada “Nasıl daha iyi ve kalıcı öğrenme sağlanır?” , “Aradığımız cevapları nasıl daha iyi bulabiliriz?” , bu sorular üzerinde duracağım.

Bir konuyu öğrenmenin en iyi yolu ilk olarak öğrenmeyi aşamalara ayırmak ile başlamalı. Burda aşamaları şu şekilde sıralayabiliriz;

1. Spesifik bir konu belirlemek.
2. Konu hakkında teorik araştırma yapmak ve notlar almak.
3. Konunun olabildiğince ayrıntılı bir şekilde uygulamalarını ve örneklerini bulmak.
4. İlk aşamada bu uygulama ve örnekleri gerçekleştirmek. 
5. Sonrasında, belirlediğiniz konu üzerinde kendiniz neler katabilirsiniz ya da farklı olarak kendiniz neler yapabilirsiniz bunu düşünmek. Örneğin; textview üzerine çalıştığınızı varsayarsak, araştırmalarınız sonucunda bir text’in rengini , boyutunu, fontunu değiştirmek ile ilgili örnekler buldunuz diyelim. Sonraki aşamada kendiniz örneğin , üstü çizgili bir yazı nasıl yazdırabilirim sorusunu sorabilirsiniz kendinize. Bu tabiki tamamen benim kendi örneğim, buradaki farklı katkılar ve düşünüş biçimi tamamen sizin yaratıcılığınıza ve kararınıza kalmış bir durum.
6. Son olarak spesifik olarak ilgilendiğiniz bir kaç konuyu birleştirerek ortaya daha komplex bir yapı koyarak öğrendiklerinizi pekiştirmek.

Burada her ne kadar en iyi olarak bahsettiğim bu metot salt doğru olarak kabul edilmemeli. Sonuç itibari ile öğrenme metodolojileri kişinin kendi insiyatifinde olan metotlardır ve herkes kendi metodunu geliştirebilir.

# Efektif Bir Arama
Yaptığımız işin doğası gereği bir teknolojiye hakim olma yetisi çok uzun süre sonra elde edilen birşey. Bu sebeptendir ki çoğu zaman hatta zaten bildiğimiz fakat teferruatını unuttuğumuz konular için bile Google araması yaparak çalışacağımız konu üzerine araştırma yaparız. Bu başlıkta efektif bir Google araması üzerine kısaca değineceğim.

İlk aşamada tavsiyem aramalarınızı İngilizce yapmanız. Belki temel yapılar için bir takım kaynaklar elde edebilirsiniz fakat ilerleyen zamanlarda yaşadığınız bazı spesifik hata durumlarında ingilizceyi kullanmaya mecbur kalacaksınız. Bu konuda eğer İngilizceniz yeterli değil ise Google Translate’den yardım alabilirsiniz. Basit bir tavsiye gibi görünebilir fakat Google geçtiğimiz yıl algoritmasını baya geliştirdi, eğer cümleniz Türkçe kurallarına uygun ise Translate mükemmel çeviri yapabiliyor. Buradaki püf nokta ingilizceniz kötü olabilir fakat Türkçeniz kötüyse yandınız :)

“How to” ile soru kalıbı nasıl yazılır bunu öğrenmelisiniz. Ve bu kalıba ek olarak diyelim ki bir konunun uygulamalı örneğini arıyorsunuz şu şekilde oluşturacağınız bir kalıp işinizi görecektir “android - spesifik konu - tutorial/example”. Örnek vermek gerekirse;

* android Textview tutorial
* android checkbox example, gibi

Bazen ilerleyen zamanlarda yeterince uygulamalı örnek bulamayacaksınız ve tek çareniz o konunun dökümantasyonunu incelemek olacak. Bunun içinse aramanızın sonuna “documentation” kelimesini eklemeniz gerekecek. Örneğin;

> Android exoplayer documentation

Dökümantasyonlar genellikle bir teknolojide yapabileceğiniz veya özelleştirebileceğiniz bütün konulara değinir. Biraz ayrıntılıdır fakat aradığınız çoğu cevabı bulabilirsiniz. Fakat eğer yeni başladıysanız dökümantasyonlar biraz korkutucu ve sıkıcı gelebilir. İlk aşamalarda buna pek de ihtiyacımız olmayacak.

Son olarak, araştırdığımız konu ile ilgili arama yaparken arama cümlesinin uzamamasına dikkat etmelisiniz. Yalnızca anahtar kelimeleri kullanmanız yeterli olacaktır. Şöyle bir senaryo düşünelim, Android liste yapısında bulunan her bir item içinde birer  buton yapılması istendi diyelim fakat bu butonlara tıklandığında nasıl aksiyon vereceğimizi bilmiyoruz. Bu durumda şu şekilde bir arama yapmamız yeterli; “button click in recyclerview”. Buradaki recyclerview Android’de kullandığımız liste yapısıdır bu konuya geldiğimde bahsedeceğim.

Bunun yanında ek olarak Google araması yaparken kullanabileceğiniz bazı püf noktalar var. Şimdi ilk görevinizi veriyorum bir google araması yapacaksınız ve bu püf noktalar nedir bunları bulacaksınız. Küçük bir ipucu; püf noktanın ingilizcesi “trick”.

## Programming Languages
Android geliştirirken Java ve Kotlin dillerini kullanıyoruz. Kotlin 2017 yılında Google tarafından Android'in resmi programlama dili olarak kabul edildi, bu sebeple bu iki dile de hakim olmak bizler için önemli. Paylaştığımız makale ve sayfaları takip ederek bu dillerin yazım stillerine aşina olabilirsiniz.

### Kaynaklar
* https://play.kotlinlang.org/koans/overview
* https://blog.teamtreehouse.com/absolute-beginners-guide-kotlin

## Data Structure & Algorithms
Veri yapıları ve algoritmalar olarak çevirebiliriz bu başlığı. Bu bölümdeki amaç sizlere veri yapıları ve algoritmalar üzerine detaylı bilgi vermek değil ne kadar bildiğinizi ölçmek ve bu konuda olmazsa olmaz bilmeniz gerekenler yönünde sizleri yönlendirmek. 

Aslında Android uygulama geliştirme için gereken java ve kotlin’deki data tipleri ve hangi durumda en optimize şekilde bu data tiplerini nasıl kullanacağınızı bilmek yeterli. Java için bilinmesi gereken veri yapıları şu şekilde sıralayabiliriz;

* Object
* List & ArrayList
* Map & HashMap
* Array & SparseArray
* String & StringBuilder
* Primitive data types ( int , boolean , double, long, char v.b.)

Kotlin için bilinmesi gereken data tipleri java ile aynı şekilde diyebiliriz. Fakat kotlin java vm üzerinde çalıştığı için kodlar ilk olarak javaya ardından makine diline çevrilir. Data tipleri temel olarak aynıdır fakat kotlin’de java’dan farklı olarak her bir primitive tip aslında bir referans tipidir,  yani bir objedir. Java’da int olarak belirttiğimiz integer tipi kotlin’de Int olarak tanımlandığını fark edeceksiniz. Bu sayede kotlin’de integer değerler üzerinde kolayca uygulayabileceğiniz çoğu fonksiyona ulaşabileceksiniz.

### Kaynaklar
* https://kotlinlang.org/docs/reference/basic-types.html
* https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
* https://www.javatpoint.com/java-list
* https://www.javatpoint.com/java-arraylist
* https://www.javatpoint.com/java-map
* https://www.javatpoint.com/java-hashmap

## Version Control System
Git bizlerin kullandığı en güçlü araçlardan bir tanesi. Projelerimizi geliştirirken bu aracın belli başlı özelliklerini rahat kullanabiliyor olmamız bizler için önemli. Paylaştığımız sayfa ile Git hakkında basit bilgiler edinebilirsiniz, bu sayfayı takip ederken adımları kendi makinalarınızda uygulamalısınız. Bu adımları tamamladıktan sonra bu başlığı geçebilirsiniz fakat daha kompleks komutları ve arka planda bu aracın nasıl çalıştığını araştırmanızı öneriyoruz. 

GitFlow, bu aracı kullanırken takip ettiğimiz modeldir. Öncelikle böyle bir modele neden ihtiyaç duyulduğunu araştırıp, bu modelin bizlere sağladığı faydaları incelemenizi öneriyoruz.

### Kaynaklar
* https://rogerdudler.github.io/git-guide
* https://datasift.github.io/gitflow/IntroducingGitFlow.html#:~:text=GitFlow%20is%20a%20branching%20model,and%20scaling%20the%20development%20team

## Android Studio
Programlama yaparken kullandığımız IDE'ye hakim olmak bizlere büyük faydalar sağlıyor. Bu IDE'yi iyi tanıyıp kullanarak, sadece geliştirme hızımızı arttırmakla kalmayıp aynı zamanda geliştirdiğimiz ürünlerde hata ayıklama, performans izleme gibi becerileri kazanıyor olacağız.

Bu başlık altında Android projelerinin dosya yapılarını, logcat, emulator, debugger ve layout inspector kullanımlarını araştırmanızı öneriyoruz.

### Kaynaklar
* https://blog.mindorks.com/android-studio-tutorial-for-beginners
* https://medium.com/androiddevelopers/debugging-in-android-studio-dfbbf8a8d03c

## UI Components
UI components Android uygulama geliştirmede kullandığımız tasarım elemanlarıdır. Bu aşamada yapılması gereken mümkün olduğu kadar tasarım elemanları üzerinde değişiklikler ve denemeler olmalı. Öğrenilmesi zorunlu olan tasarım elemanları aşağıdaki gibidir.

* Textview, ImageView ve EditText
* Checkbox, RadioButton ve RadioGroup
* ProgressBar, SeekBar ve SnackBar
* ScrollView ve NestedScrollView
* Spinner
* CardView
* RecyclerView
* Layout çeşitleri
* Toolbar
* ViewPager ve Tablayout
* RecyclerView2 ve ViewPager2
* FloatingActionButton
* NavigationDrawer

## Manifest

Manifest, proje içerisinde bir çok konfigürasyonu yönettiğimiz xml dosyasıdır. Yönettiğimiz parametrelere örnek vermek gerekirse proje içerisinde bazen kullanıcıdan istediğimiz hassas bilgiler için izin alırız ve bu izinleri Permissions olarak adlandırırız. Bu izinlerin uygulama içinde kullanılıp kullanılmayacağının kararını manifest dosyasında belirtiriz. Internet kullanım iznine örnek vermek gerekirse eğer;
 <uses-permission android:name="android.permission.INTERNET" />

### Kaynaklar
* https://developer.android.com/guide/topics/manifest/manifest-intro
* https://www.androidauthority.com/androidmanifest-xml-992934/

## Activity
Bir Activity android cihazın kullanıcı ile etkileşime giren yüzüdür. Bir Android uygulamanın ortaya çıkabilmesi için mutlaka bir Activity’e ihtiyacı vardır. Activity’lerin kendilerine ait yaşam döngüleri vardır ve bir Android uygulama geliştirirken sürekli olarak bu yaşam döngüsü hesaba katılarak hareket edilmeli. İlk aşamalar buna ihtiyaç duymayabilirsiniz fakat bazı durumlarda bazı objelerin uygulama kapandığı esnada yok edilmesi gerekir. Bu objelere örnek olarak MediaPlayer objelerini örnek verebiliriz. Eğer bu objeleri uygulama kapandığı anda yok etmezsek yaşamlarına devam ettiklerini ve bir takım crash’lere sebep olduğunu göreceksiniz.

### Kaynaklar
* https://developer.android.com/guide/components/activities/intro-activities
* https://developer.android.com/guide/components/activities/activity-lifecycle
* https://developer.android.com/guide/components/activities/state-changes

## Fragment
Android framework’ü ilk ortaya çıktığında fragmentlar henüz yoktu. Çok basit arayüzler için bile activity’ler oluşturuluyordu ki bu hiç ekonomik bir durum değildi. Bir süre sonra istediğimiz zaman kolaylıkla çağırabileceğimiz kendisine ait yaşam döngüsü olan ve activity’lerden daha az maliyetli fragment’lar ortaya çıktı. Bir süre sonra fragment’ların daha optimize çalışması ve her yerde kolaylıkla çağırılıp kullanılabilmeleri neredeyse tek activity’e sahip ve bütün ekranların fragment’dan oluştuğu uygulamaların ortaya çıkmasına yol açtı. Fragment konusunun anlaşılması için Google’ın dökümanı son derece ayrıntılı ve yeterlidir.

### Kaynaklar
* https://developer.android.com/guide/components/fragments

## Intent
Intent'ler yapılmasını istediğimiz operasyonun soyut bir açıklaması olarak görev yapıyorlar. Bu operasyon yeni bir Activity'nin açılması da olabilir, Android sisteminde gerçekleşmesini istediğimiz herhangi bir operasyon da.

### Kaynaklar
* https://developer.android.com/guide/components/intents-filters
* https://developer.android.com/guide/components/intents-common
* https://developer.android.com/training/basics/firstapp/starting-activity

## Resources
Uygulamalarımızı geliştirirken kullandığımız kaynakların tamamını inceleyip, bu kaynakları farklı cihaz boyutlarında, farklı dillerde nasıl kullanıldığını bu başlık altında araştırmanızı öneriyoruz.

### Kaynaklar
* https://developer.android.com/guide/topics/resources/providing-resources
* https://guides.codepath.com/android/Understanding-App-Resources

## Storage
Uygulamalarımızda kalıcı bilgileri kaydetmek adına, Android framework’ü disk yönetimi için bizlere birçok farklı araç sağlıyor. Bu araçların en basiti olan Shared Preferences’ı bu başlık altında inceleyeceğiz. Shared Preferences ile saklamak istediğimiz bilgiler key-value çifleri olarak tutabiliyoruz. 
Bu başlık Shared Preference ile önce basit veri tiplerini saklamayı, kompleks veri tiplerini Serialize edip saklamayı ve bu işlemlerin hepsini yaptığımız Manager yapılarını oluşturmayı öğreneceğiz.

### Kaynaklar
* https://developer.android.com/training/data-storage/shared-preferences
* https://codelabs.developers.google.com/codelabs/android-training-shared-preferences/index.html?index=..%2F..android-training#0

## Gradle
Projelerimizde yazdığımız kodları alıp cihazlara yüklenebilir bir çıktı veren sisteme Build sistemleri denir. Android geliştirirken Gradle aracını kullanıyoruz. Bu araç ile 3. parti kütüphaneleri projelerimize eklemeyi ve farklı build tipleri oluşturmayı öğreneceğiz. 

### Kaynaklar
* https://www.raywenderlich.com/249-gradle-tutorial-for-android-getting-started

## Principles
Bu başlık altında, uygulamalarımızı geliştirirken takip etmemiz gereken prensipleri inceleyeceğiz. Bu prensipler projelerimizin okunabilirliğini ve performanslarını arttırıp global standartları yakalamamızı sağlıyor.

SOLID: Sadece Android geliştirirken değil herhangi bir platformda geliştirme yaparken takip etmemiz gereken prensipler. Uncle Bob ismiyle bilinen ve bu prensipleri ilk defa ortaya koyan Robert C. Martin’in video sunumlarını takip etmenizi öneriyoruz.

Material Design: Android ekibi tarafından oluşturulan bu yönerge, uygulamalarımızı geliştirirken özellikle dizayn kısımlarında takip etmemiz gereken usulleri bizlere sunuyor.

### Kaynaklar
* https://scotch.io/bar-talk/s-o-l-i-d-the-first-five-principles-of-object-oriented-design
* https://www.youtube.com/watch?v=zHiWqnTWsn4
* https://material.io/develop/android
