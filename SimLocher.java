/**
 * Der eigentliche Locher. Er ist in der Lage Papier Stapel zu lochen.
 */
public class SimLocher
{
    private PapierStapel stapel;

    /**
     * Legt einen Papier Stapel in den Locher ein.
     * @param stapel Der Papier Stapel.
     */
    public void einlegen(PapierStapel stapel) {
        this.stapel = stapel;
    }

    /**
     * Entnimmt den aktuellen Papier Stapel aus dem Locher.
     * @return Der entnommene Papier Stapel.
     */
    public PapierStapel entnehmen() {
        PapierStapel stapel = this.stapel;
        this.stapel = null;
        return stapel;
    }

    /**
     * Gibt den aktuell sich im Locher befindlichen Papier Stapel zurück.
     * @return Der aktuelle Papier Stapel.
     */
    public PapierStapel getStapel() {
        return this.stapel;
    }

    /** 
     * Locht den aktuell eingelegten Papier Stapel.
     * @exception IllegalStateException Es ist kein Papier Stapel eingelegt.
     */
    public void lochen() {
        if(this.stapel == null) {
            throw new IllegalStateException("Es ist kein Papier Stapel eingelegt.");
        }
        this.stapel.gelocht();
    }
}
