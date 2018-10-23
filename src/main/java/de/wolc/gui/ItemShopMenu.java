package de.wolc.gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.geometry.*;

import java.util.HashMap;

import com.sun.prism.paint.Color;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.papier.Konfetti;
import javafx.event.*;

public class ItemShopMenu {
    private static final String TITLE = "World of Locher Craft - 💲 Pay2Win 💲";

    private Spieler spieler;
    private Stage stage;

    private HashMap<Farbe, Label> scoreLabels = new HashMap<>();
    private Label scoreLabel;

    public Scene ItemShopStage(Stage stage) {
        this.stage = stage;
        try {
            this.spieler = (Spieler) Gui.DB.laden("spieler");
        } catch (Exception e) {
            this.spieler = new Spieler();
            // TODO: Fehlermeldung ausgeben dass der Spieler nicht geladen werden konnte
            e.printStackTrace();
        }

        // BorderPane
        BorderPane settingsPane = new BorderPane();
        settingsPane.setMinHeight(100);

        // GridPane
        GridPane settingsGridPane = new GridPane();
        settingsGridPane.setHgap(1);
        settingsGridPane.setVgap(1);
        settingsGridPane.setMinHeight(100);

        // Buttons
        Button backButton = new Button("Zur\u00fcck");
        backButton.addEventHandler(ActionEvent.ACTION, (ActionEvent actionEvent) -> {
            this.zurueck();
        });
        Button continueButton = new Button("Weiterspielen");
        continueButton.addEventHandler(ActionEvent.ACTION, (ActionEvent actionEvent) -> {
            this.weiterspielen();
        });

        // Labels
        Farbe[] farben = Farbe.values();
        this.scoreLabel = new Label();
        for(int i = 0; i < farben.length; i++) {
            Farbe farbe = farben[i];
            Label label = new Label();
            label.setTextFill(farbe.getGuiFarbe());
            this.scoreLabels.put(farbe, label);
            settingsGridPane.add(label, 0, i + 2);
        }

        settingsGridPane.add(backButton, 1, 0);
        settingsGridPane.add(continueButton, 2, 0);

        settingsGridPane.add(this.scoreLabel, 0, 1);
        
        // Get Scene size and create a new Instance
        settingsPane.setCenter(settingsGridPane);

        Scene sceneMainWindow = new Scene(settingsPane, 500, 250);

        // Updating the Title
        stage.setTitle(TITLE);

        this.scoreLabelsAktualisieren();

        return sceneMainWindow;
    }

    private void scoreLabelsAktualisieren() {
        // Score einteilen nach Farbe
        HashMap<Farbe, Integer> hash = new HashMap<>();
        for(Konfetti konfetti : this.spieler.getKonfetti()) {
            Farbe farbe = konfetti.getFarbe();
            Integer zahl = hash.getOrDefault(farbe, 0) + 1;
            hash.put(farbe, zahl);
        }
        for(Farbe farbe : Farbe.values()) {
            Label label = this.scoreLabels.get(farbe);
            Integer zahl = hash.getOrDefault(farbe, 0);
            label.setText("  " + farbe.getAnzeigeName() + ": " + zahl);
        }
        this.scoreLabel.setText("Score: " + this.spieler.getKonfetti().size());
    }

    private void speichern() {
        try {
            Gui.DB.speichern("spieler", this.spieler);
        } catch (Exception e) {
            this.spieler = new Spieler();
            // TODO: Fehlermeldung ausgeben dass der Spieler nicht gespeichert werden konnte
            e.printStackTrace();
        }
    }

    private void weiterspielen() {
        this.speichern();
        Game g = new Game();               
        stage.setScene(g.GameMainStage(stage));
        stage.setFullScreen(true);
    }

    private void zurueck() {
        this.speichern();
        MainMenu mm = new MainMenu();
        this.stage.setScene(mm.MainMenuStage(stage));
        this.stage.centerOnScreen();
    }
}