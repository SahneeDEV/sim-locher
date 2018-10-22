package de.wolc.spiel;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Repräsentiert eine Farbe.
 */
public enum Farbe {
  WEISS("Weiß", Color.WHITE),
  ROT("Rot", Color.RED),
  GRUEN("Grün", Color.GREEN),
  BLAU("Blau", Color.BLUE),
  PINK("Pink", Color.PINK),
  ORANGE("Orange", Color.ORANGE);

  private Farbe(String anzeigeName, Color guiFarbe) {
    this.anzeigeName = anzeigeName;
    this.guiFarbe = guiFarbe;
  }

  private static final Farbe[] ALLE_FARBEN = values();
  private static final Random ZUFALL = new Random();

  private String anzeigeName;
  private Color guiFarbe;

  public String getAnzeigeName() {
    return this.anzeigeName;
  }

  public Color getGuiFarbe() {
    return this.guiFarbe;
  }

  /**
   * Gibt eine zufällige Farbe zurück.
   * @return Die Farbe.
   */
  public static Farbe zufallsfarbe()  {
    return ALLE_FARBEN[ZUFALL.nextInt(ALLE_FARBEN.length)];
  }
}
