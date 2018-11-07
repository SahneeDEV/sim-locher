package de.wolc.spiel.locher.upgrades;

import java.util.List;

import de.wolc.MultiUse;
import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.papier.Konfetti;

/**
 * Erzeugt automatisch Konfetti.
 */
public class UpgradePraktikant extends LocherUpgrade {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 3L;
    private static final Preis PREIS = new Preis(
        Farbe.GRUEN, 15, Farbe.ROT, 15, 
        Farbe.BLAU, 15, Farbe.ORANGE, 15, 
        Farbe.PINK, 15, Farbe.WEISS, 15
    );
    
    private double time;
    private double vergangen;
    private int min, max;

    public UpgradePraktikant(double time, int min, int max) {
        super("Praktikant", PREIS);
        this.time = time;
        this.min = min;
        this.max = max;
    }

    public double getTime() {
        return time;
    }

    /**
     * @return the vergangen
     */
    public double getVergangen() {
        return vergangen;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    @Override
    public void upgradeKonfettiGenerator(double deltaZeit, List<Konfetti> konfetti) {
        this.vergangen += deltaZeit;
        if (this.vergangen >= this.time) {
            int anzahl = MultiUse.zufall(this.min, this.max);
            for(int i = 0; i < anzahl; i++) {
                konfetti.add(new Konfetti(Farbe.zufallsfarbe()));
            }
            this.vergangen = 0;
        }
    }

    @Override
    public String toString() {
        return this.getGuiName() + ": +" + this.min + " - " + this.max + " Konfetti /" + MultiUse.sekundenRunden(this.time) + "s";
    }
}