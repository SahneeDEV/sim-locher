import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.control.Labeled;

public class SettingsMenu{

    //variables
    private final String[] baseSettingNames = new String[] {"Very Low", "Low", "Medium", "High", "Ultra", "MSAA 1x", "MSAA 2x", "MSAA 4x", "FXAA 1x", "FXAA 2x", "FXAA 4x", "FXAA 8x", "FXAA 16x"};
    private final String windowTitle = "World of Locher Craft - Einstellungen";

    public Scene SettingsScene(Stage stage){

        //GridPane
        GridPane settingsPane = new GridPane();
        settingsPane.setHgap(10);
        settingsPane.setVgap(10);

        //Labels
        Label credits = new Label("Credits:");
        Label anisotropeFilterung = new Label("Anisotrope Filterung:");
        Label antiAliasing = new Label("Anti-Aliasing:");

        //CheckBoxes
        CheckBox RTXSupport = new CheckBox("RTX Einschalten/Ausschalten");
            RTXSupport.setSelected(true);
        CheckBox diagnoseDatenCheckBox = new CheckBox("Diagnosedaten an Entwickler senden");
            diagnoseDatenCheckBox.setSelected(true);
            diagnoseDatenCheckBox.setDisable(true);

        //ComboBox
        ComboBox<Label> anisotropeFilterungComboBox = new ComboBox<Label>();
            anisotropeFilterungComboBox.getItems().addAll(
                new Label(baseSettingNames[0]),
                new Label(baseSettingNames[1]),
                new Label(baseSettingNames[2]),
                new Label(baseSettingNames[3]),
                new Label(baseSettingNames[4])
            );
            anisotropeFilterungComboBox.setStyle(" -fx-text-fill: black");
            

        ComboBox<Label> antiAliasingComboBox = new ComboBox<Label>();
            antiAliasingComboBox.getItems().addAll(
                new Label(baseSettingNames[5]),
                new Label(baseSettingNames[6]),
                new Label(baseSettingNames[7]),
                new Label(baseSettingNames[8]),
                new Label(baseSettingNames[9]),
                new Label(baseSettingNames[10]),
                new Label(baseSettingNames[11]),
                new Label(baseSettingNames[12])
            );

        //Positioning
        settingsPane.add(anisotropeFilterung, 0, 0);
        settingsPane.add(anisotropeFilterungComboBox, 1, 0);
        settingsPane.add(antiAliasing, 0, 1);
        settingsPane.add(antiAliasingComboBox, 1, 1);
        settingsPane.add(diagnoseDatenCheckBox, 0, 2);
        settingsPane.add(RTXSupport, 0, 3);


        //Get Scene size and create a new Instance
        MultiUse mU = new MultiUse();
        int[] screenSizes = mU.GetScreenSize();
        Scene sceneMainWindow = new Scene(settingsPane, (int)(screenSizes[0] * 0.5), (int)(screenSizes[1] * 0.5));


        return sceneMainWindow;
    }
}