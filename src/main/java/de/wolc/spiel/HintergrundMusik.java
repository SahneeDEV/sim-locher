package de.wolc.spiel;

/**
 * Enthält eine Aufzählung der verfügbaren Hintergrund Musiktitel
 */
public enum HintergrundMusik{

    BACKGROUNDMUSIK_OFFICIALSOUNDTRACK("mainTheme.wav","backgroundMusik_mainTheme.gif","Offizieller Soundtrack©️", new Preis()),
    BACKGROUNDMUSIK_HOUSE("houseTheme.wav","backgroundMusik_house.png","House Track🏠", new Preis(Farbe.WEISS, 50, Farbe.ROT, 45, Farbe.BLAU, 50)),
    BACKGROUNDMUSIK_METAL("metalTheme.wav","backgroundMusik_metal.png","Metal Track🤘", new Preis(Farbe.GRUEN, 30, Farbe.PINK, 55, Farbe.ORANGE, 50, Farbe.ROT, 10));
    
    //Constructor
    private HintergrundMusik(String guiMusik, String guiBild, String name, Preis preis){
        this.guiMusik = guiMusik;
        this.guiBild = guiBild;
        this.name = name;
        this.preis = preis;
    }

    //Variables
    private String guiMusik;
    private String guiBild;
    private String name;
    private Preis preis;


    /**
     * @return the guiMusik
     */
    public String getMusikName(){
        return guiMusik;
    }

    /**
     * @return the guiBild
     */
    public String getGuiBild(){
        return guiBild;
    }

    /**
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * @return the Preis
     */
    public Preis getPreis(){
        return preis;
    }



}