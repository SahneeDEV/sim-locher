package de.wolc.test.spiel.locher;

import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.SimLocher;
import junit.framework.TestCase;

public class LochprozessTest extends TestCase {
    public LochprozessTest() {
        super("Lochprozess");
    }
    
    public void testHatLocher() {
        SimLocher locher = new SimLocher();
        Lochprozess prozess = new Lochprozess(locher);

        assertEquals(prozess.getLocher(), locher);
    }
    
    public void testHatKonfetti() {
        SimLocher locher = new SimLocher();
        Lochprozess prozess = new Lochprozess(locher);

        assertNotNull(prozess.getKonfetti());
    }

    public void testHatStandardCooldown() {
        SimLocher locher = new SimLocher();
        Lochprozess prozess = new Lochprozess(locher);

        assertTrue(prozess.getCooldown() > 0d);
    }

    public void testIstStandardNichtZuGross() {
        SimLocher locher = new SimLocher();
        Lochprozess prozess = new Lochprozess(locher);

        assertFalse(prozess.getWarZuGross());
    }

    public void testKannCooldownSetzen() {
        SimLocher locher = new SimLocher();
        Lochprozess prozess = new Lochprozess(locher);

        prozess.setCooldown(10d);
        assertEquals(prozess.getCooldown(), 10d);
    }

    public void testKannZuGrossSetzen() {
        SimLocher locher = new SimLocher();
        Lochprozess prozess = new Lochprozess(locher);

        prozess.setWarZuGross(true);
        assertTrue(prozess.getWarZuGross());
    }
}
