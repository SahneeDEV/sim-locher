public class Lochen
{
    public Lochen(){
        
    }
    
    //Prüft, ob die maximale Anzahl an Lochvorgängen für das Blatt überschritten wurde
    // 0 = DinA4; 1 = DinA5, 2 = DinA6
    private boolean anzahlBisherGelocht(byte bisherGelocht, byte DinA, byte maximaleAnzahlLochen){
        
        switch(DinA){
            //DinA4
            case 0:
                if(checkGelochtAnzahl(bisherGelocht, maximaleAnzahlLochen)){
                    
                }
                break;
            //DinA5
            case 1:
                break;
            //DinA6
            case 2:
                break;
            default:
                break;
        }
        
        return false;
    }
    
    //Prüft, ob die maximale Anzahl an Lochvorgängen für das Blatt überschritten wurde
    private boolean checkGelochtAnzahl(byte bisherGelocht, byte maximaleAnzahlLochen){
        if(bisherGelocht <= maximaleAnzahlLochen){
            return true;
        }
        else{
            return false;
        }
    }
}
