package de.wolc.spiel;

import java.util.Random;

/**
 * Repräsentiert eine Farbe.
 */
public enum Farbe {
  WEISS,
  ROT,
  GRUEN,
  BLAU,
  PINK,
  ORANGE;

  private static final Farbe[] ALLE_FARBEN = values();
  private static final Random ZUFALL = new Random();

  /**
   * Gibt eine zufällige Farbe zurück.
   * @return Die Farbe.
   */
  public static Farbe zufallsfarbe()  {
    return ALLE_FARBEN[ZUFALL.nextInt(ALLE_FARBEN.length)];
  }
}
