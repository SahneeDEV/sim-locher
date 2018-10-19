package de.wolc.spiel.papier;

import java.io.Serializable;

public class A6 extends Papier implements Serializable
{
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;
    
    private static int maximaleAnzahlLochen = 10;
    
    //Konstruktor
    public A6(){
        super(maximaleAnzahlLochen);
    }
}
