package de.wolc.gui.herausforderung;

import java.io.Serializable;

import de.wolc.gui.Game;
import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.Lochprozess;

/**
 * Diese Herausforderung erfordert dass der Spieler eine gewisse Anzahl an Konfetti erzeugt.
 */
public class HerausforderungKonfettiRausch extends Herausforderung implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;

    private int anzahl;
    private int erreicht;

    public HerausforderungKonfettiRausch(int anzahl) {
        super(new Preis(Farbe.ORANGE, 40));
        this.anzahl = anzahl;
    }

    /**
     * @return the anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * @return the erreicht
     */
    public int getErreicht() {
        return erreicht;
    }

    @Override
    public void herausforderungLochprozess(Game spiel, Lochprozess prozess) {
        this.erreicht += prozess.getKonfetti().size();
        if (this.erreicht >= this.getAnzahl()) {
            this.erreicht();
        }
    }

    @Override
    public String toString() {
        return "Konfetti Rausch (" + this.getAnzahl() + "/" + this.getErreicht() + " Konfetti erzeugt)";
    }

}