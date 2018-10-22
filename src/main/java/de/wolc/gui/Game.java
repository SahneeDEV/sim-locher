package de.wolc.gui;

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
//TODO: '*' entfernen und nur die benutzen objekte importieren
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.paint.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.shape.*;

public class Game{

    private final String windowTitle = "World of Locher Craft";
    private final String backgroundImageLocation = "de/wolc/gui/images/Test_Bild.jpg";
    private Spieler spieler;
    private Class<? extends Papier> currentPapierFormat;
    private Rectangle locher_new;
    private AnchorPane gameArea;
    
    private static final Random RANDOM = new Random();
    
    //Game Variables
    private double remainingTimeAvailable = 30d;
    private boolean hasLoched = false;

    //Variables for Countdown timer
    private long firstNanoTimeTimer = 0;
    private String leadingZero;

    private double timeToNextPapier = 0;

    private Label score, remainingTime, formatLabel, papierLabel;
    private HashMap<Farbe, Label> scoreLabels = new HashMap<>();

    public Game () {
       this.spieler = new Spieler();
       this.firstNanoTimeTimer = 0;
       this.currentPapierFormat = A4.class;
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
        this.formatLabel.setText("Format: " + this.spieler.getLocher().getFormat().getSimpleName());
        this.papierLabel.setText("Stapel: " + this.spieler.getLocher().getStapel().groesse());
        //TODO: Zeit wird negativ!!!! 0-3s 💩💩💩💩
        //Changing the Time
        //Setting a leading Zero if reamingTimeAvailable is one digit
        if (remainingTimeAvailable < 10 && remainingTimeAvailable > 0) {
            leadingZero = "0";
        }
        else {
            leadingZero = "";
        }
        remainingTime.setText("Zeit: " + this.leadingZero + Math.round(this.remainingTimeAvailable) + "s");   
    }
    
    public Scene GameMainStage(Stage stage){
        //Main Orientation Node and initale settings
        BorderPane mainPane = new BorderPane();
        //mainPane.setStyle("-fx-background-repeat: repeat"); 

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
        PapierStapel<A4> stapel_A4 = new PapierStapel<>(A4.class);
        PapierStapel<A5> stapel_A5 = new PapierStapel<>(A5.class);
        PapierStapel<A6> stapel_A6 = new PapierStapel<>(A6.class);
        this.spieler.getLocher().setFormat(this.currentPapierFormat);
        this.spieler.getLocher().einlegen(stapel_A4);
        
        //Creating the Component-nodes
        //Creating the VBox for the right Output
        VBox rightVBox = new VBox();

        // Labels erstellen
        score = new Label();
        papierLabel = new Label();
        formatLabel = new Label();
        remainingTime = new Label();
        papierLabel.setTextFill(Color.WHITE);
        score.setTextFill(Color.WHITE);
        remainingTime.setTextFill(Color.WHITE);
        formatLabel.setTextFill(Color.WHITE);
        rightVBox.getChildren().add(score);
        for(Farbe farbe : Farbe.values()) {
            Label label = new Label();
            label.setTextFill(farbe.getGuiFarbe());
            this.scoreLabels.put(farbe, label);
            rightVBox.getChildren().add(label);
        }
        rightVBox.getChildren().addAll(remainingTime, formatLabel, papierLabel);
        this.updateLabels();

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
                if (spieler.getLocher().getCooldown() == 0) {
                    Lochprozess prozess = spieler.getLocher().lochen();
                    ArrayList<Konfetti> spielerKonfetti = spieler.getKonfetti();
                    spielerKonfetti.addAll(prozess.getKonfetti());
                }
            }
        });


        //creating a new Animation Timer for refreshing the GUI
        new AnimationTimer(){
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
                    new PapierObjekt(Game.this, new A4());
                    timeToNextPapier = 0.5d + (2d - 0.5d) * RANDOM.nextDouble();
                }

                if(hasLoched) {
                    //Setting the new Score
                    score.setText("Score: " + spieler.getKonfetti().size());
                    hasLoched = false;
                }
                //Check for end of Time
                if(remainingTimeAvailable == 0){
                    //TODO: End Game and Display Score Screen
                }

                Game.this.updateLabels();
            }
        }.start();


        //Add Nodes to the AnchorPane
        gameArea.getChildren().add(locher_new);

        //Add the elements to the Main Pane
        mainPane.setCenter(gameArea);

        //Set Window Titel
        stage.setTitle(windowTitle);
        return gameScene;
    }

    /**
     * Prüft, ob sich die beiden übergebenen Shapes überschneiden, wenn ja, dann wird die Anzeige angepasst
     * @return true=collision vorhanden, false= keine collision vorhanden
     */
    private boolean checkForCollision(Shape shape1, Shape shape2){
        Shape ueberschneidung = Shape.intersect(shape1, shape2);
        return !ueberschneidung.getBoundsInLocal().isEmpty();
    }

    public AnchorPane getArea() {
        return this.gameArea;
    }

    public boolean checkForLocherCollision(Shape papier) {
        return checkForCollision(papier, locher_new);
    }

    @SuppressWarnings("unchecked")
    public void papierAufLocherGezogen(PapierObjekt objekt) {
        // penis 🍆
        Class<? extends Papier> papierTyp = objekt.getPapier().getClass();
        if (this.currentPapierFormat == papierTyp) {
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
                objekt.zerstoeren();
            }
        }
    }

}