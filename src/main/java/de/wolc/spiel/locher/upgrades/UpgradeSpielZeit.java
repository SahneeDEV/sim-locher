package de.wolc.spiel.locher.upgrades;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;

/**
 * Der Panzer Stanzer erhöht die Stärke der Stanzer um mehr Blätter auf einal zu lochen.
 */
public class UpgradeSpielZeit extends LocherUpgrade {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;
    private static final Preis PREIS = new Preis(Farbe.GRUEN, 15, Farbe.ROT, 10);
    
    private double time;

    public UpgradeSpielZeit(double time) {
        super("SpielZeit", PREIS);
        this.time = time;
    }

    /**
     * Gibt an wie umwieviel Stärke die Stanzer Stärke erhöht wird.
     * @return Wie Stark sind die Stanzer? 
     */
    public double getTime() {
        return time;
    }

    @Override
    public double upgradeSpielZeit (double time) {
        return time + this.time;
    }

    @Override
    public String toString() {
        return this.getGuiName() + ": +" + (Math.round((time * 10) / 10)) + "s Spielzeit";
    }
}
