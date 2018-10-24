package de.wolc.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Random;

import de.wolc.MultiUse;
import de.wolc.gui.PapierObjekt;
import de.wolc.spiel.Farbe;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.papier.A4;
import de.wolc.spiel.papier.A5;
import de.wolc.spiel.papier.A6;
import de.wolc.spiel.papier.Konfetti;
import de.wolc.spiel.papier.Papier;
import de.wolc.spiel.papier.PapierStapel;
import de.wolc.gui.LocherPapier;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.paint.ImagePattern;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Game{

    private final String windowTitle = "World of Locher Craft";
    private final String backgroundImageLocation = "de/wolc/gui/images/Test_Bild.jpg";
    private Spieler spieler;
    private Rectangle locher_new;
    private AnchorPane gameArea;   
    private Stage stage;
    private AnimationTimer timer;
    
    private static final Random RANDOM = new Random();
    
    //Game Variables
    private double remainingTimeAvailable = 30d;

    //Variables for Countdown timer
    private long firstNanoTimeTimer = 0;
    private double timeToNextPapier = 0;

    //Papierstapel erstellen
    private PapierStapel<A4> stapel_A4;
    private PapierStapel<A5> stapel_A5;
    private PapierStapel<A6> stapel_A6;

    //Diverse Nodes
    private Label score, remainingTime, formatLabel, papierLabel, locherCooldown;
    private ToggleButton formatA4Button, formatA5Button, formatA6Button;
    private ToggleGroup formatGroup;
    private HashMap<Farbe, Label> scoreLabels = new HashMap<>();
    private Alert speichernFehler, ladeFehler;

    //SaveGame Toggle Buttons
    private Boolean loadedSaveGame = false;

    private ArrayList<LocherPapier> locherPapier= new ArrayList<LocherPapier>();

    public Game () {
        try {
            this.spieler = (Spieler) Gui.DB.laden("spieler");
            loadedSaveGame = true;
        } catch (Exception e) {
            ladeFehler = new Alert(AlertType.INFORMATION);
            ladeFehler.setTitle("Kein Speicherstand gefunden!");
            ladeFehler.setHeaderText("Es wurde kein Speicherstand gefunden oder es konnte kein Speicherstand geladen werden.");
            ladeFehler.setContentText(e.toString());
            ladeFehler.setResult(ButtonType.OK);
            ladeFehler.showAndWait();
            e.printStackTrace();

            this.spieler = null;
        }
        if (this.spieler == null) {
            this.spieler = new Spieler();
        }
        this.firstNanoTimeTimer = 0;
    }

    private void updateLabels() {
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
        this.score.setText("Score: " + this.spieler.getKonfetti().size());
        // Sonstige Stats
        this.formatLabel.setText("Format: " + this.spieler.getLocher().getFormat().getSimpleName());
        this.papierLabel.setText("Stapel: " + this.spieler.getLocher().getStapel().groesse());
        this.locherCooldown.setText("Cooldown: " + Math.round(spieler.getLocher().getCooldown() * 10) / 10 + "s");
        remainingTime.setText("Zeit: " + Math.round(this.remainingTimeAvailable * 10d) / 10d + "s");   
    }
    
    public Scene GameMainStage(Stage stage){
        this.stage = stage;

        //Main Orientation Node and initale settings
        BorderPane mainPane = new BorderPane();

        //Setting and creating the new Scene
        Scene gameScene = new Scene(mainPane);

        //Set Fullscreen
        //TODO: wenn man den Fullscreen verlässt skalieren die Nodes nicht mehr nach bzw. ändern Ihre Position nicht erneut
        stage.setFullScreen(true);

        //Setting Background and width and height to screen
        MultiUse mu = new MultiUse();
        int[] windowSize = mu.GetScreenSize();
        Image backgroundImage = new Image(backgroundImageLocation, (double)windowSize[0], (double)windowSize[1], false, false);

        BackgroundImage backgroundImageGame = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(backgroundImageGame));

        //Papierstapel creation
        stapel_A4 = new PapierStapel<>(A4.class);
        stapel_A5 = new PapierStapel<>(A5.class);
        stapel_A6 = new PapierStapel<>(A6.class);
        if(this.spieler.getLocher().getFormat() == null){
            this.spieler.getLocher().setFormat(A4.class);
            this.spieler.getLocher().einlegen(stapel_A4);
        }
        else{
            this.spieler.getLocher().setFormat(this.spieler.getLocher().getFormat());
            if(this.spieler.getLocher().getFormat() == A4.class){
                this.spieler.getLocher().einlegen(stapel_A4);
            }
            else if(this.spieler.getLocher().getFormat() == A5.class){
                this.spieler.getLocher().einlegen(stapel_A5);
            }
            else if(this.spieler.getLocher().getFormat() == A6.class){
                this.spieler.getLocher().einlegen(stapel_A6);
            }
        }
        
        
        //Creating the Component-nodes
        //Creating the VBox for the right Output
        VBox rightVBox = new VBox();

        // Labels erstellen
        score = new Label();
        papierLabel = new Label();
        formatLabel = new Label();
        remainingTime = new Label();
        locherCooldown = new Label();
        locherCooldown.setTextFill(Color.WHITE);
        papierLabel.setTextFill(Color.WHITE);
        score.setTextFill(Color.WHITE);
        remainingTime.setTextFill(Color.WHITE);
        formatLabel.setTextFill(Color.WHITE);
        rightVBox.getChildren().addAll(remainingTime, score);
        for(Farbe farbe : Farbe.values()) {
            Label label = new Label();
            label.setTextFill(farbe.getGuiFarbe());
            this.scoreLabels.put(farbe, label);
            rightVBox.getChildren().add(label);
        }
        rightVBox.getChildren().addAll(locherCooldown, formatLabel, papierLabel);
        this.updateLabels();

        //Adding the Format ToggleButtons + ToggleGroup + default ToggleButton configuration
        HBox formatBox = new HBox();
        formatGroup = new ToggleGroup();
        formatBox.setPadding(new Insets(20, 5 , 20 ,5));

        formatA4Button = new ToggleButton("A4");
        formatA4Button.setSelected(true);
        formatA4Button.setToggleGroup(formatGroup);
        formatA4Button.setOnMousePressed((MouseEvent e) -> {
            papierWechsel(stapel_A4, A4.class);
            locherPapierEntfernen();
        });

        formatA5Button = new ToggleButton("A5");
        formatA5Button.setSelected(false);
        formatA5Button.setToggleGroup(formatGroup);
        formatA5Button.setOnMousePressed((MouseEvent e) -> {
            papierWechsel(stapel_A5, A5.class);
            locherPapierEntfernen();
        });

        formatA6Button = new ToggleButton("A6");
        formatA6Button.setSelected(false);
        formatA6Button.setToggleGroup(formatGroup);
        formatA6Button.setOnMousePressed((MouseEvent e) -> {
            papierWechsel(stapel_A6, A6.class);
            locherPapierEntfernen();
        });

        formatBox.getChildren().addAll(formatA4Button, formatA5Button, formatA6Button);
        mainPane.setLeft(formatBox);
        BorderPane.setAlignment(formatBox, Pos.CENTER_LEFT);

        //If a SaveGame has been loaded the ToggleButtons get adjusted here
        if(loadedSaveGame){
            //A4
            if(this.spieler.getLocher().getFormat() == A4.class){
                this.formatA4Button.setSelected(true);
                this.formatA5Button.setSelected(false);
                this.formatA6Button.setSelected(false);
            }
            //A5
            else if(this.spieler.getLocher().getFormat() == A5.class){
                this.formatA4Button.setSelected(false);
                this.formatA5Button.setSelected(true);
                this.formatA6Button.setSelected(false);
            }
            //A6
            else if(this.spieler.getLocher().getFormat() == A6.class){
                this.formatA4Button.setSelected(false);
                this.formatA5Button.setSelected(false);
                this.formatA6Button.setSelected(true);
            }
        }

        //Adding remainingTime and score to VBox 

        //Adding VBox to mainPane
        mainPane.setRight(rightVBox);
        BorderPane.setAlignment(rightVBox, Pos.CENTER);
        
        //Spawn the paper
        gameArea = new AnchorPane();
        //Setting height of paperPane
        gameArea.setMinWidth(((double)windowSize[0] * 0.8));
        gameArea.setMinHeight(((double)windowSize[1] * 0.8));

        //LOCHER
        String skin = spieler.getLocher().getSkin();

        Image locher_skin = new Image("de/wolc/gui/images/" + skin + ".png");
        locher_new = new Rectangle();
        locher_new.setHeight(locher_skin.getHeight());
        locher_new.setWidth(locher_skin.getWidth());
        locher_new.setFill(new ImagePattern(locher_skin));

        //Setting the default Anchor points for the paper
        AnchorPane.setBottomAnchor(locher_new, stage.getWidth() * 0.20);
        AnchorPane.setLeftAnchor(locher_new, stage.getHeight() * 0.65);

        //Locher_new Mouse Events
        locher_new.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //Abgleichen des gedrückten Buttons und des Cooldowns
                if (e.getButton() == MouseButton.PRIMARY && spieler.getLocher().getCooldown() == 0) {
                    Lochprozess prozess = spieler.getLocher().lochen();
                    ArrayList<Konfetti> spielerKonfetti = spieler.getKonfetti();
                    spielerKonfetti.addAll(prozess.getKonfetti());
                }
                //Abgleichen des gedrückten Buttons
                if ( e.getButton() == MouseButton.SECONDARY) {
                    PapierStapel<?> stapel = spieler.getLocher().getStapel();
                    Papier removedPapier = stapel.entnehmen();

                    //entfernen des Eingelgeten Bilds wenn kein Papier mehr im Locher
                    if (removedPapier != null) {
                        new PapierObjekt(Game.this, removedPapier);
                        for (int i = 0; i <= locherPapier.size(); i++) {
                            LocherPapier todeltetPapier = locherPapier.get(i);
                            if (todeltetPapier.getPapier() == removedPapier){
                                ArrayList<Rectangle> todeletRectangle = todeltetPapier.getPapierListe();
                                locherPapier.remove(i);
                                for (int a = 0; a <= todeletRectangle.size(); a++) {
                                    Rectangle deletLocherPapier = todeletRectangle.get(a);
                                    gameArea.getChildren().remove(deletLocherPapier);
                                }
                                break;
                            }
                        }
                    } 
                }
            }
        });


        //creating a new Animation Timer for refreshing the GUI
        this.timer = new AnimationTimer(){
            public void handle(long currentNanoTime){
                if (firstNanoTimeTimer == 0) {
                    firstNanoTimeTimer = currentNanoTime;
                }
                //Getting new and last TimeStamp in Miliseconds and calculating 
                long elapsedNanoSeconds = currentNanoTime - firstNanoTimeTimer;
                double elapsedSeconds = ((elapsedNanoSeconds / 1000000000d));
                firstNanoTimeTimer = currentNanoTime;

                //Triggering Cooldown and giving him the elapsedSeconds
                spieler.tick(elapsedSeconds);

                timeToNextPapier -= elapsedSeconds;
                remainingTimeAvailable -= elapsedSeconds;

                if (timeToNextPapier <= 0) {
                    spawnPapier();
                    timeToNextPapier = 0.5d + (2d - 0.5d) * RANDOM.nextDouble();
                }

                //Check for end of Time
                if(remainingTimeAvailable <= 0) {
                    Game.this.spielEnde();
                }

                Game.this.updateLabels();

                PapierStapel<?> stapel = spieler.getLocher().getStapel();
                if (stapel.groesse() == 0) {
                    
                }
            }
        };


        //Add Nodes to the AnchorPane
        gameArea.getChildren().add(locher_new);

        //Add the elements to the Main Pane
        mainPane.setCenter(gameArea);

        //Set Window Titel
        stage.setTitle(windowTitle);

        this.timer.start();

        return gameScene;
    }

    public void spielEnde() {
        this.timer.stop();
        try {
            Gui.DB.speichern("spieler", this.spieler);
        } catch (IOException e) {
            speichernFehler = new Alert(AlertType.WARNING);
            speichernFehler.setTitle("Fehler beim Speichern deines Spielstands!");
            speichernFehler.setHeaderText("Beim Speichern deines Spielstands ist ein Fehler aufgetreten.");
            speichernFehler.setContentText(e.toString());
            speichernFehler.setResult(ButtonType.OK);
            speichernFehler.showAndWait();
            e.printStackTrace();
		}
        ItemShopMenu menu = new ItemShopMenu();
        stage.setScene(menu.ItemShopStage(this.stage));
    }

    public void spawnPapier() {
        int format = RANDOM.nextInt(3);
        Papier papier;
        if(format == 0) { papier = new A4(); }
        else if(format == 1) { papier = new A5(); }
        else { papier = new A6(); }
        papier.setFarbe(Farbe.zufallsfarbe());
        new PapierObjekt(Game.this, papier);
    }

    /**
     * Prüft, ob sich die beiden übergebenen Shapes überschneiden, wenn ja, dann wird die Anzeige angepasst
     * @return true=collision vorhanden, false= keine collision vorhanden
     */
    private boolean checkForCollision(Shape shape1, Shape shape2){
        Shape ueberschneidung = Shape.intersect(shape1, shape2);
        return !ueberschneidung.getBoundsInLocal().isEmpty();
    }

    /**
     * Gibt das aktuell instalzierte AnchorPane zurück
     * @return Das aktuelle 'gameArea' AnchorPane
     */
    public AnchorPane getArea() {
        return this.gameArea;
    }

    public boolean checkForLocherCollision(Shape papier) {
        return checkForCollision(papier, locher_new);
    }

    public Spieler getCurrentSpieler(){
        return this.spieler;
    }

    @SuppressWarnings("unchecked")
    public void papierAufLocherGezogen(PapierObjekt objekt) {
        // penis 🍆
        Class<? extends Papier> papierTyp = objekt.getPapier().getClass();
        if (this.spieler.getLocher().getFormat() == papierTyp) {
            PapierStapel<?> stapel = this.spieler.getLocher().getStapel();
            boolean abgelegt;
            if(papierTyp == A4.class){
                PapierStapel<A4> stapel_A4 = (PapierStapel<A4>)stapel;
                abgelegt = stapel_A4.ablegen((A4)objekt.getPapier());
            }
            else if(papierTyp == A5.class){
                PapierStapel<A5> stapel_A5 = (PapierStapel<A5>)stapel;
                abgelegt = stapel_A5.ablegen((A5)objekt.getPapier());
            }
            else if(papierTyp == A6.class){
                PapierStapel<A6> stapel_A6 = (PapierStapel<A6>)stapel;
                abgelegt = stapel_A6.ablegen((A6)objekt.getPapier());
            } else {
                abgelegt = false;
            }
            if (abgelegt) {
                locherPapier.add(new LocherPapier(Game.this, spieler.getLocher().getStapel().groesse(), locher_new, objekt.getPapier()));
                objekt.zerstoeren();
            }
        }
    }

    /**
     * Wechselt das Papier anhand des ausgewählten neuen Formats
     * @param papierStapel - der neue Papierstapel des Formats
     * @param neuesPapierformat - das neue Format mit <Format>.class
     */
    private <T extends Papier> void papierWechsel(PapierStapel<T> papierStapel, Class<T> neuesPapierformat){
        // Alten Stapel rausnehmen
        PapierStapel<?> alterStapel = this.spieler.getLocher().entnehmen();
        if (alterStapel != null) {
            while(alterStapel.groesse() > 0) {
                alterStapel.entnehmen();
            }
        }
        // Neue Auswahl setzen
        this.spieler.getLocher().setFormat(neuesPapierformat);
        this.spieler.getLocher().einlegen(papierStapel);
    }

    /**
     * Entfernt das aktuell in den Locher eingelegte Papier
     */
    private void locherPapierEntfernen(){
        //Entfernen aller Papiere im Locher
        for(int i = 0; i <= locherPapier.size(); i++){
            LocherPapier papierEntfernen = locherPapier.get(i);
            ArrayList<Rectangle> papiereInDerListe = papierEntfernen.getPapierListe();
            for(int g = 0; g <= papiereInDerListe.size(); g++){
                Rectangle zuEntfernendesPapier = papiereInDerListe.get(g);
                this.gameArea.getChildren().remove(zuEntfernendesPapier);
            }
        }

    }

}