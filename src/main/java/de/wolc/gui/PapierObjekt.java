package de.wolc.gui;

import java.util.Random;

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
    private static final Random RANDOM = new Random();

    public double zufallsBreite() {
        return this.game.getArea().getWidth() * RANDOM.nextDouble();
    }

    public double zufallsHoehe() {
        return this.game.getArea().getHeight() * RANDOM.nextDouble();
    }

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
        this.drawnPapier.setTranslateX(this.zufallsBreite());
        this.drawnPapier.setTranslateY(this.zufallsHoehe());
        this.drawnPapier.setOnMouseDragged((MouseEvent e) -> {
            //Change the location if the cursor has moved
            //TODO: Grab area anpassen
            this.drawnPapier.toFront();
            this.drawnPapier.setTranslateX(this.drawnPapier.getTranslateX() + (e.getX() - 300));
            this.drawnPapier.setTranslateY(this.drawnPapier.getTranslateY() + (e.getY() - 250));
            if (this.game.checkForLocherCollision(this.drawnPapier)){
                this.game.papierAufLocherGezogen(this);
                e.consume();
            }
        });
        this.game.getArea().getChildren().add(this.drawnPapier);
    }

    public Papier getPapier() {
        return this.papier;
    }

    public void zerstoeren() {
        this.game.getArea().getChildren().remove(this.drawnPapier);
    }
}