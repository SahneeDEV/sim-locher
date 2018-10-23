package de.wolc.gui;

import java.util.ArrayList;

import de.wolc.spiel.Spieler;
import de.wolc.spiel.papier.A4;
import de.wolc.spiel.papier.A5;
import de.wolc.spiel.papier.A6;
import de.wolc.spiel.papier.Papier;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

public class LocherPapier{

    private static final Image paperImageA4 = new Image("de/wolc/gui/images/paper_cutout.png");
    private static final Image paperImageA5 = new Image("de/wolc/gui/images/paper_cutout_a5.png");
    private static final Image paperImageA6 = new Image("de/wolc/gui/images/paper_cutout_a6.png");

    //Klassenvariablen
    private Rectangle locherPapier;
    private final Game game;
    private Spieler spieler;
    private ArrayList<Rectangle> locherPapiere = new ArrayList<Rectangle>();
    private Class<? extends Papier> aktuellesFormat;
    private Alert spielerAlert;
    private Papier papier;
    private Color currentColor;

    public LocherPapier(Game game, int stapelGroesse, Rectangle locher, Papier papier){
        //Zuweisung der Klassenvariabeln
        this.game = game;
        this.papier = papier;
        this.currentColor = papier.getFarbe().getGuiFarbe();
        this.spieler = game.getCurrentSpieler();
        this.aktuellesFormat = this.spieler.getLocher().getFormat();

        //Hole die position des lochers
        Bounds locherPosition = locher.localToScene(locher.getBoundsInLocal());

        //Effekt für die Farbe des Papieres setzen
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, currentColor));

        //Neues papier erzeugen
        this.locherPapier = new Rectangle();
        this.locherPapier.toFront();
        this.locherPapier.setEffect(lighting);

        //A4 Papier
        if(this.aktuellesFormat == A4.class){
            //Neues A4 Blatt erzeugen
            this.locherPapier.setFill(new ImagePattern(paperImageA4));
            this.locherPapier.setWidth(paperImageA4.getWidth());
            this.locherPapier.setHeight(paperImageA4.getHeight());
            this.locherPapier.setTranslateX(locherPosition.getMinX() - 199  - (stapelGroesse * 0.15));
            this.locherPapier.setTranslateY(locherPosition.getMinY() + 249  - (stapelGroesse * 0.15));
        }
        //A5 Papier
        else if(this.aktuellesFormat == A5.class){
            //Neues A5 Blatt erzeugen
            this.locherPapier.setFill(new ImagePattern(paperImageA5));
            this.locherPapier.setWidth(paperImageA5.getWidth());
            this.locherPapier.setHeight(paperImageA5.getHeight());
            this.locherPapier.setTranslateX(locherPosition.getMinX() - 100  - (stapelGroesse * 0.15));
            this.locherPapier.setTranslateY(locherPosition.getMinY() + 290  - (stapelGroesse * 0.15));
        }
        //A6 Papier
        else if(this.aktuellesFormat == A6.class){
            //Neues A6 Blatt erzeugen
            this.locherPapier.setFill(new ImagePattern(paperImageA6));
            this.locherPapier.setWidth(paperImageA6.getWidth());
            this.locherPapier.setHeight(paperImageA6.getHeight());
            this.locherPapier.setTranslateX(locherPosition.getMinX() - 23.5  - (stapelGroesse * 0.15));
            this.locherPapier.setTranslateY(locherPosition.getMinY() + 309.5  - (stapelGroesse * 0.15));
        }
        else{
            this.spielerAlert = new Alert(AlertType.WARNING);
            this.spielerAlert.setTitle("Fehler beim zuweisen des Papieres in den Locher!");
            this.spielerAlert.setHeaderText("Das Papier, dass du gerade in den Locher stecken wolltest kann nicht hinzugefügt werden.");
            this.spielerAlert.setResult(ButtonType.OK);
            this.spielerAlert.showAndWait();
        }

        //Das eben erzeugte Blatt dem 'game' hinzufügen
        this.game.getArea().getChildren().add(this.locherPapier);
        this.locherPapiere.add(this.locherPapier);
    }

    /**
     * Gibt das Array mit den Papieren zurück
     * @return Die ArrayList<Rectangle>
     */
    public ArrayList<Rectangle> getPapierListe(){
        return this.locherPapiere;
    }
}