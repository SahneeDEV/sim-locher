package de.wolc.spiel.papier;

import java.io.Serializable;
import java.util.ArrayList;
import de.wolc.spiel.papier.Papier;
import de.wolc.spiel.locher.Lochprozess;

/**
 * Mehrere Papiere eines Types auf einmal um sie lochen zu können.
 */
public class PapierStapel<TPapier extends Papier> implements Serializable
{
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;

    private ArrayList<TPapier> papiere;
    private Class<TPapier> format;
    
    public PapierStapel(Class<TPapier> format) {
        this.papiere = new ArrayList<TPapier>();
        this.format = format;
    }
    
    /**
     * Gibt das Format der Papiere auf diesem Stapel zurück.
     * @return Das Format(A4.class, A5.class, A6.class)
     */
    public Class<TPapier> getFormat() {
        return this.format;
    }

    /**
     * Überprüft ob das gegegene Papier in diesem Papier Staple abgelegt worden ist und immnernoch darin vorhanden ist.
     * @param papier Der Papier.
     * @return true wenn vorhanden, sonst false.
     */
    public boolean istVorhanden(Papier papier) {
        return this.papiere.contains(papier);
    }

    /**
     * Legt ein Blatt Papier auf dem Stapel ab. Wenn das Papier breits auf dem Stapel ist, wird es nicht abgelegt.
     * @exception IllegalArgumentException Kann keine nicht existierenden Papiere auf den Stapel legen.
     * @param papier Das Papier, welches auf dem Stapel abgelegt werden soll.
     * @return true wenn das Papier abgelegt wurde, sonst false.
     */
    public boolean ablegen(TPapier papier) {
        if (!papier.existiert()) {
            throw new IllegalArgumentException("Kann keine nicht existierenden Papiere auf den Stapel legen.");
        }
        if (papiere.contains(papier)) {
            return false;
        }
        papiere.add(papier);
        return true;
    }
    
    /**
     * Entnimmt das oberste Papier aus dem Stapel wenn es darauf liegt.
     * @return Das Papier, welches vom Stapel entnommen wurde, sonst null.
     */
    public TPapier entnehmen() {
        if (groesse() == 0) {
            return null;
        }
        return papiere.remove(groesse() - 1);
    }
    
    /**
     * Gibt die Größe des Papierstapels zurück.
     * @return Die Anzahl
     */
    public int groesse() {
        return papiere.size();
    }

    /**
     * Wird aufgerufen wenn alle Papiere auf diesem Stapel gelocht worden sind. 
     * Benachrichtig alle Papiere dass diese gelocht worden sind.
     * @return Alle Konfettis, die bei diesem Lochprozess entstanden sind.
     */
    public void gelocht(Lochprozess prozess) {
        for(int i = 0; i < papiere.size(); i++) {
            Papier papier = papiere.get(i);
            papier.gelocht(prozess);
            // Wenn das Papier nach dem Lochen zu viele Löcher hat(= kaputtgelocht ist) 
            // wird es aus dem Stapel entnommen.
            if (!papier.existiert()) {
                this.papiere.remove(papier);
            }
        }
    }
}
