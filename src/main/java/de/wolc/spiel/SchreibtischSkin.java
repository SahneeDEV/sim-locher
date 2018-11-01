package de.wolc.spiel;

/**
 * Repräsentiert einem Schreibtischskin.
 */
public enum SchreibtischSkin {
    BACKGROUND_AUTUMN("background_autumn.jpg", "Herbstlischer Schreibtisch", new Preis(Farbe.GRUEN, 25, Farbe.ORANGE, 10)),
    BACKGROUND_BASE("background_base.jpg", "0815 Schreibtisch", new Preis()),
    BACKGROUND_MACBOOK("background_deskmacbook.jpg", "Apple 🍏", new Preis(Farbe.WEISS, 40)),
    BACKGROUND_DEVELOPER("background_developer.jpg", "Entwicklungshintergund 🤖", new Preis()),
    BACKGROUND_SATATTELLER("background_salatteller.jpg", "Hintergrundsalat 🥗", new Preis(Farbe.GRUEN, 10, Farbe.WEISS, 10, Farbe.ROT, 10));

    private SchreibtischSkin(String guiBild, String name, Preis preis) {
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
