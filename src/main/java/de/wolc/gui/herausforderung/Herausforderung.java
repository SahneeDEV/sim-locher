package de.wolc.gui.herausforderung;

import java.io.Serializable;

import de.wolc.gui.Game;
import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.Lochprozess;

/**
 * <p>Basis Klasse für eigene Herausforderungen.<br/>
 * Herausforderungen stellen eine bestimmte Aufgabe für den Spieler dar die von diesem erfüllt werden müssen.</p>
 * 
 * <p>Herausforderungen können gestartet werden indem sie einfach der Gui.getHerausforderungen() Liste hinzugefügt
 * werden.<br/>
 * Abbrechen ist aktuell aus technischen Gründen nicht unterstützt.</p>
 * 
 * <p><b>Achtung für eigene Herausforderungen</b>: Keine Werte von Events als Klassenvariable speichern. Das spielt
 * nicht gut mit dem Speicherstand zusammen.</p>
 */
public abstract class Herausforderung implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 2L;

    private boolean erreicht;
    private boolean belohnungErhalten;
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
     * Das der Spieler bereits die Belohnung für diese Herausforderung erhalten?
     * @return true wenn ja, sonst false.
     */
    public boolean hatBelohnungErhalten() {
        return belohnungErhalten;
    }

    /**
     * Setzt dass der Spieler bereits die Belohnung für diese Herausforderung erhalten hat.
     */
    public void setBelohnungErhalten() {
        this.belohnungErhalten = true;
    }

    /**
     * Diese Methode MUSS aufgerufen werden sobald die Herausforderung erreicht wurde.
     */
    protected void erreicht() {
        if (this.erreicht) {
            throw new IllegalStateException("Diese Herausforderung wurde bereits erreicht!");
        }
        System.out.println("Herausforderung erreicht: " + this);
        this.erreicht = true;
    }

    /**
     * Formattiert die Herausforderung(Name, Fortschrittsanzeige, ...) um sie in der GUI schön für Fleischsäcke anzeigen 
     * zu können.
     * @return Der verständliche Anzeigestring.
     */
    @Override
    public abstract String toString();

    // =========================================
    // == EVENTS ===============================
    // Diese Methoden können in eigenen Herausforderungen überschrieben werden um bei bestimmten Ereignissen die
    // Hausforderung zu aktualisieren.
    // TODO: Itemshop Events (kaufen, startet, etc...)
    // TODO: Mehr Locher Event -> Wir sind die GUI, wir können alles!
    // =========================================

    /**
     * Wird aufgerufen sobald das Spiel startet.
     * @param spiel Das Spiel.
     */
    public void herausforderungSpielStart(Game spiel) { }

    /**
     * Wird aufgerufen sobald das Spiel beendet.
     * @param spiel Das Spiel.
     */
    public void herausforderungSpielEnde(Game spiel) { }

    /**
     * Wird nach jedem Lochprozess aufgerufen.
     * @param spiel Das Spiel.
     * @param prozess Der Lochprozess.
     */
    public void herausforderungLochprozess(Game spiel, Lochprozess prozess) { }

    /**
     * Wird bei jedem Game Tick aufgerufen.
     * @param spiel Das Spiel.
     * @param deltaZeit Die vergangene Zeit in Sekunden.
     */
    public void herausforderungTick(Game spiel, double deltaZeit) { }
}
