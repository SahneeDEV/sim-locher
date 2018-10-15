package de.wolc.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Stages extends Application
{
    //Variables
    private final String windowTitle = "World of Locher Craft";
    private final String iconLocation = "de/wolc/gui/images/locher_base.png";
    
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle(windowTitle);        
        
        //stage decoration
        stage.getIcons().add(new Image(iconLocation));

        //Put everything together
        MainMenu mm = new MainMenu();
        //SettingsMenu sm = new SettingsMenu();
        //Game g = new Game();

        stage.setScene(mm.MainMenuStage(stage));
        //stage.setScene(nm.SettingsScene(stage));
        //stage.setScene(g.GameMainStage(stage));
        
        
        //Show the Stage
        stage.show();
        stage.centerOnScreen();
    }
}