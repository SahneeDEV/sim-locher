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
    private static final double STANDARD_COOLDOWN = 3.5;

    private final SimLocher locher;
    private final ArrayList<Konfetti> konfetti;
    private double cooldown;

    public Lochprozess(SimLocher locher) {
        this.konfetti = new ArrayList<Konfetti>();
        this.locher = locher;
        this.cooldown = STANDARD_COOLDOWN;
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

    /**
     * Gibt den Cooldown an welchen der Locher nach dem Prozess haben wird.
     * @return Der Cooldown in Sekunden.
     */
    public double getCooldown() {
        return this.cooldown;
    }

    /**
     * Setzt den Cooldown des Lochers nach dem Lochvorgang. Werte unter 0 werden durch 0 ausgetauscht.
     * @param cooldown Der Cooldown in Sekunden.
     */
    public void setCooldown(double cooldown) {
        this.cooldown = Math.max(cooldown, 0);
    }
}
