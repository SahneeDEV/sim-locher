import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Main Klasse 
 */
public class Gui
{
    private static Gui gui;
    private static String[] args;

    /**
     * Main Methode 
     */
    public static void main(String[] args) {
        Gui.args = args;
        Gui.gui = new Gui();
        Application.launch(StageMainMenu.class);
    }
}
