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
     * Überprüft ob dieses Papier noch existiert
     * Papiere die mehr oder gleich die Anzahl der maximalen Löcher haben existieren nicht mehr.
     * @return true wenn das Papier existiert, sonst false.
     */
    public boolean existiert() {
        return this.lochAnzahl >= this.getMaximaleLochAnzahl();
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
        if (this.existiert()) {
            this.lochAnzahl += 2;
        }
    }
}
