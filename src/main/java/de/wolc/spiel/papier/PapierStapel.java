package de.wolc.spiel.papier;

import java.util.ArrayList;
import de.wolc.spiel.papier.Papier;
import de.wolc.spiel.locher.Lochprozess;

/**
 * Mehrere Papiere eines Types auf einmal um sie lochen zu können.
 */
public class PapierStapel<TPapier extends Papier>
{
    private final ArrayList<TPapier> papiere;
    
    public PapierStapel() {
        this.papiere = new ArrayList<TPapier>();
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
     * Entnimmt das Papier aus dem Stapel wenn es darauf liegt.
     * @param papier Das Papier, welches vom Stapel entnommen werden soll.
     * @return true wenn das Papier entnommen wurde, sonst false.
     */
    public boolean entnehmen(TPapier papier) {
        return papiere.remove(papier);
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
