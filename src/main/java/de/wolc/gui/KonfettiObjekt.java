package de.wolc.gui;

import de.wolc.MultiUse;
import de.wolc.spiel.papier.Konfetti;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

/**
 * Stellt ein Konfetti in der Welt da.
 */
public class KonfettiObjekt {

    private static final Image BILD = new Image("de/wolc/gui/images/konfetti_master.png");
    private static final double FALLGESCHWINDIGKEIT = -100d;
    private static final double WACKEL = 1.3d;
    private static final double TTL = 10d;

    private final Game game;
    private final Konfetti konfetti;
    private double x, y;
    private Rectangle objekt;
    private double alter;

    public KonfettiObjekt(Game game, Konfetti konfetti, double x, double y) {
        this.game = game;
        this.konfetti = konfetti;
        this.x = x;
        this.y = y;
        
        // Effekt für die Farbe des Konfetti setzen.
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, this.konfetti.getFarbe().getGuiFarbe()));

        // Neues Konfetti Objekt erzeugen.
        this.objekt = new Rectangle();
        this.objekt.toFront();
        this.objekt.setEffect(lighting);
        this.objekt.setFill(new ImagePattern(BILD));
        this.objekt.setWidth(BILD.getWidth());
        this.objekt.setHeight(BILD.getHeight());
        this.positionSetzen();

        this.game.getArea().getChildren().add(this.objekt);
    }

    public void tick(double deltaZeit) {
        this.objekt.toBack();
        this.alter += deltaZeit;
        this.y -= KonfettiObjekt.FALLGESCHWINDIGKEIT * deltaZeit;
        this.x += MultiUse.zufall(-KonfettiObjekt.WACKEL, KonfettiObjekt.WACKEL);
        this.positionSetzen();
        if (this.alter >= TTL) {
            this.zerstoeren();
        }
    }

    private void positionSetzen() {
        this.objekt.setTranslateX(this.x);
        this.objekt.setTranslateY(this.y);
    }

    private void zerstoeren() {
        this.game.getArea().getChildren().remove(this.objekt);
        this.objekt = null;
    }
    
    public boolean istZerstoert() {
        return this.objekt == null;
    }

    /**
     * @return the konfetti
     */
    public Konfetti getKonfetti() {
        return konfetti;
    }
}
