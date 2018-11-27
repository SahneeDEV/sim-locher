package de.wolc.spiel.locher;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;

public enum LocherSkin {
    LOCHER_ANIMATED("locher_rgb.gif", "All Hail The RGB Locher", new Preis(Farbe.ROT, 100, Farbe.GRUEN, 100, Farbe.BLAU, 100), "locher_animated_gedrueckt.gif"),
    LOCHER_BASE("locher_base.png", "0815 Locher", new Preis(), "locher_gedrueckt2.png"),
    LOCHER_BAUMBLAETTER("locher_baumblaetter.png", "Öko-Locher", new Preis(Farbe.GRUEN, 20, Farbe.WEISS, 5), "locher_baumblaetter_gedrueckt.png"),
    LOCHER_BEACH("locher_beach.png", "Locher on the Beach", new Preis(Farbe.ROT, 15, Farbe.GRUEN, 15), "locher_beach_gedrueckt.png"),
    LOCHER_BLUE("locher_blue.png", "Bright Blue", new Preis(Farbe.BLAU, 30), "locher_blue_gedrueckt.png"),
    LOCHER_COALLBLUE("locher_coallblue.png", "Eau de Toilette", new Preis(Farbe.BLAU, 15, Farbe.WEISS, 15), "locher_coallblue_gedrueckt.png"),
    LOCHER_COPPER("locher_copper.png", "Elektroschock Locher", new Preis(Farbe.ORANGE, 20, Farbe.WEISS, 10), "locher_copper_gedrueckt.png"),
    LOCHER_DEUTSCHLAND("locher_deutschland.png", "Das Vaterland", new Preis(Farbe.BLAU, 30, Farbe.ROT, 30, Farbe.ORANGE, 30), "locher_deutschland_gedrueckt.png"),
    LOCHER_GOLD("locher_gold.png", "The-Million-Dollar-Hole-Puncher", new Preis(Farbe.ORANGE, 100), "locher_gold_gedrueckt.png"),
    LOCHER_GREEN("locher_green.png", "Gumball Green", new Preis(Farbe.GRUEN, 30), "locher_green_gedrueckt.png"),
    LOCHER_GRAY("locher_grey.png", "Gross Gray", new Preis(Farbe.WEISS, 30), "locher_grey_gedrueckt.png"),
    LOCHER_HFLAGGE("locher_hflagge.png", "Toleranzlocher", new Preis(Farbe.PINK, 100), "locher_hflagge_gedrueckt.png"),
    LOCHER_KLETTBLATT("locher_kleeblatt.png", "Glückslocher", new Preis(Farbe.GRUEN, 25, Farbe.BLAU, 5), "locher_kleeblatt_gedrueckt.png"),
    LOCHER_LIGHTPURPLE("locher_lightpurple.png", "Lätschernes Lila", new Preis(Farbe.PINK, 15, Farbe.WEISS, 15), "locher_lightpurple_gedrueckt.png"),
    LOCHER_LILA("locher_lila.png", "Lustiges Lila", new Preis(Farbe.PINK, 20, Farbe.BLAU, 10), "locher_lila_gedrueckt.png"),
    LOCHER_MATRIX("locher_matrix.png", "Enter The Matrix", new Preis(Farbe.GRUEN, 30, Farbe.BLAU, 30), "locher_matrix_gedrueckt.png"),
    LOCHER_MICROSOFT("locher_microsoft.png", "Billy Bob Locher", new Preis(), "locher_microsoft_gedrueckt.png"),
    LOCHER_MYLITTLEPONY("locher_mylittlepony.png", "My Little Locher", new Preis(Farbe.WEISS, 50, Farbe.PINK, 50), "locher_mylittlepony_gedrueckt.png"),
    LOCHER_PIRATE("locher_pirate.png", "Locherrrrrrrr", new Preis(Farbe.BLAU, 100), "locher_pirate_gedrueckt.png"),
    LOCHER_SPOOKY("locher_spooky.png", "2spooky4me", new Preis(Farbe.WEISS, 100), "locher_spooky_gedrueckt.png"),
    LOCHER_STORM("locher_storm.png", "Locher Harvey", new Preis(Farbe.BLAU, 20, Farbe.WEISS, 5), "locher_storm_gedrueckt.png"),
    LOCHER_PANZER("locher_tank.png", "Tiger", new Preis(Farbe.GRUEN, 100), "locher_tank_gedrueckt.png"),
    LOCHER_UNICORN("locher_unicorn.png", "💜🦄", new Preis(Farbe.PINK, 500), "locher_unicorn_gedrueckt.png");

    private LocherSkin(String guiBild, String name, Preis preis, String animationBild) {
        this.guiBild = guiBild;
        this.name = name;
        this.preis = preis;
        this.animationBild = animationBild;
    }

    private String guiBild;
    private String name;
    private Preis preis;
    private String animationBild;

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

    /**
     * @return das Bild der Animation
     */
    public String getAnimationBild(){
        return animationBild;
    }
}
