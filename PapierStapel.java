import java.util.ArrayList;

/**
 * Mehrere Papiere auf einmal um sie lochen zu können.
 */
public class PapierStapel
{
    private final ArrayList<Papier> papiere;
    
    public PapierStapel() {
        this.papiere = new ArrayList<Papier>();
    }
    
    /**
     * Legt ein Blatt Papier auf dem Stapel ab. Wenn das Papier breits auf dem Stapel ist, wird es nicht abgelegt.
     * @return true wenn das Papier abgelegt wurde, sonst false.
     */
    public boolean ablegen(Papier papier) {
        if (papiere.contains(papier)) {
            return false;
        }
        papiere.add(papier);
        return true;
    }
    
    /**
     * Entnimmt das Papier aus dem Stapel wenn es darauf liegt.
     * @return true wenn das Papier entnommen wurde, sonst false.
     */
    public boolean entnehmen(Papier papier) {
        return papiere.remove(papier);
    }
    
    /**
     * Gibt die Größe des Papierstapels zurück.
     */
    public int groesse() {
        return papiere.size();
    }
}
