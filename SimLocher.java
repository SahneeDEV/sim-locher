/**
 * Der eigentliche Locher. Er ist in der Lage Papier Stapel zu lochen.
 */
public class SimLocher
{
    private PapierStapel stapel;
    private int stanzer;

    public SimLocher() {
        this.stanzer = 2;
    }

    /**
     * Gibt die aktuelle Anzahl der Stanzer zurück.
     * @return Die Stanzer.
     */
    public int getStanzer() {
        return stanzer;
    }

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
     * @return Alle Konfettis, die bei diesem Lochprozess entstanden sind.
     */
    public Lochprozess lochen() {
        Lochprozess vorgang = new Lochprozess(this);
        if(this.stapel != null) {
            // Erzeuge einen Lochprozess welcher dem Stapel übergeben wird.
            this.stapel.gelocht(vorgang);
        }
        return vorgang;
    }
}
