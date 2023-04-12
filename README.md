# Pisti-Game-Java
Java Pisti Game Implementation

1 kişinin bilgisayara oynayabileceği bir pişti oyunu tasarlayınız. Bilgisayar kağıtları rasgele 
sıralayacak ve bir turu ayrı bir dosyaya yazacak. Mesela açılış.txt, tur1.txt, tur2.txt şeklinde en 
son tur6.txt’de oyun bitecek. Her dosyanın içinde 8 adet değişik kağıt bulunacak (tabii ki 
önceki turlarda yer almayan). Bu kağıtların 4 tanesi bilgisayarın, 4 tanesi de karşısındaki 
insanın olacak. Bilgisayar elbette karşısındaki insandaki kağıtların ne olduğunu bilemeyecek 
(en son turda bilebilir).
Puanlamada her türlü J=1 puan, ♦10 3 puan, ♣2 2 puan olarak alınacak. Oyunun sonunda 
kağıt fazlası olan oyuncu 3 puan daha alacak. 
Bu kurallara uygun olarak bir Java veya Python programı yazın. Arayüz olması şart değil. 
Oyunu nasıl oynadığını, oyun esnasında ne yaptığını bana mantıklı şekilde anlatabilecek 
durumda olmanız gerekiyor.

Dikkat edilmesi gereken durumlar:
- Oyun tüm 52 kağıt bitene kadar devam eder.
- Her pişti yapıldığında 10 puan alınır. Bu 10 puanın haricinde yukarıda belirtilen 
puanlamaya da dikkat etmek gerekir.
- Oyun stratejisinde kağıtların gelme olasılığını göz önüne almanız gerekmektedir. 
Strateji olarak o anda en mantıklı kağıdı oynamanız beklenir. Bununla ilgili programa 
yorumlar da ekleyebilirsiniz.
- Oyunun açılışında yerde kapalı durumda olan 3 kağıdın ne olduğunu bilmediğinizi varsayarak modeli, kurgulayın. Elbette eğer oyun esnasında ilk aynı kağıdı bularak yerdeki kağıtları almaya hak kazanan taraf bu kağıtların ne olduğunu görebilecek. Yani eğer bilgisayar ilk eli alırsa yapay zeka algoritması bu kağıtların değerlerini bileceği için onları kullanabilir. Fakat ilk eli, insan kazanırsa, bu durumda yapay zeka (bilgisayar) bu kağıtların ne olduğunu oyunun sonuna kadar bilemeyecek, ve olasılıkları hesaplarken bunu da göz önüne alacak.
