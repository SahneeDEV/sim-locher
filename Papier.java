public abstract class Papier
{
    private final int maximaleAnzahlLochen;
    
    protected Papier(int maximaleAnzahlLochen) {
        this.maximaleAnzahlLochen = maximaleAnzahlLochen;
    }
        
    public abstract void lochen();
    
    public int getMaximaleLochAnzahl() {
        return this.maximaleAnzahlLochen;
    }
}
