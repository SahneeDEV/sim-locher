package de.wolc.gui;

import javafx.application.Application;

/**
 * Main Klasse 
 */
public class Gui
{
    private static Gui gui;
    private static String[] args;

    /**
     * Main Methode 
     */
    public static void main(String[] args) {
        Gui.args = args;
        Gui.gui = new Gui();
        Application.launch(Stages.class);
    }
}
