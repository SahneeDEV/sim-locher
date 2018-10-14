package de.wolc.gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.control.ProgressIndicator;

public class SettingsMenu{

    //variables
    private final String[] baseSettingNames = new String[] {"Very Low", "Low", "Medium", "High", "Ultra", "MSAA 1x", "MSAA 2x", "MSAA 4x", "FXAA 1x", "FXAA 2x", "FXAA 4x", "FXAA 8x", "FXAA 16x"};
    private final String windowTitle = "World of Locher Craft - Einstellungen";
    private final String creditsString = "Patrick Sachs, Jakob Heubl, Lukas Neubauer";


    public Scene SettingsScene(Stage stage){

        //BorderPane
        BorderPane settingsPane = new BorderPane();
        settingsPane.setMinHeight(100);

        //GridPane
        GridPane settingsGridPane = new GridPane();
        settingsGridPane.setHgap(10);
        settingsGridPane.setVgap(10);
        settingsGridPane.setAlignment(Pos.CENTER);
        settingsGridPane.setMinHeight(100);


        //Buttons
        Button backButton = new Button("Zur\u00fcck");
            backButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent){
                    MainMenu mm = new MainMenu();
                    stage.setScene(mm.MainMenuStage(stage));
                    stage.centerOnScreen();
                }
            });

        //Labels
        Label credits = new Label("Credits: " + creditsString);
        Label anisotropeFilterung = new Label("Anisotrope Filterung:");
        Label antiAliasing = new Label("Anti-Aliasing:");


        //ProgressIndicator
        final ProgressIndicator uploadingFiles = new ProgressIndicator();
        uploadingFiles.setVisible(false);


        //CheckBoxes
        CheckBox RTXSupport = new CheckBox("RTX Einschalten/Ausschalten");
            RTXSupport.setSelected(true);
            final CheckBox diagnoseDatenCheckBox = new CheckBox("Diagnosedaten an Entwickler senden");
            diagnoseDatenCheckBox.setSelected(true);
            diagnoseDatenCheckBox.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent){
                    uploadingFiles.setVisible(true);

                    diagnoseDatenCheckBox.setSelected(true);

                    uploadingFiles.setProgress(100);
                }
            });

        //ComboBox
        ComboBox<String> anisotropeFilterungComboBox = new ComboBox<String>();
        anisotropeFilterungComboBox.getItems().addAll(
            baseSettingNames[0],
            baseSettingNames[1],
            baseSettingNames[2],
            baseSettingNames[3],
            baseSettingNames[4]
        );
        anisotropeFilterungComboBox.setValue(baseSettingNames[4]);
            

        ComboBox<String> antiAliasingComboBox = new ComboBox<String>();
        antiAliasingComboBox.getItems().addAll(
            baseSettingNames[5],
            baseSettingNames[6],
            baseSettingNames[7],
            baseSettingNames[8],
            baseSettingNames[9],
            baseSettingNames[10],
            baseSettingNames[11],
            baseSettingNames[12]
        );
        antiAliasingComboBox.setValue(baseSettingNames[12]);        

        //Positioning
        settingsGridPane.add(anisotropeFilterung, 0, 0);
        settingsGridPane.add(anisotropeFilterungComboBox, 1, 0);
        settingsGridPane.add(antiAliasing, 0, 1);
        settingsGridPane.add(antiAliasingComboBox, 1, 1);
        settingsGridPane.add(diagnoseDatenCheckBox, 0, 2);
        settingsGridPane.add(uploadingFiles, 1 , 2);
        settingsGridPane.add(RTXSupport, 0, 3);
        settingsGridPane.add(credits, 0, 4);
        settingsGridPane.add(backButton,0,6);


        //Get Scene size and create a new Instance
        settingsPane.setCenter(settingsGridPane);

        Scene sceneMainWindow = new Scene(settingsPane, 500, 250);

        //Updating the Title
        stage.setTitle(windowTitle);

        return sceneMainWindow;
    }
}