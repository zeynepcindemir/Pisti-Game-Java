# Pisti-Game-Java
Java Pisti Game Implementation

Tr Instructions
---------------

1 kişinin bilgisayara oynayabileceği bir pişti oyunu tasarlayınız. Bilgisayar kağıtları rasgele sıralayacak ve bir turu ayrı bir dosyaya yazacak. Mesela açılış.txt, tur1.txt, tur2.txt şeklinde en son tur6.txt’de oyun bitecek. Her dosyanın içinde 8 adet değişik kağıt bulunacak (tabii ki önceki turlarda yer almayan). Bu kağıtların 4 tanesi bilgisayarın, 4 tanesi de karşısındaki insanın olacak. Bilgisayar elbette karşısındaki insandaki kağıtların ne olduğunu bilemeyecek (en son turda bilebilir).
Puanlamada her türlü J=1 puan, ♦10 3 puan, ♣2 2 puan olarak alınacak. Oyunun sonunda kağıt fazlası olan oyuncu 3 puan daha alacak. 


Dikkat edilmesi gereken durumlar:

- Oyun tüm 52 kağıt bitene kadar devam eder.
- Her pişti yapıldığında 10 puan alınır. Bu 10 puanın haricinde yukarıda belirtilen puanlamaya da dikkat etmek gerekir.
- Oyun stratejisinde kağıtların gelme olasılığını göz önüne almanız gerekmektedir. 
Strateji olarak o anda en mantıklı kağıdı oynamanız beklenir.
- Oyunun açılışında yerde kapalı durumda olan 3 kağıdın ne olduğunu bilmediğinizi varsayarak modeli, kurgulayın. Elbette eğer oyun esnasında ilk aynı kağıdı bularak yerdeki kağıtları almaya hak kazanan taraf bu kağıtların ne olduğunu görebilecek. Yani eğer bilgisayar ilk eli alırsa yapay zeka algoritması bu kağıtların değerlerini bileceği için onları kullanabilir. Fakat ilk eli, insan kazanırsa, bu durumda yapay zeka (bilgisayar) bu kağıtların ne olduğunu oyunun sonuna kadar bilemeyecek, ve olasılıkları hesaplarken bunu da göz önüne alacak.


Eng Instructions
---------------

Design a "pişti" game that can be played on a computer by one person. The computer will shuffle the cards randomly and write each round to a separate file, such as opening.txt, round1.txt, round2.txt, and so on, until the game ends in round6.txt. Each file will contain eight different cards (which were not used in previous rounds), with four belonging to the computer and four to the human player. The computer will not know which cards the human player has (even in the last round).

The score for each card will be J=1 point, ♦10=3 points, and ♣2=2 points. At the end of the game, the player with more leftover cards will get an additional 3 points.

Important considerations:

- The game will continue until all 52 cards have been used.
- Each "pişti" (a specific combination of cards) will earn 10 points. In addition to these 10 points, the scoring system described above should be taken into account.
- The game strategy should take into account the probability of cards being played. The most logical move at any given time should be played.
- At the beginning of the game, three cards will be face-down on the table, and their values will be unknown to the players. If a player wins the first round and collects these cards, they will be able to see their values for the rest of the game. However, if the computer wins the first round, it will not know the values of these cards and will take this into account when calculating probabilities throughout the game.
