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

public class Game{

    private final String windowTitle = "World of Locher Craft";

    public Scene GameMainStage(Stage stage){

        //Main Orientation Node
        BorderPane mainPane = new BorderPane();

        //Setting and creating the new Scene
        Scene gameScene = new Scene(mainPane);

        //Set Fullscreen
        stage.setFullScreen(true);

        //Creating the Component-nodes
        

        return gameScene;
    }
}