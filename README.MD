# sim-locher

## Was ist das?

sim-locher(auch wolc) ist eine Hochrealistische Lochersimulation. Es wird simuliert Papiere zu lochen um diese zu Konfetti zu verarbeiten.

Je mehr Konfetti man erzeugt desto bessere Upgrades können für den eigenen Locher gekauft werden!

## Credits

* Locher Sound: https://freesound.org/people/Australopithecusman/sounds/388946/

## Entwicklerhinweise

### Leaderboardserver

#### Verwenden

Der Leaderboard REST Server ist unter https://sim-locher.herokuapp.com/ erreichbar.

Folgende HTTP Requests können verwendet werden(Alle Requests verwenden JSON als Payload Format):

 * `GET / {}` - Leitet nur auf GitHub weiter. Dient nur dem falls jemand die URL in den Browser tippen sollte.
 * `POST /api/leaderboard { name: String, punkte: Integer }` - Legt den neuen Leaderboard Score für den Spieler fest.
 * `GET /api/leaderboard {}` - Gibt alle Leaderboard Scores zurück.
 * `GET /api/leaderboard/top/:top {}` - Gibt die Top `:top` Leaderboard Scores zurück.
 * `GET /api/leaderboard/spieler/:name {}` - Gibt den aktuellen Leaderboard Score von `:name` zurück.

Rückgabecodes sind entweder 200, 400 oder 500. Bei 400 und 500 ist der Fehler in der Property `fehler` zu finden.
Bei 200 wird stets die Antwort auf die Anfrage gesendet.

#### Entwickeln

Server lokal laufen lassen: 

1. Node.js(10) installieren: https://nodejs.org/en/
2. MongoDB(3.6) installieren: https://www.mongodb.com/download-center/community
3. MongoDB starten, eventuell als Service einrichten
4. `/server/.env` Datei erstellen und `DB=mongodb://user:password@host:port/datenbank` eintragen. (`user`, `password`, `host`, `port` & `datenbank` sind hier natürlich durch die eigenen Werte zu ersetzen.)
5. `/scripts/run-server` ausführen um den Server zu starten.

### IDEs

Das Projekt ist mit Maven und BlueJ kompatibel.

#### BlueJ

1. Öffnen und loslegen.
2. Merken dass die Performance schlecht ist und zu VS Code wechseln
3. Anwendung nicht über die Gui.java starten, sondern die JavaFX Anwendung "Stages" via Rechtsklick starten

#### Maven & VS Code

1. Download: https://archive.apache.org/dist/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.zip
2. Das `.zip` Archiv in einen Ordner mit Schreibrechne für den aktuellen Nutzer entpacken. (z.B. `E:/Data/Maven/`)
3. Den Pfad in dem es entpackt wurde in Umgegungsvariable `M2_HOME` packen. (z.B. `M2_HOME=E:/Data/Maven/`)
4. Dem `PATH` den Pfad in den `bin` Unterordner geben.. (z.B. `PATH=...;E:/Data/Maven/bin`)
5. Maven kann jetzt via `mvn` verwendet werden.
6. Kompilieren: Maven Goal "compile"
7. Ausführen/Debuggen: https://www.youtube.com/watch?v=0Ky_RnluHUY
8. .jar erstellen: Maven Goal "package"


 
# RGB Locher 4 the Win
