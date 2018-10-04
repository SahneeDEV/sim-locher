public class A4 extends Papier
{
    private static final int maximaleAnzahlLochen = 20;
    
    public void lochen(){
        Lochen lochen = new Lochen();
    }
    
    //Konstruktor
    public A4(){
        super(maximaleAnzahlLochen);
    }
}
