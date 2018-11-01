package de.wolc.spiel.locher.upgrades;

import java.util.Random;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.SimLocher;

/**
 * Reduziert den Cooldown um eine zufällige Anzahl an Sekunden.
 */
public class UpgradeLocherAufSpeed extends LocherUpgrade {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;
    private static final Random ZUFALL = new Random();
    private static final Preis PREIS = new Preis(Farbe.BLAU, 20, Farbe.ORANGE, 10);

    private double min, max;

    public UpgradeLocherAufSpeed(double min, double max) {
        super("Locher auf Speed", PREIS);
        this.min = min;
        this.max = max;
    }

    /**
     * Die minimale Cooldownreduktion.
     * @return Die Zeit in Sekunden
     */
    public double getMin() {
        return this.min;
    }

    /**
     * Die maximale Cooldownreduktion.
     * @return Die Zeit in Sekunden
     */
    public double getMax() {
        return this.max;
    }

    @Override
    public void upgradeLochprozess(SimLocher locher, Lochprozess prozess) {
        // Cooldown um Zufallszahl reduzieren. Wir müssen nicht auf negative Werte überprüfen, da diese bereits vom 
        // Setter für Cooldown im Lochprozess herausgefiltert werden.
        double reduktion = min + (max - min) *  ZUFALL.nextDouble();
        prozess.setCooldown(prozess.getCooldown() - reduktion);
    }

    @Override
    public String toString() {
        return this.getGuiName() + ": " + min + "s - " + max + "s Cooldown Reduktion";
    }
}