public class A4 extends Papier
{
    //public abstract void lochen();
    //public abstract int getLochZahl();
    private int maximaleAnzahlLochen = 20;
    
    public void lochen(){
        Lochen lochen = new Lochen();
    }
    
    public void setGelochtAnzahl(byte anzahlReduktion){
        this.maximaleAnzahlLochen = (this.maximaleAnzahlLochen - anzahlReduktion);
    }
    
    //Konstruktor
    public A4(){
        
    }
}
