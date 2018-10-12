/**
 * Ein einzelnes Konfetti. 
 */
public class Konfetti 
{
    private final Farbe farbe;

    /**
     * Erstellt ein neues Konfetti mit der gegebenen Farbe.
     * @param farbe Die Farbe.
     */
    public Konfetti(Farbe farbe) {
        this.farbe = farbe;
    }

    /**
     * Gibt die Farbe des Konfettis zurück.
     * @return Die Farbe.
     */
    public Farbe getFarbe() {
        return this.farbe;
    }
}
