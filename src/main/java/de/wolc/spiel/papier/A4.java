package de.wolc.spiel.papier;

import java.io.Serializable;

public class A4 extends Papier implements Serializable
{
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;
    
    private static final int MAX_LOECHER = 20;
    private static final double GROESSE = 1d;
    
    //Konstruktor
    public A4(){
        super(MAX_LOECHER, GROESSE);
    }
}
