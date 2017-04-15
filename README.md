# My project's README
Zadaća 3. - Tasky - Aplikacija za pohranu obaveza

Potrebno je kreirati aplikaciju koja koristi jednostavnu bazu podataka za kreiranje korisničkih zabilješki(neka vrsta To do liste). Nužno je omogućiti korisniku kreiranje zabilješki, spremanje u bazu, pregled zabilješki u obliku liste te njihovo brisanje. Koristiti primjere u ovoj vježbi te naučeno na proteklim vježbama.

Prvi problem s kojim sam se susreo je bio napraviti bazu podataka za pohranu kategorija. To je riješeno pomoću Youtube tutorijala korisnika "ProgrammingKnowledge" u kojem je objašnjeno sve što je potrebno (u vezi SQLite baza) za ovu zadaću. Pomoću istog tutorijala je napravljena i baza za pohranu Taskova. Prilikom stvaranja baze Taskova bilo je potrebno odabrati primarni ključ po kojemu će se znati koji Task treba obrisati. Odabran je datum stvaranja objekta Task, jer pretpostavka je da se nijedna dva Taska neće napraviti na isti datum, u istoj sekundi. Kasnije je to promijenjeno u ID koji pretstavlja integer, međutim taj način se pokazao lošijim rješenjem(tj. nije radio), pa je vraćeno da je primarni ključ datum stvaranja. ListView i njegov adapter su napravljeni po uzoru na ListView i adapter u trećoj laboratorijskoj vježbi. S tim da je u adapteru još dodan i ImageView koji mijenja boju u ovisnosti o prioritetu Task-a. Pri brisanju Taskova je bilo potrebno nekako predati metodi deleteData datum kada je Task kreiran. Rješenje za to je nađeno na StackOverflowu, a datum kreiranja se dobije preko ArrayList-a u koji se učitaju svi Taskovi iz baze. Također, pri dodavanju Taskova je bilo potrebno provjeriti da je korisnik uneo podatke u sve EditText-ove, a to je riješeno tako da se provjeri da li su EditText-ovi prazni, te da li je odabrana kategorija. Ukoliko nije odabrana kategorija ili je jedan EditText prazan, ispisuje se Toast poruka da nisu uneseni svi podaci, a inače se doda novi Task i vrati na glavni Activity(List Activity).

Izvori:

http://www.androiddesignpatterns.com/2012/05/correctly-managing-your-sqlite-database.html
http://tips.androidhive.info/2013/10/android-insert-datetime-value-in-sqlite-database/
http://stackoverflow.com/questions/9277747/android-simpledateformat-how-to-use-it
https://developer.android.com/training/basics/data-storage/databases.html
http://www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/
https://www.youtube.com/playlist?list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07
http://stackoverflow.com/questions/31161944/how-to-delete-a-single-row-in-android-sqlite
http://stackoverflow.com/questions/6290531/check-if-edittext-is-empty