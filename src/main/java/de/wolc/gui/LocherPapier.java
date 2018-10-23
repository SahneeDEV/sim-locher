package de.wolc.gui;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Bounds;

public class LocherPapier{

    private static final Image paperImage = new Image("de/wolc/gui/images/paper_cutout.png");

    private Rectangle locherPapier;
    private final Game game;
    private ArrayList<Rectangle> locherPapiere = new ArrayList<Rectangle>();

    public LocherPapier(Game game, int stapelGroesse, Rectangle locher){
        //
        this.game = game;

        this.locherPapier = new Rectangle();
        this.locherPapier.toFront();
        this.locherPapier.setFill(new ImagePattern(paperImage));
        this.locherPapier.setWidth(paperImage.getWidth());
        this.locherPapier.setHeight(paperImage.getHeight());
        //TODO: papier genauer einpassen + 3D Effekt richtig einbinden
        Bounds locherPosition = locher.localToScene(locher.getBoundsInLocal());
        this.locherPapier.setTranslateX(locherPosition.getMinX() - 199  - (stapelGroesse * 0.15));
        this.locherPapier.setTranslateY(locherPosition.getMinY() + 249  - (stapelGroesse * 0.15));

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