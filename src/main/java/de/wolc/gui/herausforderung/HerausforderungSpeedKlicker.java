package de.wolc.gui.herausforderung;

import java.io.Serializable;
import java.util.ArrayList;

import de.wolc.MultiUse;
import de.wolc.gui.Game;
import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.Lochprozess;

/**
 * Diese Herausforderung erfordert dass der Spieler eine Xmal in Y Sekunden locht.
 */
public class HerausforderungSpeedKlicker extends Herausforderung implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;

    private int anzahl;
    private ArrayList<Double> erreicht;
    private double lastTime, zeit;

    public HerausforderungSpeedKlicker(int anzahl, double zeit) {
        super(new Preis(Farbe.GRUEN, 20, Farbe.WEISS, 20));
        this.anzahl = anzahl;
        this.erreicht = new ArrayList<>();
        this.zeit = zeit;
    }

    /**
     * @return the zeit
     */
    public double getZeit() {
        return this.zeit;
    }

    /**
     * @return the anzahl
     */
    public int getAnzahl() {
        return this.anzahl;
    }

    /**
     * @return the erreicht
     */
    public int getErreicht() {
        return this.erreicht.size();
    }

    @Override
    public void herausforderungLochprozess(Game spiel, Lochprozess prozess) {
        this.erreicht.add(this.lastTime);
        if (this.getErreicht() >= this.getAnzahl()) {
            this.erreicht();
        }
    }

    @Override
    public void herausforderungTick(Game spiel, double deltaZeit) {
        this.lastTime += deltaZeit;
        int anzahl = this.erreicht.size();
        for(int i = 0; i < anzahl; i++) {
            double zeit = this.erreicht.get(i);
            if (zeit < this.lastTime - this.zeit) {
                this.erreicht.remove(i);
                i--;
                anzahl--;
            }
        }
    }

    @Override
    public String toString() {
        return "Speed Klicker (" + this.getErreicht() + "/" + this.getAnzahl() + " Mal in " + MultiUse.sekundenRunden(getZeit()) + "s gelocht)";
    }

}