package de.wolc.gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class TutorialMenu {
    private final String windowTitle = "World of Locher Craft - Lochen for Dummies 🤤";

    public Scene TutorialScene(Stage stage) {
        final WebView webview = new WebView();
        webview.getEngine().load("https://www.youtube.com/embed/rNiJ4a7zft8?autoplay=1");
        webview.setPrefSize(640, 390);

        Scene sceneMainWindow = new Scene(webview);

        // Updating the Title
        stage.setTitle(windowTitle);
        stage.setOnCloseRequest(e -> {
            webview.getEngine().load(null);
        });

        return sceneMainWindow;
    }
}