package de.wolc.spiel.locher;

import java.util.ArrayList;

/**
 * Ein Lochprozess stellt den Vorgang dar, einen Papierstapel zu lochen. Er enthält alle dafür nötigen Daten, sovie das 
 * Resultat des Vorgangs.
 */
public class Lochprozess
{
    private final SimLocher locher;
    private final ArrayList<Konfetti> konfetti;

    public Lochprozess(SimLocher locher) {
        this.konfetti = new ArrayList<Konfetti>();
        this.locher = locher;
    }

    /**
     * Gibt den Locher zurück, welcher diesen Lochprozess durchführt.
     * @return Der Locher.
     */
    public SimLocher getLocher() {
        return this.locher;
    }

    /**
     * Gibt das Konfetti zurück, welches in disem Lochprozess erzeugt wurde.
     * @return Das Konfetti.
     */
    public ArrayList<Konfetti> getKonfetti() {
        return this.konfetti;
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
