package com.eltescode.rpgsession.features.admin_user.data.repository


val text =
    "1. Akrobatyka 2. Aktorstwo 3. Alchemia 4. Astrologia 5. Astronomia 6. Bardzo krzepki 7. Bardzo szybki 8. Bardzo odporny 9. Bijatyka 10. Błaznowanie 11. Błogosławieństwa powszechne 12. Błyskotliwość 13. Brzuchomówstwo 14. Bystry wzrok 15. Celny cios 16. Celny strzał 17. Charakteryzacja 18. Chemia 19. Chiromancja 20. Chirurgia 21. Cichy chód 22. Czarnoksięstwo 23. Czarostwo 24. Człowiek-guma 25. Czuły słuch 26. Czuły węch 27. Czynienie cudów 28. Czytanie z warg 29. Czytanie_pisanie 30. Defraudacja 31. Doliniarstwo 32. Doświadczenie bojowe 33. Dowodzenie 34. Erotyka 35. Etykieta 36. Farmacja 37. Finta 38. Gadanina 39. Gawędziarstwo 40. Geniusz arytmetyczny 41. Gotowanie 42. Górnictwo 43. Guślarstwo 44. Hazard 45. Heraldyka 46. Hipnoza 47. Historia 48. Infrawizja 49. Inżynieria 50. Jeździectwo 51. Język tajemny 52. Jubilerstwo 53. Kamieniarstwo 54. Kartografia 55. Komedianctwo 56. Kowalstwo 57. Krasomówstwo 58. Krawiectwo 59. Kryptografia 60. Kucie runy 61. Kucie runy mistrzów 62. Leczenie chorób 63. Leczenie ran 64. Łowiectwo 65. Magia czarnoksięska 66. Magia powszechna 67. Magia prosta 68. Magia tajemna 69. Medytacja 70. Metalurgia 71. Meteorologia 72. Mimika 73. Mocna głowa 74. Monetoznawstwo 75. Muzykalność 76. Naśladownictwo 77. Oburęczność 78. Odporność na choroby 79. Odporność na grozę 80. Odporność na strach 81. Odporność na trucizny 82. Ogłuszenie 83. Opieka nad zwierzętami 84. Otwieranie zamków 85. Pieśni bardów 86. Piwowarstwo 87. Pływanie 88. Połykanie ognia 89. Posłuch u zwierząt 90. Powożenie 91. Prawo 92. Przekupstwo 93. Przyzwanie zwierząt 94. Riposta 95. Rozbrojenie 96. Rozpoznawanie magicznych przedmiotów 97. Rozpoznawanie ożywieńców 98. Rozpoznawanie roślin 99. Rozpoznawanie run 100. Różdżkarstwo 101. Rusznikarstwo 102. Rybołówstwo 103. Sekretne znaki 104. Sekretny język 105. Silny cios 106. Siłacz 107. Snycerstwo 108. Specjalna broń 109. Splatanie magii 110. Stolarstwo 111. Szacowanie 112. Szaleńczy atak 113. Szczęście 114. Szkutnictwo 115. Szósty zmysł 116. Sztuka 117. Sztuka przetrwania 118. Szulerstwo 119. Szybki refleks 120. Szyderstwo 121. Śledzenie 122. Ślepowidzenie 123. Śpiew 124. Taniec 125. Targowanie się 126. Teologia 127. Torturowanie 128. Tresura 129. Tropienie 130. Tworzenie magicznych pergaminów 131. Tworzenie magicznych przedmiotów 132. Tworzenie tatuażu 133. Tworzenie tatuażu mistrzów 134. Ucieczka 135. Ukrywanie się 136. Uniki 137. Urok osobisty 138. Uwodzenie 139. Uzdolnienia językowe 140. Walka na koniu 141. Warzenie trucizn 142. Widzenie w ciemności 143. Wiedza 144. Wiedza dynastyczna 145. Wiedza o demonach 146. Wiedza o magicznych pergaminach 147. Wiedza o morzu 148. Wiedza o rzekach 149. Wiosłowanie 150. Wirująca śmierć 151. Woltyżerka 152. Wróżenie 153. Wspinaczka 154. Wyczucie kierunku 155. Wykrywanie istot magicznych 156. Wykrywanie magii 157. Wykrywanie pułapek 158. Wyzwalanie z więzów 159. Zacieranie śladów 160. Zapasy 161. Zarządzanie 162. Zastawianie pułapek 163. Zastraszanie 164. Zielarstwo 165. Zmysł magii 166. Znajomość języka obcego 167. Zwinne palce 168. żebractwo 169. żeglowanie 170. żonglowanie"


val textPo: List<String> = text.replace(Regex("[1234567890]")) {
    when (it.value) {
        "1" -> " "
        "2" -> " "
        "3" -> " "
        "4" -> " "
        "5" -> " "
        "6" -> " "
        "7" -> " "
        "8" -> " "
        "9" -> " "
        "0" -> " "
        else -> it.value
    }
}.split(".").filter { !it.contains(".") }.map { it.trim().lowercase() }.filter { it.isNotBlank() }


val text2 =
    "APTEKARZ BANITA BŁAZEN BYDŁOKRAD CHŁOP CHŁOPIEC_OKRĘTOWY CIURA_OBOZOWA CYRKOWIEC CYRULIK CZELADNIK DRUID DRWAL DZIWKA GAJOWY_KŁUSOWNIK GAWĘDZIARZ GIERMEK GLADIATOR GONIEC GÓRNIK GUŚLARZ HANDLARZ HIENA_CMENTARNA GRABARZ HIPNOTYZER INŻYNIER_KHAZADZKI LATARNIK ŁOWCA NAGRÓD MIESZCZANIN MILICJANT MINSTREL MYŚLIWY MYTNIK NAJEMNIK NOWICJUSZ_OCHRONIARZ OPRYCH PASTERZ PAŹ PILOT POBORCA_PODATKOWY PODŻEGACZ POGANIACZ_MUŁÓW POGROMCA_TROLLI PORYWACZ_ZWŁOK POSZUKIWACZ_ZŁOTA PRZEMYTNIK PRZEPATRYWACZ PRZEWOŹNIK RAJFUR ROBOTNIK ROZJEMCA RYBAK SKRYBA SŁUGA STRAŻNIK_DRÓG STRAŻNIK_KANAŁÓW STRAŻNIK_MIEJSKI STRAŻNIK_WIĘZIENNY SZCZUROŁAP SZLACHCIC SZULER TERMINATOR TRAPER UCZEŃ_CZARODZIEJA UCZEŃ_MEDYKA UCZEŃ_MISTRZA_TATUAŻU WĘDROWNY_KRAMARZ WĘGLARZ WIESZCZ WOJOWNIK_PODZIEMNY WOŹNICA ZABIJAKA ZIELARZ ZŁODZIEJ ŻAK ŻEBRAK ŻEGLARZ ŻOŁNIERZ ŻOŁNIEŻ_OKRĘTOWY_PIRAT"

val umki = text2.lowercase().split(" ")