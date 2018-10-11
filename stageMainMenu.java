import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class stageMainMenu extends Application
{
    private final String windowTitle = "World of Locher Craft";
    
    
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle(windowTitle);
        
        //Create the Scene Content
        Group mainFrame = new Group();
        
        //Set Scene
        Scene sceneMainWindow = new Scene(mainFrame, 400, 400);
        
        //Put everything together
        stage.setScene(sceneMainWindow);
        
        //Show the Stage
        stage.show();
    }
    
}
