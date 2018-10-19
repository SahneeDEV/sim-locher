package de.wolc.spiel.papier;

import java.io.Serializable;

import de.wolc.spiel.Farbe;

/**
 * Ein einzelnes Konfetti. 
 */
public class Konfetti implements Serializable
{
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;
    
    private final Farbe farbe;

    /**
     * Erstellt ein neues Konfetti mit der gegebenen Farbe.
     * @param farbe Die Farbe.
     */
    public Konfetti(Farbe farbe) {
        this.farbe = farbe;
    }

    /**
     * Gibt die Farbe des Konfettis zurück.
     * @return Die Farbe.
     */
    public Farbe getFarbe() {
        return this.farbe;
    }
}
