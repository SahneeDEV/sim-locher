import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.control.Labeled;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;



public class Stages extends Application
{
    //Variables
    private final String windowTitle = "World of Locher Craft";
    private final String iconLocation = "images/locher_base.png";
    
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle(windowTitle);        
        
        //stage decoration
        stage.getIcons().add(new Image(iconLocation));

        //Put everything together
        MainMenu mm = new MainMenu();

        stage.setScene(mm.MainMenuStage(stage));
        
        //Show the Stage
        stage.show();
    }
}