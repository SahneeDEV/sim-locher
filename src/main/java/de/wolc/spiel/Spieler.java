package de.wolc.spiel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import de.wolc.gui.Gui;
import de.wolc.spiel.locher.SimLocher;
import de.wolc.spiel.papier.Konfetti;

/**
 * Die Spieler Klasse ist die primäre öffentliche API. Die GUI Implementation sollte hiervon eine neue Instanz erstellen
 * und dann die Getter und Setter in dem erzeugen Objekt und deren Unterobjekte verwenden.
 */
public class Spieler implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 5L;
    
    private SimLocher locher;
    private SchreibtischSkin schreibtischSkin;
    private ArrayList<Konfetti> konfetti;
    private String name;
    private HintergrundMusik hintergrundMusik;
    private double hintergrundMusikVolume;

    public Spieler() {
        this.locher = new SimLocher();
        this.schreibtischSkin = SchreibtischSkin.BACKGROUND_BASE;
        this.konfetti = new ArrayList<Konfetti>();
        this.name = "Namenloser Held";
        this.hintergrundMusik = HintergrundMusik.BACKGROUNDMUSIK_OFFICIALSOUNDTRACK;
        this.hintergrundMusikVolume = Gui.getEinstellungen().getAmbientSoundVolume();
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the schreibtischSkin
     */
    public SchreibtischSkin getSchreibtischSkin() {
        return this.schreibtischSkin;
    }

    /**
     * @param schreibtischSkin the schreibtischSkin to set
     */
    public void setSchreibtischSkin(SchreibtischSkin schreibtischSkin) {
        this.schreibtischSkin = schreibtischSkin;
    }

    /**
     * @return the hintergrundMusik
     */
    public HintergrundMusik geHintergrundMusik(){
        return this.hintergrundMusik;
    }

    /**
     * @param hintergrundMusik the hintergrundMusik to set
     */
    public void setHintergrundMusik(HintergrundMusik hintergrundMusik){
        this.hintergrundMusik = hintergrundMusik;
    }

    /**
     * @return die lautstärke der Hintergrundmusik
     */
    public double getHintergrundMusikVolume(){
        return this.hintergrundMusikVolume;
    }

    /**
     * Setzt die lautstärke der Hintergrundmusik
     * @param hintergrundMusikVolume die lautstärke der Hintergrundmusik zwischen 0 und 100
     */
    public void setHintergrundMusikVolume(double hintergrundMusikVolume){
        if(hintergrundMusikVolume > 0 && hintergrundMusikVolume <= 100){
            this.hintergrundMusikVolume = hintergrundMusikVolume;
        }
    }

    /**
     * Gibt den Locher des Spielers zurück.
     * @return Der Locher.
     */
    public SimLocher getLocher() {
        return this.locher;
    }

    /**
     * Gibt das die Konfettiliste des Spielers zurück. Diese repräsentiert den Punktestand.
     * Hinweis: Nach dem Lochen muss dieser Liste manuell das erzeugte Konfetti hinzugefügt werden (falls gewünscht).
     * @return Die Liste von Konfetti.
     */
    public ArrayList<Konfetti> getKonfetti() {
        return this.konfetti;
    }

    /**
     * Gibt eine HashMap mit allen Konfetti Arten sortiert zurück.
     * @return The HashMap.
     */
    public HashMap<Farbe, ArrayList<Konfetti>> getKonfettiSortiert() {   
        HashMap<Farbe, ArrayList<Konfetti>> hash = new HashMap<>();
        for(Konfetti konfetti : this.getKonfetti()) {
            Farbe farbe = konfetti.getFarbe();
            ArrayList<Konfetti> zahl = hash.get(farbe);
            if (zahl == null) {
                zahl = new ArrayList<>();
                hash.put(farbe, zahl);
            }
            zahl.add(konfetti);
        }
        return hash;
    }

    /**
     * Muss regelmäßig von der GUI aufgerufen werden um der Logik ein "Zeitgefühl" zu verschaffen.
     * Ruft automatisch die "tick" Methode des Lochers auf!
     * @param deltaZeit Die vergangene Zeit in Sekunden. (Kann/sollte auch Kommazahl sein!)
     */
    public void tick(double deltaZeit) {
        locher.tick(deltaZeit);
    }
}
