package de.wolc.spiel.locher.upgrades;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.SimLocher;

/**
 * Der Vampir fügt dem Locher mehr Stanzer("Zähne") hinzu. 🧛🧛🧛
 */
public class UpgradeVampir extends LocherUpgrade {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;
    private static final Preis PREIS = new Preis(Farbe.WEISS, 10, Farbe.ROT, 50);
    
    private int stanzer;

    public UpgradeVampir(int staerke) {
        super("Vampir", PREIS);
        this.stanzer = staerke;
    }

    /**
     * Gibt an wie umwieviel Stärke die Stanzer Stärke erhöht wird.
     * @return Wie Stark sind die Stanzer?
     */
    public int getStanzer() {
        return stanzer;
    }

    @Override
    public int upgradeStanzer(SimLocher locher, int stanzer) {
        return stanzer + this.stanzer;
    }

    @Override
    public String toString() {
        return this.getGuiName() + ": +" + stanzer + " Stanzer";
    }
}
