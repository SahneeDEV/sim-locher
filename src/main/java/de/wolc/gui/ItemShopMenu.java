package de.wolc.gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.geometry.*;

import com.sun.prism.paint.Color;

import de.wolc.spiel.Spieler;
import javafx.event.*;

public class ItemShopMenu {
    private static final String TITLE = "World of Locher Craft - 💲 Pay2Win 💲";

    private Spieler spieler;
    private Stage stage;

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
        settingsGridPane.setHgap(10);
        settingsGridPane.setVgap(10);
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
        Label credits = new Label("END SCREEN --- SCORE: " + this.spieler.getKonfetti().size());
        settingsGridPane.add(backButton, 0, 0);
        settingsGridPane.add(continueButton, 1, 0);
        settingsGridPane.add(credits, 0, 1);

        // Get Scene size and create a new Instance
        settingsPane.setCenter(settingsGridPane);

        Scene sceneMainWindow = new Scene(settingsPane, 500, 250);

        // Updating the Title
        stage.setTitle(TITLE);

        return sceneMainWindow;
    }

    private void weiterspielen() {
        Game g = new Game();               
        stage.setScene(g.GameMainStage(stage));
        stage.setFullScreen(true);
    }

    private void zurueck() {
        MainMenu mm = new MainMenu();
        this.stage.setScene(mm.MainMenuStage(stage));
        this.stage.centerOnScreen();
    }
}