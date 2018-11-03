package de.wolc.spiel.herausforderung;

import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.Lochprozess;

public abstract class Herausforderung {
    // FIXME: Können wir nicht verwenden -> Serialisierung
    private Runnable erreichtHandler;
    private boolean erreicht;
    private Preis belohnung;

    protected Herausforderung(Preis belohnung) {
        this.belohnung = belohnung;
    }

    /**
     * Ist diese Herausforderung erreicht?
     * @return true wenn ja, sonst false.
     */
    public boolean isErreicht() {
        return erreicht;
    }

    /**
     * Gibt die Belohnung für das Erreichen der Herausforderung zurück.
     * @return Die Belohnung in Konfetti.
     */
    public Preis getBelohnung() {
        return belohnung;
    }

    /**
     * Gibt den aktuell gesetzten Handler zurück der augerufen wird sobald die Herausforderung abgeschlossen ist.
     * @return Der Handler.
     */
    public Runnable getErreichtHandler() {
        return erreichtHandler;
    }

    /**
     * Setzt eine Funktion die darüber informiert sobald die Herausforderung abgeschlossen ist.
     * @param erreichtHandler Der Handler.
     */
    public void setErreichtHandler(Runnable erreichtHandler) {
        this.erreichtHandler = erreichtHandler;
    }

    /**
     * Diese Methode muss aufgerufen werden sobald die Herausforderung erreicht wurde.
     */
    protected void erreicht() {
        if (this.erreicht) {
            throw new IllegalStateException("Diese Herausforderung wurde bereits erreicht!");
        }
        this.erreicht = true;
        if (this.getErreichtHandler() != null) {
            this.getErreichtHandler().run();
        }
    }

    @Override
    public abstract String toString();

    // =========================================
    // EVENTS
    // =========================================

    /**
     * Wird nach jedem Lochprozess aufgerufen.
     * @param prozess Der Lochprozess.
     */
    public void herausforderungLochprozess(Lochprozess prozess) {

    }

    /**
     * Wird bei jedem Tick aufgerufen.
     * @param deltaZeit Die vergangene Zeit in Sekunden.
     */
    public void herausforderungTick(double deltaZeit) {

    }
}
