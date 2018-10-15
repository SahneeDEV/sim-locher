package de.wolc.gui;

import javafx.application.Application;

/**
 * Main Klasse.
 * IDE Hinweise:
 *  - BlueJ verwendet statt dieser Klasse in "Stages" Klasse und wählt "Run as JavaFX Application".
 *    Zum .jar erstellen MÜSSEN in BlueJ die Sources und Package Dateien enthalten sein!
 *  - VS Code klickt auf die "main(String[] args)" Methode in "Gui" und wählt "Debug".
 */
public class Gui
{
    /**
     * Main Methode 
     */
    public static void main(String[] args) {
        Application.launch(Stages.class);
    }
}
