package de.wolc.gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Callable;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.SchreibtischSkin;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.locher.LocherSkin;
import de.wolc.spiel.locher.upgrades.LocherUpgrade;
import de.wolc.spiel.locher.upgrades.UpgradeLocherAufSpeed;
import de.wolc.spiel.locher.upgrades.UpgradePanzerStanzer;
import de.wolc.spiel.locher.upgrades.UpgradeSpielZeit;
import de.wolc.spiel.locher.upgrades.UpgradeVampir;
import de.wolc.spiel.locher.upgrades.UpgradeWeissesLoch;
import de.wolc.spiel.papier.Konfetti;
import de.wolc.spiel.HintergrundMusik;

public class ItemShopMenu {
    private static final String TITLE = "World of Locher Craft - 💲 Pay2Win 💲";
    private static final Random ZUFALL = new Random();

    private Map<String, LocherUpgrade> upgradeCache = new HashMap<>();
    private Spieler spieler;
    private Stage stage;

    private HashMap<Farbe, Label> scoreLabels = new HashMap<>();
    private Rectangle locherVorschau;
    private Label scoreLabel;
    private BorderPane pane;
    private GridPane upgradeShopGrid;

    private Alert speichernFehler, ladeFehler;

    public Scene ItemShopStage(Stage stage) {
        this.stage = stage;

        try {
            this.spieler = (Spieler) Gui.DB.laden("spieler");
        } catch (Exception e) {
            this.spieler = new Spieler();
            ladeFehler = new Alert(AlertType.INFORMATION);
            ladeFehler.setTitle("Kein Speicherstand gefunden!");
            ladeFehler.setHeaderText("Es wurde kein Speicherstand gefunden oder es konnte kein Speicherstand geladen werden.");
            ladeFehler.setContentText(e.toString());
            ladeFehler.setResult(ButtonType.OK);
            ladeFehler.showAndWait();
            e.printStackTrace();
        }

        this.pane = new BorderPane();
        this.pane.setMinHeight(100);

        // ===============================================
        //  SKIN SHOP
        // ===============================================
        this.pane.setCenter(this.guiLocherVorschau());
        this.pane.setTop(this.guiButtons());
        this.pane.setLeft(this.guiUpgradeShop());
        this.pane.setRight(this.guiScoreScreen());
        this.pane.setBottom(this.guiSkinShop());
        //MultiUse mu = new MultiUse();
        //int[] windowSize = mu.GetScreenSize();
        //Scene sceneMainWindow = new Scene(pane, windowSize[0] / 2d, windowSize[1] / 2d);
        Scene sceneMainWindow = new Scene(pane);

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
        grid.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setVgap(1);
        Button backButton = new Button("◀ Zur\u00fcck ◀");
        backButton.addEventHandler(ActionEvent.ACTION, (ActionEvent actionEvent) -> {
            this.zurueck();
        });
        Button continueButton = new Button("▶ Weiterspielen ▶");
        continueButton.addEventHandler(ActionEvent.ACTION, (ActionEvent actionEvent) -> {
            this.weiterspielen();
        });
        Button leaderboardButton = new Button("🏅 Leaderboard 🏅");
        leaderboardButton.addEventHandler(ActionEvent.ACTION, (ActionEvent actionEvent) -> {
            this.leaderboard();
        });
        grid.add(backButton, 0, 0);
        grid.add(continueButton, 1, 0);
        grid.add(leaderboardButton, 4, 0);
        return grid;
    }

