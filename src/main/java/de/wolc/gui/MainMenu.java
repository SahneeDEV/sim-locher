package de.wolc.gui;

import java.util.ArrayList;

import de.wolc.Einstellungen;
import de.wolc.MultiUse;
import de.wolc.gui.herausforderung.Herausforderung;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

public class MainMenu{

    private final String backgroundImageLocation = "de/wolc/gui/images/locher_animated.gif";
    private final String windowTitle = "World of Locher Craft - Hauptmen\u00fc";


    public Scene MainMenuStage(final Stage stage){

        //Set Window title
        stage.setTitle(windowTitle);

        Einstellungen einstellungen;
        try {
            einstellungen = (Einstellungen) Gui.DB.laden("einstellungen");
        } catch(Exception e) {
            Alert ladeFehler = new Alert(AlertType.ERROR);
            ladeFehler.setTitle("Fehler bei Einstellungen laden");
            ladeFehler.setHeaderText("Beim Laden der Einstellungen ist ein Fehler aufgetreten. Dies liegt " +
                "wahrscheinlich daran, dass eine neue Version des Spiels nicht mit der vorherigen kompatibel " +
                "ist.\nDie Einstellungen wurden deshalb auf Standard zurückgesetzt.");
            ladeFehler.setContentText(e.toString());
            ladeFehler.setResult(ButtonType.OK);
            ladeFehler.showAndWait();
            e.printStackTrace();

            einstellungen = null;
        }
        if (einstellungen == null) {
            einstellungen = new Einstellungen();
        }
        Gui.setEinstellungen(einstellungen);
        
        ArrayList<Herausforderung> herausforderungen;
        try {
            // TODO: "laden" generisch machen?
            herausforderungen = (ArrayList<Herausforderung>) Gui.DB.laden("herausforderungen");
        } catch(Exception e) {
            Alert ladeFehler = new Alert(AlertType.ERROR);
            ladeFehler.setTitle("Fehler bei Herausforderungen laden");
            ladeFehler.setHeaderText("Beim Laden der Herausforderungen ist ein Fehler aufgetreten. Dies liegt " +
                "wahrscheinlich daran, dass eine neue Version des Spiels nicht mit der vorherigen kompatibel " +
                "ist.\nAlle aktiven Herausforderungen wurde deshalb beendet.");
            ladeFehler.setContentText(e.toString());
            ladeFehler.setResult(ButtonType.OK);
            ladeFehler.showAndWait();
            e.printStackTrace();

            herausforderungen = null;
        }
        if (herausforderungen == null) {
            herausforderungen = new ArrayList<Herausforderung>();
        }
        Gui.setHerausforderungen(herausforderungen);

        //Generating and setting the Objects
        BorderPane mainPane = new BorderPane();

        //Get Scene size and create a new Instance
        MultiUse mU = new MultiUse();
        int[] screenSizes = mU.GetScreenSize();
        Scene sceneMainWindow = new Scene(mainPane, (int)(screenSizes[0] * 0.5), (int)(screenSizes[1] * 0.5));

        //Background setzen
        BackgroundImage backgroundImageMainMenu = new BackgroundImage(new Image(backgroundImageLocation), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(backgroundImageMainMenu));


        //Gruppe für die Buttons
        VBox buttonBox = new VBox();
        buttonBox.setPadding(new Insets(25,8,25,8));
        buttonBox.setSpacing(35);
        buttonBox.setPrefWidth(200);
        buttonBox.setPrefHeight(75);
        buttonBox.setAlignment(Pos.CENTER);

        //Buttons

        //Button1
        final Button button_playgame = new Button("▶ Spiel starten ▶");
        button_playgame.setMinWidth(buttonBox.getPrefWidth());
        button_playgame.setMinHeight(buttonBox.getPrefHeight());
        button_playgame.addEventHandler(ActionEvent.ACTION, actionEvent -> {
            Game g = new Game();               
            stage.setScene(g.GameMainStage(stage));
            stage.setFullScreen(Gui.getEinstellungen().isVollbild());
        });
        //Button2
        final Button button_settings = new Button("⚙ Einstellungen ⚙");
        button_settings.setMinWidth(buttonBox.getPrefWidth());
        button_settings.setMinHeight(buttonBox.getPrefHeight());
        button_settings.addEventHandler(ActionEvent.ACTION, actionEvent -> {
            SettingsMenu sm = new SettingsMenu();
            stage.setScene(sm.SettingsScene(stage));
            stage.centerOnScreen();
        });
        // Tutorial
        Button button_dummy = new Button("⁉ Spielanleitung ⁉");
        button_dummy.setMinWidth(buttonBox.getPrefWidth());
        button_dummy.setMinHeight(buttonBox.getPrefHeight());
        button_dummy.addEventHandler(ActionEvent.ACTION, actionEvent ->  {
            Stage stageTutorial = new Stage();
            TutorialMenu sm = new TutorialMenu();
            stageTutorial.setScene(sm.TutorialScene(stageTutorial));
            stageTutorial.centerOnScreen();
            stageTutorial.show();
        });
        // Beenden
        Button button_exit = new Button("☠ Beenden ☠");
        button_exit.setMinWidth(buttonBox.getPrefWidth());
        button_exit.setMinHeight(buttonBox.getPrefHeight());
        button_exit.addEventHandler(ActionEvent.ACTION, actionEvent ->  stage.close());

        //Adding the Buttons to the VBox and the VBox to the BorderPane
        buttonBox.getChildren().addAll(button_playgame, button_settings, button_dummy, button_exit);
        mainPane.setCenter(buttonBox);

        return sceneMainWindow;
    }

}