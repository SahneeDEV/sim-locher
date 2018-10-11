import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

public class StageMainMenu extends Application
{
    //Variables
    private final String windowTitle = "World of Locher Craft";
    
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle(windowTitle);
        
        //Create the Scene Content
        Group mainFrame = new Group();
        
        //Get Scene size and create a new Instance
        int[] screenSizes = GetScreenSize();
        Scene sceneMainWindow = new Scene(mainFrame, (int)(screenSizes[0] * 0.5), (int)(screenSizes[1] * 0.5));
        
        
        
        //Generating and adding the 
        
        //Put everything together
        stage.setScene(sceneMainWindow);
        
        //Show the Stage
        stage.show();
    }
    
    //Konstruktor
    public void StageMainMenu(){
        Application.launch();
    }
    
    //To get the Screen Size
    //Returns int[width_screen1, height_screen1, width_screen2, height_screen2,...]
    private int[] GetScreenSize(){
        //Define 
        GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphDev = graphEnv.getScreenDevices();
        
        //Variables
        int[] screenSizes = new int[(graphDev.length + 2)];
        int count = 0;
        
        for(int i = 0; i < graphDev.length; i++){
            screenSizes[count] = graphDev[i].getDisplayMode().getWidth();
            screenSizes[count + 1] = graphDev[i].getDisplayMode().getHeight();
            count = count + 2;
        }
        
        for(int i: screenSizes){
            System.out.println(i);
        }
        
        return screenSizes;
    }
    
}