    private Node guiUpgradeShop() {
        this.upgradeShopGrid = new GridPane();
        this.upgradeShopGrid.setHgap(1);
        this.upgradeShopGrid.setVgap(2);
        { // Alle Upgrades
            Label label = new Label();
            label.setText("⚒ Upgrades 🔨");
            Button ansehen = new Button();
            ansehen.setText("Ansehen");
            this.upgradeShopGrid.add(label, 0, 0);
            this.upgradeShopGrid.add(ansehen, 2, 0);
            ansehen.setOnMouseClicked(e -> {
                this.upgradesZeigen("Alle Upgrades", this.spieler.getLocher().getUpgrades());
            });
        }
        guiUpgradeShop(UpgradePanzerStanzer.class, "Panzer Stanzer", () -> {
            UpgradePanzerStanzer upgrade = new UpgradePanzerStanzer(ZUFALL.nextInt(10) + 1);
            return upgrade;
        });
        guiUpgradeShop(UpgradeLocherAufSpeed.class, "Locher auf Speed", () -> {
            double min = Math.round(ZUFALL.nextDouble() * 10d) / 10d + 0.1d;
            double max = min + Math.round(ZUFALL.nextDouble() * 10d) / 10d;
            UpgradeLocherAufSpeed upgrade = new UpgradeLocherAufSpeed(min, max);
            return upgrade;
        });
        guiUpgradeShop(UpgradeWeissesLoch.class, "Weißes Loch", () -> {
            int min = ZUFALL.nextInt(5) + 1;
            int max = min + ZUFALL.nextInt(5);
            UpgradeWeissesLoch upgrade = new UpgradeWeissesLoch(min, max);
            return upgrade;
        });
        guiUpgradeShop(UpgradeVampir.class, "Vampir", () -> {
            UpgradeVampir upgrade = new UpgradeVampir(ZUFALL.nextInt(5) + 1);
            return upgrade;
        });
        guiUpgradeShop(UpgradeSpielZeit.class, "Upgrade Spiel Zeit", () -> {
            UpgradeSpielZeit upgrade = new UpgradeSpielZeit(ZUFALL.nextDouble()*10+0.5);
            return upgrade;
        });
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(this.upgradeShopGrid);
        return scroll;
    }

