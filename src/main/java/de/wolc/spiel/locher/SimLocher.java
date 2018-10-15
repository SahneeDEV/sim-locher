package de.wolc.spiel.locher;

import de.wolc.spiel.papier.A4;
import de.wolc.spiel.papier.Papier;
import de.wolc.spiel.papier.PapierStapel;

import java.util.ArrayList;

import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.upgrades.LocherUpgrade;

/**
 * Der eigentliche Locher. Er ist in der Lage Papier Stapel zu lochen.
 */
public class SimLocher
{
    private static final int STANDARD_STAERKE = 7;
    private static final int STANDARD_STANZER = 2;

    private PapierStapel<?> stapel;
    private String skin;
    private Class<? extends Papier> format;
    private ArrayList<LocherUpgrade> upgrades;

    public SimLocher() {
        this.upgrades = new ArrayList<LocherUpgrade>();
        this.skin = "locher_base";
        this.format = A4.class;
    }

    /**
     * Gibt die aktuelle Anzahl der Stanzer zurück.
     * @return Die Stanzer.
     */
    public int getStanzer() {
        int wert = STANDARD_STANZER;
        for (LocherUpgrade upgrade : upgrades) {
            wert = upgrade.upgradeStanzer(this, wert);
        }
        return wert;
    }

    /**
     * Gibt an wieviele Blätter maximal auf einmal gelocht werden können.
     * @return Die Anzahl der Blätter.
     */
    public int getStaerke() {
        int wert = STANDARD_STAERKE;
        for (LocherUpgrade upgrade : upgrades) {
            wert = upgrade.upgradeStaerke(this, wert);
        }
        return wert;
    }

    /**
     * Gibt die Upgrades des Lochers zurück, welche dann modifiziert werden können.
     * @return Die Liste der Locher Upgrades.
     */
    public ArrayList<LocherUpgrade> getUpgrades() {
        return this.upgrades;
    }

    /**
     * Gibt den Namen des aktuellen Locher-Skins zurück.
     * Der Skin existiert unter `src/main/java/de/wolc/gui/images/{skin-name-hier}.png`
     * @return Der Skin name.
     */
    public String getSkin() {
        return this.skin;
    }

    /**
     * Setzt den Namen des aktuellen Locher-Skins.
     * Der Skin existiert unter `src/main/java/de/wolc/gui/images/{skin-name-hier}.png`
     * @param skin Der Skin name.
     */
    public void setSkin(String skin) {
        this.skin = skin;
    }

    /**
     * Gibt das aktuelle Format zurück, auf das der Locher eingestellt ist.
     * @return Die Klasse des Formats(A4.class, A5.class, A6.class).
     */
    public Class<? extends Papier> getFormat() {
        return this.format;
    }

    /**
     * Setzt das aktuelle Format auf das der Locher eingestellt ist.
     * @param format A4.class, A5.class, A6.class
     */
    public void setFormat(Class<? extends Papier> format) {
        this.format = format;
    }

    /**
     * Legt einen Papier Stapel in den Locher ein, wenn der Locher auf das richtige Format eingestellt ist.
     * @param stapel Der Papier Stapel.
     * @return Wurde der Stapel eingelegt? true wenn ja, sonst false.
     */
    public boolean einlegen(PapierStapel<?> stapel) {
        if (stapel.getFormat() != this.getFormat()) {
            return false;
        }
        this.stapel = stapel;
        return true;
    }

    /**
     * Entnimmt den aktuellen Papier Stapel aus dem Locher.
     * @return Der entnommene Papier Stapel.
     */
    public PapierStapel<?> entnehmen() {
        PapierStapel<?> stapel = this.stapel;
        this.stapel = null;
        return stapel;
    }

    /**
     * Gibt den aktuell sich im Locher befindlichen Papier Stapel zurück.
     * @return Der aktuelle Papier Stapel.
     */
    public PapierStapel<?> getStapel() {
        return this.stapel;
    }

    /** 
     * Locht den aktuell eingelegten Papier Stapel.
     * @exception IllegalStateException Es ist kein Papier Stapel eingelegt.
     * @return Alle Konfettis, die bei diesem Lochprozess entstanden sind.
     */
    public Lochprozess lochen() {
        Lochprozess vorgang = new Lochprozess(this);
        // Kann nur lochen wenn wir einen Stapel haben und der Locher stark genug für diesen ist.
        if(this.stapel != null && this.stapel.groesse() <= this.getStaerke()) {
            this.stapel.gelocht(vorgang);
        }
        for (LocherUpgrade upgrade : upgrades) {
            upgrade.upgradeLochprozess(this, vorgang);
        }
        return vorgang;
    }
}
