package de.wolc.spiel.papier;

import java.io.Serializable;

public class A5 extends Papier implements Serializable
{
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;
    
    private static int MAX_LOECHER = 15;
    private static final double GROESSE = 0.8d;
    
    //Konstruktor
    public A5(){
        super(MAX_LOECHER, GROESSE);
    }
}
