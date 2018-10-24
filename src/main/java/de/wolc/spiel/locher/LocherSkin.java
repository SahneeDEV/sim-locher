package de.wolc.spiel.locher;

import java.util.HashMap;
import java.util.Map;

import de.wolc.spiel.Farbe;

public enum LocherSkin {
    LOCHER_ANIMATED("locher_rgb.gif", "All Hail The RGB Locher", kostenVon(Farbe.ROT, 100, Farbe.GRUEN, 100, Farbe.BLAU, 100)),
    LOCHER_BASE("locher_base.png", "0815 Locher", new HashMap<>()),
    LOCHER_BAUMBLAETTER("locher_baumblaetter.png", "Öko-Locher", kostenVon(Farbe.GRUEN, 20, Farbe.WEISS, 5)),
    LOCHER_BEACH("locher_beach.png", "Locher on the Beach", kostenVon(Farbe.ROT, 15, Farbe.GRUEN, 15)),
    LOCHER_BLUE("locher_blue.png", "Bright Blue", kostenVon(Farbe.BLAU, 30)),
    LOCHER_COALLBLUE("locher_coallblue.png", "Eau de Toilette", kostenVon(Farbe.BLAU, 15, Farbe.WEISS, 15)),
    LOCHER_COPPER("locher_copper.png", "Elektroschock Locher", kostenVon(Farbe.ORANGE, 20, Farbe.WEISS, 10)),
    LOCHER_DEUTSCHLAND("locher_deutschland.png", "Das Vaterland", kostenVon(Farbe.BLAU, 30, Farbe.ROT, 30, Farbe.ORANGE, 30)),
    LOCHER_GOLD("locher_gold.png", "The-Million-Dollar-Hole-Puncher", kostenVon(Farbe.ORANGE, 100)),
    LOCHER_GREEN("locher_green.png", "Gumball Green", kostenVon(Farbe.GRUEN, 30)),
    LOCHER_GRAY("locher_grey.png", "Gross Gray", kostenVon(Farbe.WEISS, 30)),
    LOCHER_HFLAGGE("locher_hflagge.png", "Toleranzlocher", kostenVon(Farbe.PINK, 100)),
    LOCHER_KLETTBLATT("locher_kleeblatt.png", "Glückslocher", kostenVon(Farbe.GRUEN, 25, Farbe.BLAU, 5)),
    LOCHER_LIGHTPURPLE("locher_lightpurple.png", "Lätschernes Lila", kostenVon(Farbe.PINK, 15, Farbe.WEISS, 15)),
    LOCHER_LILA("locher_lila.png", "Lustiges Lila", kostenVon(Farbe.PINK, 20, Farbe.BLAU, 10)),
    LOCHER_MATRIX("locher_matrix.png", "Enter The Matrix", kostenVon(Farbe.GRUEN, 30, Farbe.BLAU, 30)),
    LOCHER_MICROSOFT("locher_microsoft.png", "Billy Bob Locher", new HashMap<>()),
    LOCHER_MYLITTLEPONY("locher_mylittlepony.png", "My Little Locher", kostenVon(Farbe.WEISS, 50, Farbe.PINK, 50)),
    LOCHER_PIRATE("locher_pirate.png", "Locherrrrrrrr", kostenVon(Farbe.BLAU, 100)),
    LOCHER_SPOOKY("locher_spooky.png", "2spooky4me", kostenVon(Farbe.WEISS, 100)),
    LOCHER_STORM("locher_storm.png", "Locher Harvey", kostenVon(Farbe.BLAU, 20, Farbe.WEISS, 5)),
    LOCHER_PANZER("locher_tank.png", "Tiger", kostenVon(Farbe.GRUEN, 100)),
    LOCHER_UNICORN("locher_unicorn.png", "💜🦄", kostenVon(Farbe.PINK, 500));

    private LocherSkin(String guiBild, String name, Map<Farbe, Integer> kosten) {
        this.guiBild = guiBild;
        this.name = name;
        this.kosten = kosten;
    }

    private String guiBild;
    private String name;
    private Map<Farbe, Integer> kosten;

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
    public Map<Farbe, Integer> getKosten() {
        return kosten;
    }

    private static HashMap<Farbe, Integer> kostenVon(Farbe farbe1, int kosten1) {
        HashMap<Farbe, Integer> hash = new HashMap<>();
        hash.put(farbe1, kosten1);
        return hash;
    }

    private static HashMap<Farbe, Integer> kostenVon(Farbe farbe1, int kosten1, Farbe farbe2, int kosten2) {
        HashMap<Farbe, Integer> hash = new HashMap<>();
        hash.put(farbe1, kosten1);
        hash.put(farbe2, kosten2);
        return hash;
    }

    private static HashMap<Farbe, Integer> kostenVon(Farbe farbe1, int kosten1, Farbe farbe2, int kosten2, Farbe farbe3,
            int kosten3) {
        HashMap<Farbe, Integer> hash = new HashMap<>();
        hash.put(farbe1, kosten1);
        hash.put(farbe2, kosten2);
        hash.put(farbe3, kosten3);
        return hash;
    }
}
