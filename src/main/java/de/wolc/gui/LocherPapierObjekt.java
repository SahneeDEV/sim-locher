package de.wolc.gui;

import de.wolc.spiel.papier.A4;
import de.wolc.spiel.papier.A5;
import de.wolc.spiel.papier.A6;
import de.wolc.spiel.papier.Papier;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Bounds;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

public class LocherPapierObjekt {

    private static final Image BILD_A4 = new Image("de/wolc/gui/images/paper_cutout_a4.png");
    private static final Image BILD_A5 = new Image("de/wolc/gui/images/paper_cutout_a5.png");
    private static final Image BILD_A6 = new Image("de/wolc/gui/images/paper_cutout_a6.png");

    //Klassenvariablen
    private Rectangle locherPapier;
    private final Game game;
    private Papier papier;

    public LocherPapierObjekt(Game game, int stapelGroesse, Rectangle locher, Papier papier) {
        //Zuweisung der Klassenvariabeln
        this.game = game;
        this.papier = papier;

        //Hole die position des lochers
        Bounds locherPosition = locher.localToScene(locher.getBoundsInLocal());

        // Werte für aktuelles Format bestimmen (Fehler werfen wenn ungültig)
        Image bild;
        double x, y;
        Class<? extends Papier> aktuellesFormat = papier.getClass();
        if (aktuellesFormat == A4.class) {
            bild = BILD_A4;
            x = locherPosition.getMinX() - 90.5d  - (stapelGroesse * 0.15d);
            y = locherPosition.getMinY() + 260d  - (stapelGroesse * 0.15d);
        } else if (aktuellesFormat == A5.class) {
            bild = BILD_A5;
            x = locherPosition.getMinX() + 2.5d  - (stapelGroesse * 0.15d);
            y = locherPosition.getMinY() + 290d  - (stapelGroesse * 0.15d);
        } else if (aktuellesFormat == A6.class) {
            bild = BILD_A6;
            x = locherPosition.getMinX() + 86d  - (stapelGroesse * 0.15d);
            y = locherPosition.getMinY() + 310.5d - (stapelGroesse * 0.15d);
        } else {
            throw new IllegalStateException("Ungültiges Papierformat: " + aktuellesFormat);
        }

        //Effekt für die Farbe des Papieres setzen
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, papier.getFarbe().getGuiFarbe()));

        //Neues papier erzeugen
        this.locherPapier = new Rectangle();
        this.locherPapier.toFront();
        this.locherPapier.setEffect(lighting);
        this.locherPapier.setFill(new ImagePattern(bild));
        this.locherPapier.setWidth(bild.getWidth());
        this.locherPapier.setHeight(bild.getHeight());
        this.locherPapier.setTranslateX(x);
        this.locherPapier.setTranslateY(y);

        //Das eben erzeugte Blatt dem 'game' hinzufügen
        this.game.getArea().getChildren().add(this.locherPapier);
    }

    public void zerstoeren() {
        this.game.getArea().getChildren().remove(this.locherPapier);
    }

    public Papier getPapier () {
        return this.papier;
    }
}