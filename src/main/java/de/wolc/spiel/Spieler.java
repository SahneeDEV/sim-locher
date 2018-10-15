package de.wolc.spiel;

import java.util.ArrayList;

import de.wolc.spiel.locher.SimLocher;
import de.wolc.spiel.papier.Konfetti;

/**
 * Die Spieler Klasse ist die primäre öffentliche API. Die GUI Implementation sollte hiervon eine neue Instanz erstellen
 * und dann die Getter und Setter in dem erzeugen Objekt und deren Unterobjekte verwenden.
 */
public class Spieler {
    private final SimLocher locher;
    private final ArrayList<Konfetti> konfetti;

    public Spieler() {
        this.locher = new SimLocher();
        this.konfetti = new ArrayList<Konfetti>();
    }

    /**
     * Gibt den Locher des Spielers zurück.
     * @return Der Locher.
     */
    public SimLocher getLocher() {
        return this.locher;
    }

    /**
     * Gibt das die Konfettiliste des Spielers zurück. Diese repräsentiert den Punktestand.
     * Hinweis: Nach dem Lochen muss dieser Liste manuell das erzeugte Konfetti hinzugefügt werden (falls gewünscht).
     * @return Die Liste von Konfetti.
     */
    public ArrayList<Konfetti> getKonfetti() {
        return this.konfetti;
    }

    /**
     * Muss regelmäßig von der GUI aufgerufen werden um der Logik ein "Zeitgefühl" zu verschaffen.
     * Ruft automatisch die "tick" Methode des Lochers auf!
     * @param deltaZeit Die vergangene Zeit in Sekunden. (Kann/sollte auch Kommazahl sein!)
     */
    public void tick(double deltaZeit) {
        locher.tick(deltaZeit);
    }
}
