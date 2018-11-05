package de.wolc.spiel.locher;

import de.wolc.spiel.papier.A4;
import de.wolc.spiel.papier.Papier;
import de.wolc.spiel.papier.PapierStapel;

import java.io.Serializable;
import java.util.ArrayList;

import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.upgrades.LocherUpgrade;

/**
 * Der eigentliche Locher. Er ist in der Lage Papier Stapel zu lochen.
 */
public class SimLocher implements Serializable
{
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;

    private static final int STANDARD_STAERKE = 7;
    private static final int STANDARD_STANZER = 2;

    private PapierStapel<?> stapel;
    private LocherSkin skin;
    private Class<? extends Papier> format;
    private double cooldown;
    private ArrayList<LocherUpgrade> upgrades;
    
    public SimLocher() {
        this.upgrades = new ArrayList<LocherUpgrade>();
        this.skin = LocherSkin.LOCHER_BASE;
        this.format = A4.class;
        this.cooldown = 0;
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
     * Gibt den aktuellen Cooldown des Lochers zurück.
     * @return Der Cooldown in Sekunden. Achtung, Kommazahl!
     */
    public double getCooldown() {
        return this.cooldown;
    }

    /**
     * Gibt den aktuellen Locher-Skin zurück.
     * Der Skin existiert unter `src/main/java/de/wolc/gui/images/{skin-name-hier}.png`
     * @return Der Skin.
     */
    public LocherSkin getSkin() {
        return this.skin;
    }

    /**
     * Setzt den aktuelle Locher-Skin.
     * Der Skin existiert unter `src/main/java/de/wolc/gui/images/{skin-name-hier}.png`
     * @param skin Der Skin.
     */
    public void setSkin(LocherSkin skin) {
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
     * Setzt das aktuelle Format auf das der Locher eingestellt ist. Das Format kann nicht geändert werden wenn bereits 
     * ein Papier Stapel eingelegt ist.
     * @throws IllegalStateException Es ist bereits ein Stapel eingelegt.
     * @param format A4.class, A5.class, A6.class
     */
    public void setFormat(Class<? extends Papier> format) {
        if (this.stapel != null) {
            throw new IllegalStateException("Kann das Format nicht verstellt werden wenn bereits ein Stapel eingelegt ist.");
        }
        this.format = format;
    }

    /**
     * Legt einen Papier Stapel in den Locher ein, wenn der Locher auf das richtige Format eingestellt ist.
     * @throws IllegalArgumentException Der Stapel ist in einem falschen Format.
     * @throws IllegalStateException Es ist bereits ein Stapel eingelegt.
     * @param stapel Der Papier Stapel.
     */
    public void einlegen(PapierStapel<?> stapel) {
        if (stapel.getFormat() != this.getFormat()) {
            throw new IllegalArgumentException("Der Locher ist auf ein anderes Format eingestellt als der Stapel.");
        }
        if (this.stapel != null) {
            throw new IllegalStateException("Kann keinen neuen Stapel einlegen wenn bereits einer eingelegt ist.");
        }
        this.stapel = stapel;
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
     * @exception IllegalStateException Der Cooldown ist nicht bereit.
     * @return Alle Konfettis, die bei diesem Lochprozess entstanden sind.
     */
    public Lochprozess lochen() {
        if (this.getCooldown() > 0) {
            throw new IllegalStateException("Der Locher ist noch auf Cooldown.");
        }
        Lochprozess vorgang = new Lochprozess(this);
        // Kann nur lochen wenn wir einen Stapel haben und der Locher stark genug für diesen ist.
        if(this.stapel != null) {
            vorgang.setWarZuGross(this.stapel.groesse() > this.getStaerke());
            if(!vorgang.getWarZuGross()) {
                this.stapel.gelocht(vorgang);
            }
        }
        for (LocherUpgrade upgrade : this.upgrades) {
            upgrade.upgradeLochprozess(this, vorgang);
        }
        this.cooldown = vorgang.getCooldown();
        return vorgang;
    }

    /**
     * Muss regelmäßig von der GUI aufgerufen werden um der Logik ein "Zeitgefühl" zu verschaffen.
     * Wird automatisch von der "tick" Methode des Spielers aufgerufen. Sollte somit NICHT manuell aufgerufen werden!
     * @param deltaZeit Die vergangene Zeit in Sekunden. (Kann/sollte auch Kommazahl sein!)
     */
    public void tick(double deltaZeit) {
        this.cooldown = Math.max(this.cooldown - deltaZeit, 0);
    }
}
