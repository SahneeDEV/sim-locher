package de.wolc;

import java.io.Serializable;

/**
 * Die Einstellungen des Spiels. Werden in einem eigenen Datenbankeintrag gespeichert.
 */
public class Einstellungen implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 2L;

    /**
     * Erzeugt eine neue Einstellungsinstanz mit Standardwerten.
     */
    public Einstellungen() {
        this.vollbild = true;
        this.entitySound = true;
        this.ambientSound = true;
    }

    private boolean vollbild;
    private boolean entitySound;
    private boolean ambientSound;

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
    
    /**
     * Soll der Sounds der Entities (des Lochers) abgespielt werden?
     * @return true wenn ja, sonst false
     */
    public boolean entitySoundEnabled(){
        return this.entitySound;
    }

    /**
     * Setzt ob der Sounds der Entities (des Lochers) abgespielt werden soll
     * @param entitySound true wenn ja, sonst false
     */
    public void setEntitySoundEnabled(boolean entitySound){
        this.entitySound = entitySound;
    }

    /**
     * Soll der Offizielle Soundtrack abgespielt werden?
     * @return true wenn ja, sonst false
     */
    public boolean ambientSoundEnabled(){
        return this.ambientSound;
    }

    /**
     * Setzt ob der Offizielle Soundtrack abgespielt werden soll
     * @param ambientSound true wenn ja, sonst false
     */
    public void setAmbientSoundEnabled(boolean ambientSound){
        this.ambientSound = ambientSound;
    }
}