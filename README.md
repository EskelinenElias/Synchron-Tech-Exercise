Synchron Tech Harjoitustehtävä
Summer Sync 2024
Elias Eskelinen

Tilanvarausjärjestelmä
- Järjestelmällä käyttäjä voi varata tiloja (esim. opiskelutiloja yliopistolta tms.)

Hakemiston rakenne: 
- src-kansio sisältää toteutetun järjestelmän
- tests-kansio sisältää luokille toteutetut yksikkötestit

Toteutetut luokat: 
- Main
    - Sisältää esimerkkiajon järjestelmän käytöstä

- ReservationSystem
    - Luokka, jolla voi tehdä varauksia
    - Sisältää metodeja saatavilla olevien huoneiden etsimiseen, varauksen tekemiseen ja varauken poistamiseen
    - Varauksen tekemiseen tarvitaan käyttäjän id, huone sekä varauksen alku- ja loppuaika
    - Singleton-luokka mahdollistaa useiden käyttäjien tekevän varauksia samanaikaisesti, ilman että päällekkäisiä varauksia pääsee syntymään. 

- SortedReservationList
    - Luokka, joka säilöö varauksia. Varaukset on säilötty aikajärjestyksessä, jotta niiden etsiminen olisi nopeampaa. 
    - Sisältää useita metodeja varausten suodattamiseen.

- Reservation-luokka
    - Yksittäisen varauksen tiedot: uniikki id, varauksen tehneen käyttäjän id, huone, varauksen alku- ja loppuaika.
    
- Room-luokka
    - Yksittäisen huoneen tiedot: uniikki id, huoneen nimi, koko ja kuvaus.


