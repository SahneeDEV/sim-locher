package de.wolc.gui;

import java.util.ArrayList;

import de.wolc.MultiUse;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.papier.Konfetti;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;

public class Game{

    private final String windowTitle = "World of Locher Craft";
    private final String backgroundImageLocation = "de/wolc/gui/images/Test_Bild.jpg";
    private Spieler spieler;
    
    //Game Variables
    private int remainingTimeAvailable = 30;
    private boolean hasLoched = false;

    //Variables for Countdown timer
    private long lastNanoTimeTimer = 0;

    //Paper-Movement
    private double paperMoveX;
    private double paperMoveY;



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

        //StackPane for Locher and Paper
        StackPane gameArea = new StackPane();

        //Setting the size of the gameArea in percent from windowSize
        gameArea.setMinWidth(((double)windowSize[0] * 0.8));
        gameArea.setMinHeight(((double)windowSize[1] * 0.8));
        
        //Spawn the paper
        AnchorPane paperPane = new AnchorPane();
        //Setting height of paperPane
        paperPane.setMinWidth(150.0);
        paperPane.setMinHeight(150.0);


        ImageView paper = new ImageView("de/wolc/gui/images/paper_master.png");

        //Setting the default Anchor points for the paper
        AnchorPane.setBottomAnchor(paper, 100.0);
        AnchorPane.setLeftAnchor(paper, 100.0);
        
        //
        paperPane.getChildren().add(paper);

        //Mouse Event
        paper.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //Set the pressed mouse location
                paperMoveX = e.getSceneX();
                System.out.println(paperMoveX);
                paperMoveY = e.getSceneY();
                System.out.println(paperMoveY);                
                                                    
            }
        });

        paper.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e){
                //Change the location if the cursor has moved
                paper.setTranslateX(paper.getTranslateX() + (e.getX() - 150));
                paper.setTranslateY(paper.getTranslateY() + (e.getY() - 100));
            }
        });


        //creating a new Animation Timer for refreshing GUI
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
                    /** else{
                    FIXME: Zeit geht ins negative
                    }
                    */

                    //Changing the Time
                    //TODO: Eine 0 voransetzen wenn "remainingTimeAvailabe" einstellig ist
                    remainingTimeAvailable = remainingTimeAvailable - 1;
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

        //Creating VBox for Locher and displaying Locher
        VBox locherBox = new VBox();
        locherBox.setAlignment(Pos.CENTER);

        String skin = spieler.getLocher().getSkin();

        ImageView locher = new ImageView("de/wolc/gui/images/" +skin+ ".png"); 
        locher.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (spieler.getLocher().getCooldown() == 0) {
                    Lochprozess prozess = spieler.getLocher().lochen();
                    ArrayList<Konfetti> spielerKonfetti = spieler.getKonfetti();
                    spielerKonfetti.addAll(prozess.getKonfetti());
                    hasLoched = true;
                }
            }
        });

        //TODO:
        locher.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Dragged in");
            }
        });

        //locherBox.setMinHeight(gameArea.getMinHeight());
        //locherBox.setMinWidth(gameArea.getMinWidth());

        locherBox.getChildren().add(locher);
        

        //mainPane.setAlignment(locherBox, Pos.CENTER);
        //mainPane.setCenter(locherBox);

        //Add the elements to the Main Pane
        gameArea.getChildren().addAll(locherBox, paperPane);
        mainPane.setCenter(gameArea);

        //Set Window Titel
        stage.setTitle(windowTitle);
        return gameScene;
    }
}
