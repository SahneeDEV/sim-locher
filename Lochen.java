public class Lochen
{
    public Lochen(){
        
    }
    
    // Prüft, ob die maximale Anzahl an Lochvorgängen für das Blatt überschritten wurde
    // Wir verwenden den Getter "getMaximaleLochAnzahl" von Papier um die maximale Loch Anzahl herauszufinden.
    private boolean anzahlBisherGelocht(int bisherGelocht, Papier DinA) {
        if(checkGelochtAnzahl(bisherGelocht, DinA.getMaximaleLochAnzahl())) {
            return true;
        }
        return false;
    }
    
    //Prüft, ob die maximale Anzahl an Lochvorgängen für das Blatt überschritten wurde
    // Integer für die Lochzahlen falls man öfters als 128 mal lochen kann. (Java bytes sind signed)
    private boolean checkGelochtAnzahl(int bisherGelocht, int maximaleAnzahlLochen){
        if(bisherGelocht <= maximaleAnzahlLochen){
            return true;
        }
        else{
            return false;
        }
     }
}
