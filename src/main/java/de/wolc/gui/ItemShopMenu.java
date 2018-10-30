package de.wolc.gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.wolc.MultiUse;
import de.wolc.spiel.Farbe;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.locher.LocherSkin;
import de.wolc.spiel.papier.Konfetti;

public class ItemShopMenu {
    private static final String TITLE = "World of Locher Craft - 💲 Pay2Win 💲";

    private Spieler spieler;
    private Stage stage;

    private HashMap<Farbe, Label> scoreLabels = new HashMap<>();
    private Rectangle locherVorschau;
    private Label scoreLabel;

    public Scene ItemShopStage(Stage stage) {
        this.stage = stage;
        try {
            this.spieler = (Spieler) Gui.DB.laden("spieler");
        } catch (Exception e) {
            this.spieler = new Spieler();
            // TODO: Fehlermeldung ausgeben dass der Spieler nicht geladen werden konnte
            e.printStackTrace();
        }

        // BorderPane
        BorderPane settingsPane = new BorderPane();
        settingsPane.setMinHeight(100);

        // GridPane
        GridPane settingsGridPane = new GridPane();
        settingsGridPane.setHgap(1);
        settingsGridPane.setVgap(1);
        settingsGridPane.setMinHeight(100);

        // Buttons

        // ===============================================
        //  SKIN SHOP
        // ===============================================
        
        // Get Scene size and create a new Instance
        settingsPane.setTop(this.guiButtons());
        settingsPane.setLeft(this.guiScoreScreen());
        settingsPane.setRight(settingsGridPane);
        settingsPane.setCenter(this.guiLocherVorschau());
        settingsPane.setBottom(this.guiSkinShop());

        MultiUse mu = new MultiUse();
        int[] windowSize = mu.GetScreenSize();
        Scene sceneMainWindow = new Scene(settingsPane, windowSize[0] / 2d, windowSize[1] / 2d);

        // Updating the Title
        stage.setTitle(TITLE);

        this.scoreLabelsAktualisieren();
        this.locherVorschauAktualisieren();

        return sceneMainWindow;
    }

    private Node guiLocherVorschau() {
        this.locherVorschau = new Rectangle();
        return this.locherVorschau;
    }

    private Node guiButtons() {
        GridPane grid = new GridPane();
        grid.setVgap(1);
        Button backButton = new Button("◀ Zur\u00fcck ◀");
        backButton.addEventHandler(ActionEvent.ACTION, (ActionEvent actionEvent) -> {
            this.zurueck();
        });
        Button continueButton = new Button("▶ Weiterspielen ▶");
        continueButton.addEventHandler(ActionEvent.ACTION, (ActionEvent actionEvent) -> {
            this.weiterspielen();
        });
        grid.add(backButton, 0, 0);
        grid.add(continueButton, 1, 0);
        return grid;
    }

    private Node guiScoreScreen() {
        GridPane grid = new GridPane();
        grid.setHgap(1);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(grid);
        scroll.setPrefWidth(100d);
        Farbe[] farben = Farbe.values();
        this.scoreLabel = new Label();
        for(int i = 0; i < farben.length; i++) {
            Farbe farbe = farben[i];
            Label label = new Label();
            label.setTextFill(farbe.getGuiFarbe());
            this.scoreLabels.put(farbe, label);
            grid.add(label, 0, i + 1);
        }
        grid.add(this.scoreLabel, 0, 0);
        return scroll;
    }

    private Node guiSkinShop() {
        GridPane grid = new GridPane();
        grid.setVgap(1);
        grid.setAlignment(Pos.CENTER);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(grid);
        scroll.setPrefHeight(100d);
        LocherSkin[] skins = LocherSkin.values();
        for(int i = 0; i< skins.length; i++) {
            LocherSkin skin = skins[i];
            Image locher_skin = new Image("de/wolc/gui/images/" + skin.getGuiBild());
            Rectangle locher_new = new Rectangle();
            locher_new.setOnMouseClicked(e -> this.skinKaufen(skin));
            locher_new.setHeight(80d);
            locher_new.setWidth(locher_skin.getWidth() / (locher_skin.getHeight() / 80d));
            locher_new.setFill(new ImagePattern(locher_skin));
            grid.add(locher_new, i, 0);
        }
        return scroll;
    }

    private boolean skinKaufen(LocherSkin skin) {
        if (!this.kaufErbitten(skin.getGuiName(), skin.getKosten())) {
            System.out.println("Neuer Skin Kauf abgelehnt: " + skin);
            return false;
        }
        this.spieler.getLocher().setSkin(skin);
        System.out.println("Neuer Skin gekauft: " + skin);
        this.locherVorschauAktualisieren();
        return true;
    }

