package de.wolc.spiel.locher.upgrades;

import java.io.Serializable;

import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.SimLocher;

/**
 * Stellt ein Locher Upgrade dar.
 * Bitte Subklassen erstellen.
 */
public abstract class LocherUpgrade implements Serializable {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;

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
}
