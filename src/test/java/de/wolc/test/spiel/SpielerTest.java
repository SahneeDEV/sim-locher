package de.wolc.test.spiel;

import java.util.ArrayList;
import java.util.HashMap;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.SchreibtischSkin;
import de.wolc.spiel.Spieler;
import de.wolc.spiel.papier.Konfetti;
import junit.framework.TestCase;

/**
 * Beispiel unit test (Wir verwenden jUnit 3.8.1, siehe pom.xml für Begründung).
 * 
 * Siehe hier für alle möglichen asserts:
 * http://junit.sourceforge.net/junit3.8.1/javadoc/junit/framework/Assert.html
 * 
 * !! Alle Testfälle müssen mit "test..." beginnen.
 */
public class SpielerTest extends TestCase {
    public SpielerTest() {
        super("Spieler");
    }

    public void testSpielerHatStandardNamen() {
        Spieler spieler = new Spieler();
        assertEquals(spieler.getName(), "Namenloser Held");
    }

    public void testSpielerHatStandardSkin() {
        Spieler spieler = new Spieler();
        assertEquals(spieler.getSchreibtischSkin(), SchreibtischSkin.BACKGROUND_BASE);
    }

    public void testSpielerHatLocher() {
        Spieler spieler = new Spieler();
        assertNotNull(spieler.getLocher());
    }
    
    public void testSpielerHatKonfetti() {
        Spieler spieler = new Spieler();
        assertNotNull(spieler.getKonfetti());
    }

    public void testSpielerKonfettiIstSortiert() {
        Spieler spieler = new Spieler();
        // Erstellen ...
        ArrayList<Konfetti> konfetti = spieler.getKonfetti();
        Konfetti weissKonfetti = new Konfetti(Farbe.WEISS);
        Konfetti rotKonfetti1 = new Konfetti(Farbe.ROT);
        Konfetti rotKonfetti2 = new Konfetti(Farbe.ROT);
        konfetti.add(new Konfetti(Farbe.ORANGE));
        konfetti.add(rotKonfetti1);
        konfetti.add(rotKonfetti2);
        konfetti.add(weissKonfetti);
        konfetti.add(new Konfetti(Farbe.ORANGE));
        konfetti.add(new Konfetti(Farbe.ORANGE));
        // ... Sortieren
        HashMap<Farbe, ArrayList<Konfetti>> sortiert = spieler.getKonfettiSortiert();
        assertEquals(sortiert.size(), 3);
        ArrayList<Konfetti> rot = sortiert.get(Farbe.ROT);
        assertEquals(rot.size(), 2);
        assertTrue(rot.contains(rotKonfetti1));
        assertTrue(rot.contains(rotKonfetti2));
        ArrayList<Konfetti> weiss = sortiert.get(Farbe.WEISS);
        assertEquals(weiss.size(), 1);
        assertEquals(weiss.get(0), weissKonfetti);
    }
}