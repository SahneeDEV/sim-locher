package de.wolc.spiel.locher.upgrades;

import java.io.Serializable;

import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.SimLocher;

/**
 * Stellt ein Locher Upgrade dar.
 * Bitte Subklassen erstellen.
 */
public abstract class LocherUpgrade implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 2L;

    private Preis preis;
    private String name;

    protected LocherUpgrade(String name, Preis preis) {
        this.name = name;
        this.preis = preis;
    }

    /**
     * @return the name
     */
    public String getGuiName() {
        return name;
    }

    /**
     * Gibt die Kosten dieses Upgrades zurück.
     * @return Die Kosten.
     */
    public Preis getPreis() {
        return this.preis;
    }

    /**
     * Modifiziert die Stanzer des Lochers.
     * @param locher Der Locher.
     * @param stanzer Die Anzahl der Stanzer.
     * @return Die neue Anzahl der Stanzer.
     */
    public int upgradeStanzer(SimLocher locher, int stanzer) {
        return stanzer;
    }

    /**
     * Modifiziert die Stärke des Lochers.
     * @param locher Der Locher.
     * @param stanzer Die Stärke.
     * @return Die neue Stärke.
     */
    public int upgradeStaerke(SimLocher locher, int staerke) {
        return staerke;
    }


    /**
     * Wird nach jedem Lochprozess aufgerufen.
     * @param locher Der Locher.
     * @param stanzer Der Lochprozess.
     */
    public void upgradeLochprozess(SimLocher locher, Lochprozess prozess) {
        
    }

    public abstract String toString();
}
