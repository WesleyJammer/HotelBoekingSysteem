# Read me


## Gebruikers aanwijzing HotelBoekingSysteem

###  Inhoud

1. Overzicht project
2. Installatie
3. Configuratie
4. Gebruik van het Hotelboekingsysteem

### 1. Overzicht project
Het Project betreft een digitaal systeem voor het invoeren, bekijken, aanpassen en verwijderen van hotelboekingen.
Het systeem is ontworpen om het voor de receptionist(e) makkelijker te maken om alle boekingen van het hotel bij te houden. Doordat de boekingen niet meer handmatig
opgeschreven hoeven te worden maar digitaal ingevoerd kunnen worden is de kans op het maken van fouten zoals een dubbele boeking ook kleiner.
Ook staan alle boekingen in een duidelijk overzicht weergeven zodat de receptionist(e) makkelijk een boeking erbij kan pakken voor verschillende doeleindes, zoals het wijzigen van bepaalde gegevens.
Uiteindelijk zal het gebruik van dit digitaal systeem voor hotelboekingen ervoor zorgen voor een efficientere werkomgeving en zodoende ook voor meer inkomsten. dit maakt een eventuele uitbreiding in de toekomst sneller mogelijk.

### 2. Installatie

#### 1. benodigheden
1. Een pc of laptop
2. HotelBoekingSysteem.zip
3. Java(Java Runtime Enviroment)
4. XAMPP

#### 2. Download HotelBoekingSysteem.zip
Als je via github.com op mijn project bent gekomen kan je de het bestand als een zip file downloaden.
Daarna kan je met een rechtermuisklik de optie vinden om de zipfile uit te pakken. selecteer deze optie

#### 3. Download Java
Als je nog geen Java op je laptop of pc hebt staan moet je dat nog eerst downloaden.
dit is namelijk de omgeving waarin de Applicatie draait. dat wilt zeggen dat zonder Java de Applicatie niet zou werken.
mocht je er niet zeker van zijn of je Java hebt geïnstalleerd op je pc of laptop kan je hem altijd overnieuw downloaden. Ook als je al een verouderde versie van Java hebt kan het geen kwaad om Java opnieuw te downloaden.

Java kan je [hier](https://www.java.com/en/download/manual.jsp) downloaden.

#### 4. Download XAMPP
Om de meegeleverde database te kunnen runnen heb je het progamma XAMPP nodig, aangezien de database niet online staat.
Zonder XAMPP en de meegeleverde database zou in dit geval het progamma ook niet werken.
XAMPP is een progamma waarin je een localeserver hebt waarin je bijvoorbeeld een sql database kan aanmaken in phpMyAdmin.
hierin zou je dus ook offline kunnen werken en deze server is allen beschikbaar op jou laptop of pc. dus tenzij je database zou exporteren en online zou doorsturen kan niemand anders jou database zien.


XAMMP kan je [hier](https://www.apachefriends.org/download.html) downloaden.




### Configuratie

####  Open database
1. Open ***XAMMP.***
2. Start ***Apache*** en start ***MySql.***
3. klik als bijde progammas gestart zijn op het ***admin*** knopje bij ***MySql.***
4. Klik ***boven*** aan het scherm op ***importeer.***
5. Importeer ***hotel_boeking_systeem(4).sql*** uit de ***sql*** map van ***HotelBoekingSysteem.***

### Gebruik van Hotelboekingsysteem

#### 1. Open HotelBoekingSysteem
1. Ga naar de folder structuur van ***HotelBoekingSysteem*** uit de uitgepakte zip folder
2. zoek naar een map die ***out*** heet.
3. open ***HotelBoekingSysteem.Jar***
4. De applicatie moet nu ***opgestart*** zijn

#### 2. Voeg een boeking toe
1. Ga naar ***de menu balk*** aan de rechterkant van het ***HotelBoekingSysteem scherm.***
2. Selecteer de pagina ***Voeg Toe.***
3. Vul ***alle invoervelden in met gegevens van de klant.***
4. Druk op ***Opslaan.***
5. Selecteer ***Boekingen*** uit de ***Menu balk.***
6. De boeking hoort nu in de TableView te staan.
7. Extra controle in ***MySql Admin*** van ***XAMMP.***
8. Gegevens staan daar in Tabellen ***boeking,klant en boeking_kamer.***

#### 3. Bekijk een boeking
1. Ga in de ***menu balk*** naar ***Boekingen.***
2. ***Selecteer*** een boeking uit de ***TableView.***
3. ***Alle*** gegevens uit de boeking komen nu te voorschijn.
4. Voor een extra controleren kunt u Altijd de Sql database erbij pakken.

#### 4. Wijzig een boeking
1. Ga in de ***menu balk*** naar ***wijzig/annuleer.***
2. Selecteer de ***benodigde boeking*** uit de ***TableView.***
3. ***Klik*** op de knop ***ga naar wijzigen.***
4. Vul alle ***invoervelden*** in die ***gewijzigd*** moeten worden.
5. Geef een ***Akkoord*** dat u de boeking daadwerkelijk wilt wijzigen.
6. Druk op ***wijzigen.***
7. In de ***lijst met boekingen*** ziet u nu de ***wijzigingen*** staan.
8. In ***MySql admin*** kunt u in ***hotel_boeking_systeem*** zien dat in de tabellen ***boeking,klant of boeking_kamer*** gegevens gewijzigd zijn.

#### 5. Annuleer een boeking
1. Ga in de ***menu balk*** naar ***wijzig/annuleer.***
2. Selecteer de ***benodigde boeking*** uit de ***TableView.***
3. ***Klik*** op de knop ***ga naar Annuleren.***
4. Geef een ***Akkoord*** dat u de boeking daadwerkelijk wilt annuleren.
5. Druk op ***annuleren.***
6. In de ***lijst met boekingen*** ziet u de desbetreffende boeking ***niet meer staan***.
7. In ***MySql admin*** kunt u in ***hotel_boeking_systeem*** zien dat in de tabellen ***boeking,klant en boeking_kamer*** geen gegevens meer staan van de desbetreffende boeking .

