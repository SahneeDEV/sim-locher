package de.wolc.gui;

import java.util.ArrayList;

import de.wolc.MultiUse;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.papier.Konfetti;


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
    
    //Game Variables
    private int remainingTimeAvailable = 30;
    private boolean hasLoched = false;

    //Variables for Countdown timer
    private long lastNanoTimeTimer = 0;

    public Game () {
       this.spieler = new Spieler();
       this.lastNanoTimeTimer = System.currentTimeMillis();
       
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
        
        //Creating the Component-nodes
        //Creating the VBox for the right Output
        VBox rightVBox = new VBox();

        //Creating Label for outputing score
        Label score = new Label();
        score.setTextFill(Color.RED);
        score.setText("Score: " + spieler.getKonfetti().size()); 

        //Creating Label for remaining Time
        Label remainingTime = new Label();
        remainingTime.setTextFill(Color.RED);
        remainingTime.setText("Zeit: " + remainingTimeAvailable + "s");

        //Adding remainingTime and score to VBox 
        rightVBox.getChildren().addAll(score, remainingTime);

        //Adding VBox to mainPane
        mainPane.setRight(rightVBox);
        BorderPane.setAlignment(rightVBox, Pos.CENTER);
        
        //Spawn the paper
        AnchorPane gameArea = new AnchorPane();
        //Setting height of paperPane
        gameArea.setMinWidth(((double)windowSize[0] * 0.8));
        gameArea.setMinHeight(((double)windowSize[1] * 0.8));

        //PAPER
        Image paperImage = new Image("de/wolc/gui/images/paper_master.png");
        Rectangle paper_new = new Rectangle();
        paper_new.setHeight(paperImage.getHeight());
        paper_new.setWidth(paperImage.getWidth());
        paper_new.setFill(new ImagePattern(paperImage));

        //Setting the default Anchor points for the paper        
        AnchorPane.setBottomAnchor(paper_new, 100.0);
        AnchorPane.setLeftAnchor(paper_new, 100.0);


        //LOCHER
        String skin = spieler.getLocher().getSkin();

        Image locher_skin = new Image("de/wolc/gui/images/" + skin + ".png");
        Rectangle locher_new = new Rectangle();
        locher_new.setHeight(locher_skin.getHeight());
        locher_new.setWidth(locher_skin.getWidth());
        locher_new.setFill(new ImagePattern(locher_skin));

        //Setting the default Anchor points for the paper
        AnchorPane.setBottomAnchor(locher_new, stage.getWidth() * 0.20);
        AnchorPane.setLeftAnchor(locher_new, stage.getHeight() * 0.65);


        //Paper_new Mouse Events
        paper_new.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e){
                //Change the location if the cursor has moved
                paper_new.setTranslateX(paper_new.getTranslateX() + (e.getX() - 175));
                paper_new.setTranslateY(paper_new.getTranslateY() + (e.getY() - 110));
                checkForCollision(paper_new, locher_new, paperImage);
            }
        });


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
        locher_new.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e){
                checkForCollision(paper_new, locher_new, paperImage);
            }
        });

        //creating a new Animation Timer for refreshing the GUI
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                //Getting new and last TimeStamp in Miliseconds and calculating 
                long elapsedNanoSeconds = (System.currentTimeMillis() - lastNanoTimeTimer);
                double elapsedSeconds = ((elapsedNanoSeconds / 1000d));

                //Triggering Cooldown and giving him the elapsedSeconds
                spieler.tick(elapsedSeconds);

                if(hasLoched) {
                    //Setting the new Score
                    score.setText("Score: " + spieler.getKonfetti().size());
                    hasLoched = false;
                }

                if(elapsedSeconds >= 1){

                    //Check for end of Time
                    if(remainingTimeAvailable == 0){
                        //TODO: End Game and Display Score Screen
                    }

                    //Changing the Time
                    //TODO: Eine 0 voransetzen wenn "remainingTimeAvailabe" einstellig ist
                    if (remainingTimeAvailable instanceof Integer)
                    remainingTimeAvailable = remainingTimeAvailable - 1;
                    if (remainingTimeAvailable < 10) {
                        remainingTimeAvailable = String.valueOf(remainingTimeAvailable);
                    }
                    remainingTime.setText("Zeit: " + remainingTimeAvailable + "s");

                    lastNanoTimeTimer = System.currentTimeMillis();
                }
                /** else{
                    //Notice if Timeing is not correct
                    remainingTime.setText("Zeit Asyncron!");
                    lastNanoTimeTimer = System.currentTimeMillis();
                    FIXME: Es wird immer else Ausgegeben
                }
                */
                
                
            }
        }.start();

        //Add Nodes to the AnchorPane
        gameArea.getChildren().addAll(locher_new, paper_new);

        //Add the elements to the Main Pane
        mainPane.setCenter(gameArea);

        //Set Window Titel
        stage.setTitle(windowTitle);
        return gameScene;
    }

    /**
     * Prüft, ob sich die beiden übergebenen Shapes überschneiden, wenn ja, dann wird die Anzeige angepasst
     */
    private void checkForCollision(Shape paper, Shape locher, Image defaultSkin){
        boolean collisionDetection = false;

        Shape ueberschneidung = Shape.intersect(paper, locher);
        if(ueberschneidung.getBoundsInLocal().getWidth() != -1){
            collisionDetection = true;
        }

        if(collisionDetection){
            //TODO: Hier muss noch weiteres gemacht werden
            paper.setFill(Color.BLACK);
            //TODO: Hier benötigen wir eine Rückgabe des Papier Formates

        }
        else{
            paper.setFill(new ImagePattern(defaultSkin));
        }
    }

}