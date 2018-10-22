package de.wolc.gui;

import java.util.ArrayList;
import java.util.Random;

import de.wolc.MultiUse;
import de.wolc.gui.PapierObjekt;
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

public class LocherPapier{

    private static final Image paperImage = new Image("de/wolc/gui/images/paper_cutout.png");

    private final Rectangle locherPapier;
    private final Game game;
    private ArrayList<Rectangle> locherPapiere = new ArrayList<Rectangle>();

    public LocherPapier(Game game, int stapelGroesse){
        //
        this.game = game;

        this.locherPapier = new Rectangle();
        this.locherPapier.toFront();
        this.locherPapier.setFill(new ImagePattern(paperImage));
        this.locherPapier.setWidth(paperImage.getWidth());
        this.locherPapier.setHeight(paperImage.getHeight());
        //TODO: papier genauer einpassen
        this.locherPapier.setTranslateX(game.getArea().getWidth() * 0.340 - (stapelGroesse * 0.15));
        this.locherPapier.setTranslateY(game.getArea().getHeight() * 0.540 - (stapelGroesse * 0.15));


        this.game.getArea().getChildren().add(this.locherPapier);
        
        
    }

    /**
     * Gibt das Array mit den Papieren zurück
     * @return Die ArrayList<Rectangle>
     */
    public ArrayList<Rectangle> getPapierListe(){
        return this.locherPapiere;
    }
}