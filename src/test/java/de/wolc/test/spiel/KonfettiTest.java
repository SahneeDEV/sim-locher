package de.wolc.test.spiel;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.papier.Konfetti;
import junit.framework.TestCase;

public class KonfettiTest extends TestCase {
    public KonfettiTest() {
        super("Konfetti");
    }

    public void testKonfettiHatFarbe() {
        Konfetti konfetti = new Konfetti(Farbe.PINK);
        assertEquals(konfetti.getFarbe(), Farbe.PINK);
    }
}
