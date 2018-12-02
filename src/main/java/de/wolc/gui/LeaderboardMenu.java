package de.wolc.gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class LeaderboardMenu {
    private final String windowTitle = "LEADERBOARD von World of Lochercraft";

    public Scene LeaderboardScene(Stage stage) {
        final WebView webview = new WebView();
        webview.getEngine().load("https://sim-locher.herokuapp.com/?nobutton");
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