package de.wolc.spiel.locher;

import java.util.HashMap;
import java.util.Map;

import de.wolc.spiel.Farbe;

public enum LocherSkin {
    LOCHER_BASE("locher_base", "0815 Locher", new HashMap<>()),
    LOCHER_BAUMBLAETTER("locher_baumblaetter", "Öko-Locher", kostenVon(Farbe.GRUEN, 20, Farbe.WEISS, 5)),
    LOCHER_BEACH("locher_beach", "Locher on the Beach", kostenVon(Farbe.ROT, 15, Farbe.GRUEN, 15)),
    LOCHER_BLUE("locher_blue", "Bright Blue", kostenVon(Farbe.BLAU, 30)),
    LOCHER_COALLBLUE("locher_coallblue", "Eau de Toilette", kostenVon(Farbe.BLAU, 15, Farbe.WEISS, 15)),
    LOCHER_COPPER("locher_copper", "Elektroschock Locher", kostenVon(Farbe.ORANGE, 20, Farbe.WEISS, 10)),
    LOCHER_DEUTSCHLAND("locher_deutschland", "Das Vaterland", kostenVon(Farbe.BLAU, 30, Farbe.ROT, 30, Farbe.ORANGE, 30)),
    LOCHER_GOLD("locher_gold", "The-Million-Dollar-Hole-Puncher", kostenVon(Farbe.ORANGE, 100)),
    LOCHER_GREEN("locher_green", "Gumball Green", kostenVon(Farbe.GRUEN, 30)),
    LOCHER_GRAY("locher_grey", "Gross Gray", kostenVon(Farbe.WEISS, 30)),
    LOCHER_HFLAGGE("locher_hflagge", "Toleranzlocher", kostenVon(Farbe.PINK, 100)),
    LOCHER_KLETTBLATT("locher_kleeblatt", "Glückslocher", kostenVon(Farbe.GRUEN, 25, Farbe.BLAU, 5)),
    LOCHER_LIGHTPURPLE("locher_lightpurple", "Lätschernes Lila", kostenVon(Farbe.PINK, 15, Farbe.WEISS, 15)),
    LOCHER_LILA("locher_lila", "Lustiges Lila", kostenVon(Farbe.PINK, 20, Farbe.BLAU, 10)),
    LOCHER_MATRIX("locher_matrix", "Enter The Matrix", kostenVon(Farbe.GRUEN, 30, Farbe.BLAU, 30)),
    LOCHER_MICROSOFRT("locher_microsoft", "Billy Bob Locher", new HashMap<>()),
    LOCHER_MYLITTLEPONY("locher_mylittlepony", "My Little Locher", kostenVon(Farbe.WEISS, 50, Farbe.PINK, 50)),
    LOCHER_PIRATE("locher_pirate", "Locherrrrrrrr", kostenVon(Farbe.BLAU, 100)),
    LOCHER_SPOOKY("locher_spooky", "2spooky4me", kostenVon(Farbe.WEISS, 100)),
    LOCHER_STORM("locher_storm", "Locher Harvey", kostenVon(Farbe.BLAU, 20, Farbe.WEISS, 5)),
    LOCHER_PANZER("locher_tank", "Tiger", kostenVon(Farbe.GRUEN, 100)),
    LOCHER_UNICORN("locher_unicorn", "💜🦄", kostenVon(Farbe.PINK, 500));

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
