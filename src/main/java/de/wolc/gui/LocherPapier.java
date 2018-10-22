package de.wolc.gui;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LocherPapier{

    private static final Image paperImage = new Image("de/wolc/gui/images/paper_cutout.png");

    private Rectangle locherPapier;
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
        //TODO: papier genauer einpassen + 3D Effekt richtig einbinden
        this.locherPapier.setTranslateX(game.getArea().getWidth() * 0.339 - (stapelGroesse * 0.15));
        this.locherPapier.setTranslateY(game.getArea().getHeight() * 0.539 - (stapelGroesse * 0.15));

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