    private <T extends LocherUpgrade> void guiUpgradeShop(Class<T> typ, String name, Callable<T> factory) {
        upgradeCache.put(name, null);
        Label label = new Label();
        label.setText(name);
        Button kaufen = new Button();
        kaufen.setText("Kaufen");
        Button ansehen = new Button();
        ansehen.setText("Ansehen");
        upgradeShopGrid.add(label, 0, upgradeCache.size());
        upgradeShopGrid.add(kaufen, 1, upgradeCache.size());
        upgradeShopGrid.add(ansehen, 2, upgradeCache.size());
        ansehen.setOnMouseClicked(e -> {
            this.upgradesZeigen(label.getText(), updatesVonTyp(typ));
        });
        kaufen.setOnMouseClicked(e -> {
            try {
                LocherUpgrade upgrade = upgradeCache.get(name);
                if (upgrade == null) {
                    upgrade = factory.call();
                    upgradeCache.put(name, factory.call());
                }
                if (this.kaufErbitten(upgrade.toString(), upgrade.getPreis())) {
                    this.spieler.getLocher().getUpgrades().add(upgrade);
                    upgradeCache.put(name, null);
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        });
    }

    private Node guiScoreScreen() {
        GridPane grid = new GridPane();
        grid.setHgap(1);
        //ScrollPane scroll = new ScrollPane();
        //scroll.setContent(grid);
        //scroll.setPrefWidth(100d);
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
        return grid;
    }

    private Node guiSkinShop() {
        GridPane grid = new GridPane();
        grid.setVgap(1);
        grid.setAlignment(Pos.CENTER);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(grid);
        scroll.setPrefHeight(200d);
        LocherSkin[] locherSkins = LocherSkin.values();
        for(int i = 0; i< locherSkins.length; i++) {
            LocherSkin skin = locherSkins[i];
            Image img = new Image("de/wolc/gui/images/" + skin.getGuiBild());
            Rectangle rect = new Rectangle();
            rect.setOnMouseClicked(e -> this.skinKaufen(skin));
            rect.setHeight(80d);
            rect.setWidth(img.getWidth() / (img.getHeight() / 80d));
            rect.setFill(new ImagePattern(img));
            grid.add(rect, i, 0);
        }
        SchreibtischSkin[] schreibtischSkins = SchreibtischSkin.values();
        for(int i = 0; i< schreibtischSkins.length; i++) {
            SchreibtischSkin skin = schreibtischSkins[i];
            Image img = new Image("de/wolc/gui/images/" + skin.getGuiBild());
            Rectangle rect = new Rectangle();
            rect.setOnMouseClicked(e -> this.schreibtischKaufen(skin));
            rect.setHeight(80d);
            rect.setWidth(img.getWidth() / (img.getHeight() / 80d));
            rect.setFill(new ImagePattern(img));
            grid.add(rect, i, 1);
        }
        HintergrundMusik[] hintergrundMusiks = HintergrundMusik.values();
        for(int i = 0; i < hintergrundMusiks.length; i++){
            HintergrundMusik hintergrundMusik = hintergrundMusiks[i];
            Image img = new Image("de/wolc/gui/images/" + hintergrundMusik.getGuiBild());
            Rectangle rect = new Rectangle();
            rect.setOnMouseClicked(e -> this.hintergrundMusikKaufen(hintergrundMusik));
            rect.setHeight(80d);
            rect.setWidth(img.getWidth() / (img.getHeight() / 80d));
            rect.setFill(new ImagePattern(img));
            grid.add(rect , i, 2);
        }
        return scroll;
    }

    private <T extends LocherUpgrade> void upgradesZeigen(String name, List<T> list) {
        String upgrades;
        if (list.size() != 0) {
            upgrades = "";
            for(LocherUpgrade upgrade: list) {
                upgrades += upgrade.toString() + "\n";
            }
        } else {
            upgrades = "Keine Upgrades!";
        }
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle(name);
        info.setHeaderText("Upgrades die in diesem Spiel gekaufen wurden werden hier angezeigt.");
        info.setContentText(upgrades);
        info.setResult(ButtonType.OK);
        info.showAndWait();
    }

    private boolean schreibtischKaufen(SchreibtischSkin skin) {
        if (!this.kaufErbitten(skin.getGuiName(), skin.getPreis())) {
            System.out.println("Neuer Schreibtisch Skin Kauf abgelehnt: " + skin);
            return false;
        }
        this.spieler.setSchreibtischSkin(skin);
        System.out.println("Neuer Schreibtisch Skin gekauft: " + skin);
        this.locherVorschauAktualisieren();
        return true;
    }

    private boolean skinKaufen(LocherSkin skin) {
        if (!this.kaufErbitten(skin.getGuiName(), skin.getPreis())) {
            System.out.println("Neuer Skin Kauf abgelehnt: " + skin);
            return false;
        }
        this.spieler.getLocher().setSkin(skin);
        System.out.println("Neuer Skin gekauft: " + skin);
        this.locherVorschauAktualisieren();
        return true;
    }

        /**
     * Checks if you can buy another/a new hintergrundMusik
     * @param hintergrundMusik die neu zu kaufende hintergrundMusik
     * @return  true wenn ja, sonst false
     */
    //TODO: Funktion um die Musik für ein paar Sekunden preview zu hören
    private boolean hintergrundMusikKaufen(HintergrundMusik hintergrundMusik){
        if(!this.kaufErbitten(hintergrundMusik.getName(), hintergrundMusik.getPreis())){
            System.out.println("Neuer Hintergrundmusik Kauf abgelehnt: " + hintergrundMusik);
            return false;
        }
        this.spieler.setHintergrundMusik(hintergrundMusik);
        System.out.println("Neue Hintergrundmusik gekauft: " + hintergrundMusik);
        return true;
    }

    private void locherVorschauAktualisieren() {
        Image locher_skin = new Image("de/wolc/gui/images/" + this.spieler.getLocher().getSkin().getGuiBild());
        locherVorschau.setHeight(locher_skin.getHeight());
        locherVorschau.setWidth(locher_skin.getWidth());
        
        Image schreibtischSkin = new Image("de/wolc/gui/images/" + this.spieler.getSchreibtischSkin().getGuiBild());
        BackgroundImage schreibtischSkinImg = new BackgroundImage(schreibtischSkin, 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
        );
        this.pane.setBackground(new Background(schreibtischSkinImg));

        this.locherVorschau.setFill(new ImagePattern(locher_skin));
    }

    private void konfettiAbziehen(Preis preis) {
        HashMap<Farbe, ArrayList<Konfetti>> hash = this.spieler.getKonfettiSortiert();
        Map<Farbe, Integer> kosten = preis.getKosten();
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

    private void leaderboard() {
        Leaderboard[] top;
        try {
            top = Leaderboard.topScore(15);
        } catch (Exception e) {
            e.printStackTrace();
            top = new Leaderboard[0];
        }
        Alert zuteuer = new Alert(AlertType.INFORMATION);
        zuteuer.setTitle("🏅 Leaderboard 🏅");
        zuteuer.setHeaderText("Hier sind die besten Locherspieler aufgelistet.");
        String topString = "🏅 Top " + top.length + " 🏅\n";
        for(int i = 0; i < top.length; i++) {
            topString += "  " + (top[i].getName().equals(this.spieler.getName()) ? top[i] + " 🏁" : top[i]) + "\n";
        }
        topString += "🥈 Deine Punkte 🥈\n";
        topString += "  " + new Leaderboard(this.spieler.getName(), this.spieler.getKonfetti().size());
        zuteuer.setContentText(topString);
        zuteuer.setResult(ButtonType.OK);
        zuteuer.showAndWait();
    }

    private void speichern() {
        try {
            Leaderboard gesendet = Leaderboard.scoreSenden(this.spieler);
            this.spieler.setName(gesendet.getName());
            Gui.DB.speichern("spieler", this.spieler);
        } catch (Exception e) {
            this.spieler = new Spieler();
            speichernFehler = new Alert(AlertType.WARNING);
            speichernFehler.setTitle("Fehler beim Speichern deines Spielstands!");
            speichernFehler.setHeaderText("Beim Speichern deines Spielstands ist ein Fehler aufgetreten.");
            speichernFehler.setContentText(e.toString());
            speichernFehler.setResult(ButtonType.OK);
            speichernFehler.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Überprüft ob sich der Spieler etwas für diesen Preis leisten kann.
     * @param preis Der Preis.
     */
    private boolean kannLeisten(Preis preis) {
        HashMap<Farbe, ArrayList<Konfetti>> hash = this.spieler.getKonfettiSortiert();
        Map<Farbe, Integer> kosten = preis.getKosten();
        for(Farbe farbe: kosten.keySet()) {
            ArrayList<Konfetti> liste = hash.get(farbe);
            if (liste == null || liste.size() < kosten.get(farbe)) {
                return false;
            }
        }
        return true;
    }

    private boolean kaufErbitten(String item, Preis kosten) {
        if (!this.kannLeisten(kosten)) {
            Alert zuteuer = new Alert(AlertType.INFORMATION);
            zuteuer.setTitle("💲 Kauf fehlgeschlagen: " + item);
            zuteuer.setHeaderText("Du hast nicht genug Konfetti gesammelt um Dir \"" + item + "\" kaufen zu können." );
            zuteuer.setContentText("Kosten:\n" + kosten.toString());
            zuteuer.setResult(ButtonType.OK);
            zuteuer.showAndWait();
            //TODO: Anzeigen wieviel es kostet und wieviel man hat
            return false;
        }
        Alert frage = new Alert(AlertType.CONFIRMATION);
        frage.setTitle("💲 Kaufbestätigung: " + item);
        frage.setHeaderText("Möchtest Du \"" + item + "\" wirklich kaufen?");
        frage.setContentText(kosten.toString());
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

    /**
     * Gibt alle Upgrades dieses Upgrade Types zurück.
     * @param typ Der Upgrade Typ (z.B. PanzerAufSpeed.class)
     * @return Eine Liste mit allen Upgrades.
     */
    private <T extends LocherUpgrade> List<T> updatesVonTyp(Class<T> typ) {
        ArrayList<T> list = new ArrayList<T>();
        for(LocherUpgrade upgrade: this.spieler.getLocher().getUpgrades()) {
            if (typ.isInstance(upgrade)) {
                list.add(typ.cast(upgrade));
            }
        }
        return list;
    }

    private void weiterspielen() {
        this.speichern();
        Game g = new Game();               
        stage.setScene(g.GameMainStage(stage));
        stage.setFullScreen(Gui.getEinstellungen().isVollbild());
    }

    private void zurueck() {
        this.speichern();
        MainMenu mm = new MainMenu();
        this.stage.setScene(mm.MainMenuStage(stage));
        this.stage.centerOnScreen();
    }
}