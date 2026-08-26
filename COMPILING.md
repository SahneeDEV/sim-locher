# Kompilieren via Docker

Dieses Projekt braucht Java 8 samt **JavaFX** (in `javafx.*` Packages) zum Kompilieren.
Das ist etwas unangenehm, weil:

* Ein reines Java 8 **JRE** (kein JDK) hat keinen `javac` - man kann also nicht kompilieren.
* Ein "normales" Java 8 **JDK** (z.B. OpenJDK, auch das von CircleCI verwendete
  `circleci/openjdk:8-jdk` Docker Image) bringt **kein JavaFX** mit. Nur Oracles eigenes
  JDK/JRE 8 hatte JavaFX von Haus aus dabei (`jre/lib/ext/jfxrt.jar`).
* Aktuelle OpenJFX Pakete (z.B. `apt install openjfx` unter Debian/Ubuntu) sind für
  JavaFX 11+ gebaut. Deren `.class` Dateien haben ein neueres Class-File-Format
  (Version 54, Java 10) als das, was ein Java-8-`javac` lesen kann (Version 52, Java 8) -
  `-extdirs` mit diesen Jars schlägt daher mit `bad class file ... wrong version` fehl.

Die Lösung hier: Ein Docker Container mit einem echten Java-8-`javac` (kein JavaFX nötig)
kompiliert das Projekt, aber als Klassenpfad für `javafx.*` wird die echte `jfxrt.jar`
aus einer lokal bereits vorhandenen Oracle-Java-8-Installation verwendet. Das ist reiner
Java-Bytecode (Version 52) und plattformunabhängig - für's reine *Kompilieren* ist es egal,
ob die Jar von Windows oder Linux stammt, da keine nativen Bibliotheken gebraucht werden.

Zum **Ausführen** des fertigen Spiels wird trotzdem ein echtes Java-8-JRE mit den nativen
JavaFX-Bibliotheken benötigt (z.B. genau die Oracle-Installation, aus der die `jfxrt.jar`
stammt) - das im Docker Container gebaute `.jar` lässt sich nicht *im Container selbst*
starten/testen (keine Grafikausgabe, keine nativen Libs), nur bauen.

## Voraussetzungen

1. **Docker** installiert und der Docker-Daemon läuft (`docker info` sollte funktionieren).
2. Irgendeine lokale **Oracle Java 8** Installation (JRE reicht) mit `jre/lib/ext/jfxrt.jar`.
   Falls noch keine vorhanden ist: Ein Oracle JRE/JDK 8 installieren (z.B. von
   [Oracle Archive](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)),
   danach nach `jfxrt.jar` suchen:

   ```powershell
   Get-ChildItem "C:\Program Files\Java" -Recurse -Filter jfxrt.jar
   ```

   In dieser Umgebung liegt sie unter:
   `C:\Program Files\Java\jre1.8.0_491\lib\ext\jfxrt.jar`

   Falls sich der Pfad unterscheidet, in den Befehlen unten entsprechend anpassen.

## Kompilieren & Paketieren

Aus dem Projekt-Wurzelverzeichnis ausführen (Git Bash / MSYS unter Windows):

```bash
# Pfad-Konvertierung von Git Bash für Docker-Argumente abschalten
export MSYS_NO_PATHCONV=1

# Optional: Maven-Cache in einem Docker Volume behalten, damit nicht bei jedem
# Build alle Plugins/Dependencies neu heruntergeladen werden müssen
docker volume create sim-locher-m2

docker run --rm \
  -v "//s/Projects/sim-locher:/repo" \
  -v sim-locher-m2:/root/.m2 \
  -v "//c/Program Files/Java/jre1.8.0_491/lib/ext:/jfx:ro" \
  -w /repo \
  circleci/openjdk:8-jdk \
  mvn -DskipTests -Djavafx.extdirs=/jfx package
```

Unter PowerShell (native Windows-Pfade, kein `MSYS_NO_PATHCONV` nötig):

```powershell
docker volume create sim-locher-m2

docker run --rm `
  -v "S:\Projects\sim-locher:/repo" `
  -v sim-locher-m2:/root/.m2 `
  -v "C:\Program Files\Java\jre1.8.0_491\lib\ext:/jfx:ro" `
  -w /repo `
  circleci/openjdk:8-jdk `
  mvn -DskipTests -Djavafx.extdirs=/jfx package
```

Das Ergebnis liegt danach unter `target/wolc-<version>.jar`.

### Was macht `-Djavafx.extdirs=/jfx`?

In der [pom.xml](pom.xml) gibt es ein Maven-Profil `docker-javafx`, das **nur** aktiv wird,
wenn die Property `javafx.extdirs` gesetzt ist. Es reicht den Pfad als `-extdirs` Compiler-Flag
an `javac` durch, damit die dort liegende `jfxrt.jar` beim Kompilieren als JavaFX-Klassenpfad
verwendet wird. Ohne dieses Property (z.B. bei einem ganz normalen `mvn package` mit einem
echten Oracle JDK 8) hat das Profil keinerlei Effekt - es verändert also nichts am normalen
Build-Verhalten.

### Warum `-DskipTests`?

Die Unit-Tests laden u.a. die `Farbe`-Enum, die `javafx.scene.paint.Color` verwendet. Das
Initialisieren echter JavaFX-Klassen (nicht nur das reine Kompilieren dagegen) braucht die
*nativen* JavaFX-Bibliotheken des jeweiligen Betriebssystems - die stecken nicht in der
`jfxrt.jar`, sondern liegen als native Libraries daneben (z.B. `.dll` unter Windows).
Im Linux-Container sind diese nicht vorhanden, daher schlagen die Tests dort mit
`NoClassDefFoundError` fehl. Das ist eine Einschränkung des Container-Builds, kein Bug im
Code - lokal mit einem echten Java-8-JDK (z.B. Oracle) laufen die Tests normal durch.

## Spiel starten

Das gebaute `.jar` mit einem echten, lokal installierten Java 8 starten (nicht im Container!):

```powershell
& "C:\Program Files (x86)\Common Files\Oracle\Java\java8path\java.exe" -jar target\wolc-1.6.0.jar
```

oder einfach per Doppelklick auf die `.jar`-Datei, falls `.jar` mit Java verknüpft ist.
