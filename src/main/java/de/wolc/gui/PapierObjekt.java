package de.wolc.gui;

import de.wolc.MultiUse;
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

public class PapierObjekt {
    private static final Image IMAGE = new Image("de/wolc/gui/images/paper_master.png");

    private final Papier papier;
    private final Game game;
    private final Rectangle drawnPapier;

    public PapierObjekt(Game game, Papier papier) {
        this.papier = papier;
        this.game = game;
        this.drawnPapier = new Rectangle();
        this.drawnPapier.setFill(new ImagePattern(IMAGE));
        this.drawnPapier.setWidth(IMAGE.getWidth());
        this.drawnPapier.setHeight(IMAGE.getHeight());
        this.drawnPapier.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e){
                //Change the location if the cursor has moved
                drawnPapier.setTranslateX(drawnPapier.getTranslateX() + (e.getX() - 175));
                drawnPapier.setTranslateY(drawnPapier.getTranslateY() + (e.getY() - 110));
                if (game.checkForLocherCollision(drawnPapier)) {
                    //game.papierAufLocherGezogen();
                }
            }
        });
    }

    public Papier getPapier() {
        return this.papier;
    }
}