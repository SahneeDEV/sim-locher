package de.wolc.spiel.locher;

import de.wolc.spiel.papier.A4;
import de.wolc.spiel.papier.Papier;
import de.wolc.spiel.papier.PapierStapel;
import de.wolc.spiel.locher.Lochprozess;

/**
 * Der eigentliche Locher. Er ist in der Lage Papier Stapel zu lochen.
 */
public class SimLocher
{
    private PapierStapel<?> stapel;
    private String skin;
    private Class<? extends Papier> format;
    private int stanzer;

    public SimLocher() {
        this.stanzer = 2;
        this.skin = "locher_base";
        this.format = A4.class;
    }

    /**
     * Gibt die aktuelle Anzahl der Stanzer zurück.
     * @return Die Stanzer.
     */
    public int getStanzer() {
        return stanzer;
    }

    /**
     * Gibt den Namen des aktuellen Locher-Skins zurück.
     * Der Skin existiert unter `src/main/java/de/wolc/gui/images/{skin-name-hier}.png`
     * @return Der Skin name.
     */
    public String getSkin() {
        return skin;
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
        return format;
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
        if(this.stapel != null) {
            // Erzeuge einen Lochprozess welcher dem Stapel übergeben wird.
            this.stapel.gelocht(vorgang);
        }
        return vorgang;
    }
}
