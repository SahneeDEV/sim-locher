import java.util.ArrayList;

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
     * @param papier Das Papier, welches auf dem Stapel abgelegt werden soll.
     * @return true wenn das Papier abgelegt wurde, sonst false.
     */
    public boolean ablegen(TPapier papier) {
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
}
