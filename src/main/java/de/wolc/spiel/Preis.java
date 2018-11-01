package de.wolc.spiel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Gibt einen generischen Preis in Konfettifarben an. Hat mehrere Konstruktoren um möglichst einfach einen Preis 
 * erzeugen zu können.
 */
public class Preis implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 30L;

    private Map<Farbe, Integer> kosten;

    /**
     * Gibt die Kosten als Map zurück.
     * @return Die Kosten.
     */
    public Map<Farbe, Integer> getKosten() {
        return this.kosten;
    }

    public Preis(Map<Farbe, Integer> kosten) {
        this.kosten = kosten;
    }

    public Preis() {
        this.kosten = new HashMap<>();
    }

    public Preis(Farbe farbe1, int kosten1) {
        HashMap<Farbe, Integer> hash = new HashMap<>();
        hash.put(farbe1, kosten1);
        this.kosten = hash;
    }

    public Preis(Farbe farbe1, int kosten1, Farbe farbe2, int kosten2) {
        HashMap<Farbe, Integer> hash = new HashMap<>();
        hash.put(farbe1, kosten1);
        hash.put(farbe2, kosten2);
        this.kosten = hash;
    }

    public Preis(Farbe farbe1, int kosten1, Farbe farbe2, int kosten2, Farbe farbe3, int kosten3) {
        HashMap<Farbe, Integer> hash = new HashMap<>();
        hash.put(farbe1, kosten1);
        hash.put(farbe2, kosten2);
        hash.put(farbe3, kosten3);
        this.kosten = hash;
    }

    public Preis(Farbe farbe1, int kosten1, Farbe farbe2, int kosten2, Farbe farbe3, int kosten3, Farbe farbe4, int kosten4) {
        HashMap<Farbe, Integer> hash = new HashMap<>();
        hash.put(farbe1, kosten1);
        hash.put(farbe2, kosten2);
        hash.put(farbe3, kosten3);
        hash.put(farbe4, kosten4);
        this.kosten = hash;
    }

    @Override
    public String toString() {
        if (kosten.size() == 0) {
            return "🤑 Kostenlos! 🤑";
        } else {
            String kostenString = "";
            for(Farbe farbe: this.kosten.keySet()) {
                Integer zahl = this.kosten.get(farbe);
                String zeile = farbe.getAnzeigeName() + ": " + zahl;
                if (kostenString.length() != 0) {
                    kostenString += "\n";
                }
                kostenString += zeile;
            }
            return kostenString;
        }
    }
}
