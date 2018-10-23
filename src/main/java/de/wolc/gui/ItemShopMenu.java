package de.wolc.gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.*;

import java.util.HashMap;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.papier.Konfetti;
import javafx.event.*;

public class ItemShopMenu {
    private static final String TITLE = "World of Locher Craft - 💲 Pay2Win 💲";
    private static final String[] SKINS = {
        "locher_base",
        "locher_baumblaetter",
        "locher_beach",
        "locher_blue",
        "locher_coallblue",
        "locher_copper",
        "locher_deutschland",
        "locher_gold",
        "locher_green",
        "locher_grey",
        "locher_hflagge",
        "locher_kleeblatt",
        "locher_lightpurple",
        "locher_lila",
        "locher_love",
        "locher_matrix",
        "locher_microsoft",
        "locher_mylittlepony",
        "locher_pirate",
        "locher_spooky",
        "locher_storm",
        "locher_tank",
        "locher_unicorn"
    };

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

        settingsGridPane.add(backButton, 1, 0);
        settingsGridPane.add(continueButton, 2, 0);

        // ===============================================
        //  SKIN SHOP
        // ===============================================
        
        // Get Scene size and create a new Instance
        settingsPane.setLeft(this.scorescreen());
        settingsPane.setCenter(settingsGridPane);
        settingsPane.setBottom(this.skinshop());

        Scene sceneMainWindow = new Scene(settingsPane, 500, 250);

        // Updating the Title
        stage.setTitle(TITLE);

        this.scoreLabelsAktualisieren();

        return sceneMainWindow;
    }

    private Node scorescreen() {
        GridPane grid = new GridPane();
        grid.setHgap(1);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(grid);
        scroll.setPrefWidth(100d);
        Farbe[] farben = Farbe.values();
        this.scoreLabel = new Label();
        for(int i = 0; i < farben.length; i++) {
            Farbe farbe = farben[i];
            Label label = new Label();
            label.setTextFill(farbe.getGuiFarbe());
            this.scoreLabels.put(farbe, label);
            grid.add(label, 0, i + 1);
        }
        grid.add(this.scoreLabel, 0, 0);
        return scroll;
    }

    private Node skinshop() {
        GridPane grid = new GridPane();
        grid.setVgap(1);
        grid.setAlignment(Pos.CENTER);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(grid);
        scroll.setPrefHeight(100d);
        for(int i = 0; i< SKINS.length; i++) {
            String skin = SKINS[i];
            Image locher_skin = new Image("de/wolc/gui/images/" + skin + ".png");
            Rectangle locher_new = new Rectangle();
            locher_new.setOnMouseClicked((MouseEvent e) -> {
                this.spieler.getLocher().setSkin(skin);
                System.out.println("Neuer Skin gekauft: " + skin);
            });
            locher_new.setHeight(80d);
            locher_new.setWidth(locher_skin.getWidth() / (locher_skin.getHeight() / 80d));
            locher_new.setFill(new ImagePattern(locher_skin));
            grid.add(locher_new, i, 0);
        }
        return scroll;
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