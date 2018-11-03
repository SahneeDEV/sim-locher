package de.wolc;

import java.io.Serializable;

/**
 * Die Einstellungen des Spiels. Werden in einem eigenen Datenbankeintrag gespeichert.
 */
public class Einstellungen implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;

    /**
     * Erzeugt eine neue Einstellungsinstanz mit Standardwerten.
     */
    public Einstellungen() {
        this.vollbild = true;
    }

    private boolean vollbild;

    /**
     * Soll das Spiel im Vollbild laufen?
     * @return true wenn ja, sonst false.
     */
    public boolean isVollbild() {
        return vollbild;
    }

    /**
     * Setzt ob das Spiel im Vollbild laufen soll.
     * @param vollbild true wenn ja, sonst false.
     */
    public void setVollbild(boolean vollbild) {
        this.vollbild = vollbild;
    }
}