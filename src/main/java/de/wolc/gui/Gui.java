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
    /**
     * Main Methode
     */
    public static void main(String[] args) {
        dbTest();
        Application.launch(Stages.class);
    }

    private static void dbTest() {
        Spieler spieler = new Spieler();
        spieler.getKonfetti().add(new Konfetti(Farbe.BLAU));
        spieler.getLocher().setFormat(A5.class);
        PapierStapel<A5> stapel = new PapierStapel<>(A5.class);
        stapel.ablegen(new A5());
        stapel.ablegen(new A5());
        spieler.getLocher().einlegen(stapel);

        Datenbank db = new Datenbank();
        try {
            db.speichern("test", spieler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Spieler load = (Spieler) db.laden("test");
            System.out.println("DB Test fertig ... " + load);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
