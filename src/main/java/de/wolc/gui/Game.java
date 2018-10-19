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
        remainingTime.setText("Zeit: 00:" + remainingTimeAvailable);

        //Adding remainingTime and score to VBox 
        rightVBox.getChildren().addAll(score, remainingTime);

        //Adding VBox to mainPane
        mainPane.setRight(rightVBox);
        BorderPane.setAlignment(rightVBox, Pos.CENTER);

        //Animation Timer
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                long elapsedNanoSeconds = (System.currentTimeMillis() - lastNanoTimeTimer);
                double elapsedSeconds = ((elapsedNanoSeconds / 1000d));

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
                    remainingTimeAvailable = remainingTimeAvailable - 1;
                    //TODO: 
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
        locher.setOnMouseClicked(new EventHandler<MouseEvent>() { // (EventHandler<MouseEvent>)
            @Override
            public void handle(MouseEvent e) {
                double cooldown = spieler.getLocher().getCooldown();
                if (cooldown == 0) {
                    Lochprozess prozess = spieler.getLocher().lochen();
                    ArrayList<Konfetti> spielerKonfetti = spieler.getKonfetti();
                    spielerKonfetti.addAll(prozess.getKonfetti());
                    hasLoched = true;
                }
            }
        });
        locherBox.getChildren().add(locher);

        mainPane.setCenter(locherBox);
        BorderPane.setAlignment(locherBox, Pos.CENTER);

        //Set Window Titel
        stage.setTitle(windowTitle);
        return gameScene;
    }
}
