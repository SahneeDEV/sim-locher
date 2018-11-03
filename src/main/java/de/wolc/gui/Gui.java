package de.wolc.gui;

import de.wolc.Einstellungen;
import de.wolc.db.Datenbank;
import javafx.application.Application;

/**
 * Main Klasse. IDE Hinweise: - BlueJ verwendet statt dieser Klasse in "Stages"
 * Klasse und wählt "Run as JavaFX Application". Zum .jar erstellen MÜSSEN in
 * BlueJ die Sources und Package Dateien enthalten sein! - VS Code klickt auf
 * die "main(String[] args)" Methode in "Gui" und wählt "Debug".
 */
public class Gui {
    public static final Datenbank DB = new Datenbank();

    private static Einstellungen einstellungen;

    /**
     * Gibt die aktuellen Spieleinstellungen zurück.
     * @return Die Einstellungen.
     */
    public static Einstellungen getEinstellungen() {
        return Gui.einstellungen;
    }

    /**
     * Setzt die aktuellen Spieleinstellungen auf die neue Einstellungsinstanz.
     * ACHTUNG! Diese Methode wird NICHT verwendet um eine Einstellung zu ändern. Um dies zu tun sollte der Wert der
     * von "getEinstellungen()" zurückgegebenen Insanz angepasst werden, und dannach diese Instanz in die Datenbank
     * gespeichert werden(Key: "einstellungen").
     * @param einstellungen Die neue Einstellungsinstanz.
     */
    public static void setEinstellungen(Einstellungen einstellungen) {
        Gui.einstellungen = einstellungen;
    }

    /**
     * Main Methode
     */
    public static void main(String[] args) {
        Application.launch(Stages.class);
    }
}
