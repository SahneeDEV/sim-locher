public abstract class Papier
{
    private final int maximaleAnzahlLochen;
    private int lochAnzahl;
    
    protected Papier(int maximaleAnzahlLochen) {
        this.maximaleAnzahlLochen = maximaleAnzahlLochen;
    }
    
    public int getMaximaleLochAnzahl() {
        return this.maximaleAnzahlLochen;
    }

    /**
    * Gibt die gesamt Zahl der Löcher dieses Papieres zurück.
    * @return Die Löcher auf diesem Papier.
    */
    public int getLochAnzahl() {
        return this.lochAnzahl;
    }

    /**
     * Wird aufgerufen wenn das Papier gelocht wurde.
     */
    public void gelocht() {
        lochAnzahl++;
    }
}
