package de.wolc.gui.herausforderung;

import java.io.Serializable;

import de.wolc.MultiUse;
import de.wolc.gui.Game;
import de.wolc.spiel.Preis;

/**
 * Diese Herausforderung erfordert dass der Spieler eine gewisse Zeit in
 * Sekunden im Spiel verbringt.
 */
public class HerausforderungZeitOhneZeit extends Herausforderung implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;

    private double zeit;
    private double vergangeneZeit;

    public HerausforderungZeitOhneZeit(double zeit) {
        super(new Preis());
        this.zeit = zeit;
        this.vergangeneZeit = 0d;
    }

    /**
     * Gibt nötige Gesamtzeit an.
     * @return Die Zeit.
     */
    public double getZeit() {
        return this.zeit;
    }

    /**
     * Gibt die Zeit die bereits vergangen ist an.
     * @return Die vergangene Zeit.
     */
    public double getVergangeneZeit() {
        return this.vergangeneZeit;
    }

    /**
     * Gibt die Zeit an welche noch verstreichen muss.
     * @return Die übrige Zeit.
     */
    public double getUebrigeZeit() {
        return this.zeit - this.vergangeneZeit;
    }

    @Override
    public void herausforderungTick(Game spiel, double deltaZeit) {
        this.vergangeneZeit += deltaZeit;
        if (this.vergangeneZeit >= this.zeit) {
            this.vergangeneZeit = this.zeit;
            this.erreicht();
        }
    }

    @Override
    public String toString() {
        return "Zeit ohne Zeit (" + 
            MultiUse.sekundenRunden(this.getVergangeneZeit()) + "s / " + 
            MultiUse.sekundenRunden(this.getZeit()) + "s)";
    }

}