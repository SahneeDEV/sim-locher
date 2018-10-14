package de.wolc.gui;

import javafx.application.Application;

/**
 * Main Klasse 
 */
public class Gui
{
    private static String[] args;

    /**
     * Gibt die Kommandozeilenargumente zurück mit denen die Anwendung gestartet wurde.
     * @return Die Argumente.
     */
    public static String[] getArgs() {
        return args;
    }

    /**
     * Main Methode 
     */
    public static void main(String[] args) {
        Gui.args = args;
        Application.launch(Stages.class);
    }
}
