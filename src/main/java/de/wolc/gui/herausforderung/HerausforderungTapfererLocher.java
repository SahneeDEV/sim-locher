package de.wolc.gui.herausforderung;

import java.io.Serializable;

import de.wolc.gui.Game;
import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.Lochprozess;

/**
 * Diese Herausforderung erfordert dass der Spieler eine gewisse Anzahl an Konfetti mit einem Lochvorgang erzeugt.
 */
public class HerausforderungTapfererLocher extends Herausforderung implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;

    private int anzahl;

    public HerausforderungTapfererLocher(int anzahl) {
        super(new Preis(Farbe.ROT, 20, Farbe.PINK, 20));
        this.anzahl = anzahl;
    }

    /**
     * @return the anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

    @Override
    public void herausforderungLochprozess(Game spiel, Lochprozess prozess) {
        if (prozess.getKonfetti().size() >= this.getAnzahl()) {
            this.erreicht();
        }
    }

    @Override
    public String toString() {
        return "Tapferer Locher (" + this.getAnzahl() + " auf einen Streich)";
    }

}