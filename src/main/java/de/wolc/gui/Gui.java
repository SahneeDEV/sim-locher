package de.wolc.gui;

import java.io.IOException;

import de.wolc.db.Datenbank;
import de.wolc.spiel.Farbe;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.papier.A5;
import de.wolc.spiel.papier.Konfetti;
import de.wolc.spiel.papier.PapierStapel;
import javafx.application.Application;

/**
 * Main Klasse. IDE Hinweise: - BlueJ verwendet statt dieser Klasse in "Stages"
 * Klasse und wählt "Run as JavaFX Application". Zum .jar erstellen MÜSSEN in
 * BlueJ die Sources und Package Dateien enthalten sein! - VS Code klickt auf
 * die "main(String[] args)" Methode in "Gui" und wählt "Debug".
 */
public class Gui {
    public static final Datenbank DB = new Datenbank();

    /**
     * Main Methode
     */
    public static void main(String[] args) {
        Application.launch(Stages.class);
    }
}
