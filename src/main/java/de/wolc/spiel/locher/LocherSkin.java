package de.wolc.spiel.locher;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;

public enum LocherSkin {
    LOCHER_ANIMATED("locher_rgb.gif", "All Hail The RGB Locher", new Preis(Farbe.ROT, 100, Farbe.GRUEN, 100, Farbe.BLAU, 100)),
    LOCHER_BASE("locher_base.png", "0815 Locher", new Preis()),
    LOCHER_BAUMBLAETTER("locher_baumblaetter.png", "Öko-Locher", new Preis(Farbe.GRUEN, 20, Farbe.WEISS, 5)),
    LOCHER_BEACH("locher_beach.png", "Locher on the Beach", new Preis(Farbe.ROT, 15, Farbe.GRUEN, 15)),
    LOCHER_BLUE("locher_blue.png", "Bright Blue", new Preis(Farbe.BLAU, 30)),
    LOCHER_COALLBLUE("locher_coallblue.png", "Eau de Toilette", new Preis(Farbe.BLAU, 15, Farbe.WEISS, 15)),
    LOCHER_COPPER("locher_copper.png", "Elektroschock Locher", new Preis(Farbe.ORANGE, 20, Farbe.WEISS, 10)),
    LOCHER_DEUTSCHLAND("locher_deutschland.png", "Das Vaterland", new Preis(Farbe.BLAU, 30, Farbe.ROT, 30, Farbe.ORANGE, 30)),
    LOCHER_GOLD("locher_gold.png", "The-Million-Dollar-Hole-Puncher", new Preis(Farbe.ORANGE, 100)),
    LOCHER_GREEN("locher_green.png", "Gumball Green", new Preis(Farbe.GRUEN, 30)),
    LOCHER_GRAY("locher_grey.png", "Gross Gray", new Preis(Farbe.WEISS, 30)),
    LOCHER_HFLAGGE("locher_hflagge.png", "Toleranzlocher", new Preis(Farbe.PINK, 100)),
    LOCHER_KLETTBLATT("locher_kleeblatt.png", "Glückslocher", new Preis(Farbe.GRUEN, 25, Farbe.BLAU, 5)),
    LOCHER_LIGHTPURPLE("locher_lightpurple.png", "Lätschernes Lila", new Preis(Farbe.PINK, 15, Farbe.WEISS, 15)),
    LOCHER_LILA("locher_lila.png", "Lustiges Lila", new Preis(Farbe.PINK, 20, Farbe.BLAU, 10)),
    LOCHER_MATRIX("locher_matrix.png", "Enter The Matrix", new Preis(Farbe.GRUEN, 30, Farbe.BLAU, 30)),
    LOCHER_MICROSOFT("locher_microsoft.png", "Billy Bob Locher", new Preis()),
    LOCHER_MYLITTLEPONY("locher_mylittlepony.png", "My Little Locher", new Preis(Farbe.WEISS, 50, Farbe.PINK, 50)),
    LOCHER_PIRATE("locher_pirate.png", "Locherrrrrrrr", new Preis(Farbe.BLAU, 100)),
    LOCHER_SPOOKY("locher_spooky.png", "2spooky4me", new Preis(Farbe.WEISS, 100)),
    LOCHER_STORM("locher_storm.png", "Locher Harvey", new Preis(Farbe.BLAU, 20, Farbe.WEISS, 5)),
    LOCHER_PANZER("locher_tank.png", "Tiger", new Preis(Farbe.GRUEN, 100)),
    LOCHER_UNICORN("locher_unicorn.png", "💜🦄", new Preis(Farbe.PINK, 500));

    private LocherSkin(String guiBild, String name, Preis preis) {
        this.guiBild = guiBild;
        this.name = name;
        this.preis = preis;
    }

    private String guiBild;
    private String name;
    private Preis preis;

    /**
     * @return the guiBild
     */
    public String getGuiBild() {
        return guiBild;
    }

    /**
     * @return the name
     */
    public String getGuiName() {
        return name;
    }

    /**
     * @return the kosten
     */
    public Preis getPreis() {
        return preis;
    }
}
