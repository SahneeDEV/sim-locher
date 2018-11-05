package de.wolc.test.spiel;

import de.wolc.spiel.locher.SimLocher;
import de.wolc.spiel.papier.A4;
import de.wolc.spiel.papier.A5;
import de.wolc.spiel.papier.PapierStapel;
import junit.framework.TestCase;

public class LocherTest extends TestCase {
    public LocherTest() {
        super("Locher");
    }
    
    public void testStandardLeer() {
        SimLocher locher = new SimLocher();

        assertNull(locher.getStapel());
    }

    public void testHatUpgrades() {
        SimLocher locher = new SimLocher();

        assertNotNull(locher.getUpgrades());
    }

    public void testStandardFormat() {
        SimLocher locher = new SimLocher();

        assertEquals(locher.getFormat(), A4.class);
    }

    public void testKannNichtDoppeltEinlegen() {
        SimLocher locher = new SimLocher();
        PapierStapel<A4> stapel1 = new PapierStapel<>(A4.class);
        PapierStapel<A4> stapel2 = new PapierStapel<>(A4.class);
        
        locher.einlegen(stapel1);
        try {
            locher.einlegen(stapel2);
            fail("Stapel dürfen nicht eingelegt werden wenn einer drinnen ist.");
        } catch(IllegalStateException e) {
        }
    }

    public void testKannNichtFalschesFormatEinlegen() {
        SimLocher locher = new SimLocher();
        PapierStapel<A5> stapel = new PapierStapel<>(A5.class);
        
        try {
            locher.einlegen(stapel);
            fail();
        } catch(IllegalStateException e) {
        }
    }

    public void testKannNichtFormatAendernWennPapierEingelegt() {
        SimLocher locher = new SimLocher();
        PapierStapel<A4> stapel = new PapierStapel<>(A4.class);
        
        locher.einlegen(stapel);
        try {
            locher.setFormat(A5.class);
            fail("Format darf nicht geändert werden wenn ein Stapel drinnen ist.");
        } catch(IllegalStateException e) {
        }
    }

    public void testEinlegenUndEntnehmen() {
        SimLocher locher = new SimLocher();
        PapierStapel<A4> stapel1 = new PapierStapel<>(A4.class);
        PapierStapel<A4> stapel2 = new PapierStapel<>(A4.class);

        locher.einlegen(stapel1);
        assertEquals(locher.getStapel(), stapel1);
        assertEquals(locher.entnehmen(), stapel1);
        assertNull(locher.entnehmen());

        locher.einlegen(stapel1);
        assertEquals(locher.getStapel(), stapel1);
        assertEquals(locher.entnehmen(), stapel1);

        locher.einlegen(stapel2);
        assertEquals(locher.getStapel(), stapel2);
    }

    public void testLocherIstStandardNichtAufCooldown() {
        SimLocher locher = new SimLocher();

        assertEquals(locher.getCooldown(), 0d);
    }

    public void testLocherIstNachLochenAufCooldown() {
        SimLocher locher = new SimLocher();

        locher.lochen();
        assertTrue(locher.getCooldown() > 0);
    }

    public void testLocherKannNichtLochenWennAufCooldown() {
        SimLocher locher = new SimLocher();

        locher.lochen();
        try {
            locher.lochen();
            fail("Locher dürfen auf Cooldown nicht lochen.");
        } catch(IllegalStateException e) {
        }
    }
}