    private void locherVorschauAktualisieren() {
        Image locher_skin = new Image("de/wolc/gui/images/" + this.spieler.getLocher().getSkin().getGuiBild());
        locherVorschau.setHeight(locher_skin.getHeight());
        locherVorschau.setWidth(locher_skin.getWidth());
        this.locherVorschau.setFill(new ImagePattern(locher_skin));
    }

    private void konfettiAbziehen(Map<Farbe, Integer> kosten) {
        HashMap<Farbe, ArrayList<Konfetti>> hash = this.spieler.getKonfettiSortiert();
        for(Farbe farbe: kosten.keySet()) {
            ArrayList<Konfetti> liste = hash.get(farbe);
            for(int i = kosten.getOrDefault(farbe, 0); i > 0; i--) {
                Konfetti konfetti = liste.get(i - 1);
                this.spieler.getKonfetti().remove(konfetti);
            }
        }
    }

    private void scoreLabelsAktualisieren() {
        // Score einteilen nach Farbe
        HashMap<Farbe, Integer> hash = new HashMap<>();
        for(Konfetti konfetti : this.spieler.getKonfetti()) {
            Farbe farbe = konfetti.getFarbe();
            Integer zahl = hash.getOrDefault(farbe, 0) + 1;
            hash.put(farbe, zahl);
        }
        for(Farbe farbe : Farbe.values()) {
            Label label = this.scoreLabels.get(farbe);
            Integer zahl = hash.getOrDefault(farbe, 0);
            label.setText("  " + farbe.getAnzeigeName() + ": " + zahl);
        }
        this.scoreLabel.setText("🎉 Score: " + this.spieler.getKonfetti().size() + " 🎊");
    }

    private void speichern() {
        try {
            Gui.DB.speichern("spieler", this.spieler);
        } catch (Exception e) {
            this.spieler = new Spieler();
            // TODO: Fehlermeldung ausgeben dass der Spieler nicht gespeichert werden konnte
            e.printStackTrace();
        }
    }

    private boolean kannLeisten(Map<Farbe, Integer> kosten) {
        HashMap<Farbe, ArrayList<Konfetti>> hash = this.spieler.getKonfettiSortiert();
        for(Farbe farbe: kosten.keySet()) {
            ArrayList<Konfetti> liste = hash.get(farbe);
            if (liste == null || liste.size() < kosten.get(farbe)) {
                return false;
            }
        }
        return true;
    }

    private boolean kaufErbitten(String item, Map<Farbe, Integer> kosten) {
        if (!this.kannLeisten(kosten)) {
            Alert zuteuer = new Alert(AlertType.INFORMATION);
            zuteuer.setTitle("💲 Kauf fehlgeschlagen: " + item);
            zuteuer.setHeaderText("Du hast nicht genug Konfetti gesammelt um Dir \"" + item + "\" kaufen zu können." );
            zuteuer.setContentText("Kosten:\n" + kostenToString(kosten));
            zuteuer.setResult(ButtonType.OK);
            zuteuer.showAndWait();
            // todo: Anzeigen wieviel es kostet und wieviel man hat
            return false;
        }
        Alert frage = new Alert(AlertType.CONFIRMATION);
        frage.setTitle("💲 Kaufbestätigung: " + item);
        frage.setHeaderText("Möchtest Du \"" + item + "\" wirklich kaufen?");
        String kostenString = kostenToString(kosten);
        frage.setContentText(kostenString.equals("") ? "🤑 Kostenlos! 🤑" : kostenString);
        frage.getButtonTypes().clear();
        frage.getButtonTypes().add(ButtonType.YES);
        frage.getButtonTypes().add(ButtonType.NO);
        Optional<ButtonType> ergebnis = frage.showAndWait();
        if (!ergebnis.isPresent() || ergebnis.get() != ButtonType.YES) {
            return false;
        }
        this.konfettiAbziehen(kosten);
        this.scoreLabelsAktualisieren();
        return true;
    }

    private String kostenToString(Map<Farbe, Integer> kosten) {
        String kostenString = "";
        for(Farbe farbe: kosten.keySet()) {
            Integer zahl = kosten.get(farbe);
            kostenString += farbe.getAnzeigeName() + ": " + zahl + "\n";
        }
        return kostenString;
    }

    private void weiterspielen() {
        this.speichern();
        Game g = new Game();               
        stage.setScene(g.GameMainStage(stage));
        stage.setFullScreen(true);
    }

    private void zurueck() {
        this.speichern();
        MainMenu mm = new MainMenu();
        this.stage.setScene(mm.MainMenuStage(stage));
        this.stage.centerOnScreen();
    }
}