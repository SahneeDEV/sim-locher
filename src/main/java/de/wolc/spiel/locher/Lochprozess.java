package de.wolc.spiel.locher;

import java.util.ArrayList;
import de.wolc.spiel.locher.SimLocher;
import de.wolc.spiel.papier.Konfetti;

/**
 * Ein Lochprozess stellt den Vorgang dar, einen Papierstapel zu lochen. Er enthält alle dafür nötigen Daten, sovie das 
 * Resultat des Vorgangs.
 */
public class Lochprozess
{
    private final SimLocher locher;
    private final ArrayList<Konfetti> konfetti;

    public Lochprozess(SimLocher locher) {
        this.konfetti = new ArrayList<Konfetti>();
        this.locher = locher;
    }

    /**
     * Gibt den Locher zurück, welcher diesen Lochprozess durchführt.
     * @return Der Locher.
     */
    public SimLocher getLocher() {
        return this.locher;
    }

    /**
     * Gibt das Konfetti zurück, welches in disem Lochprozess erzeugt wurde.
     * @return Das Konfetti.
     */
    public ArrayList<Konfetti> getKonfetti() {
        return this.konfetti;
    }
}
