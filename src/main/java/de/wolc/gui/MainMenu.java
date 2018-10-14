package de.wolc.gui;

import de.wolc.MultiUse;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.event.*;

public class MainMenu{

    private final String backgroundImageLocation = "images/locher_animated.gif";
    private final String windowTitle = "World of Locher Craft - Hauptmen\u00fc";


    public Scene MainMenuStage(final Stage stage){

        //Set Window title
        stage.setTitle(windowTitle);

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
        final Button button_playgame = new Button("Play_Game()");
            button_playgame.setMinWidth(buttonBox.getPrefWidth());
            button_playgame.setMinHeight(buttonBox.getPrefHeight());
            

            //Set a event for the Button
            button_playgame.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent){
                    button_playgame.setText("NO GAME!");
                }
    
            });

        //Button2
        final Button button_settings = new Button("Settings()");
            button_settings.setMinWidth(buttonBox.getPrefWidth());
            button_settings.setMinHeight(buttonBox.getPrefHeight());

            //Set a event for the Button
            button_settings.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent){
                    SettingsMenu sm = new SettingsMenu();
                    stage.setScene(sm.SettingsScene(stage));
                    stage.centerOnScreen();
                }
    
            });
        //Button3
        Button button_exit = new Button("Exit()");
            button_exit.setMinWidth(buttonBox.getPrefWidth());
            button_exit.setMinHeight(buttonBox.getPrefHeight());

            //Set a event for the Button
            button_exit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent){
                    stage.close();
                }
    
            });

        //Adding the Buttons to the VBox and the VBox to the BorderPane
        buttonBox.getChildren().addAll(button_playgame, button_settings, button_exit);
        mainPane.setCenter(buttonBox);


        return sceneMainWindow;
    }

}