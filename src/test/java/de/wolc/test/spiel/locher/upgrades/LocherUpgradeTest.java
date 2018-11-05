package de.wolc.test.spiel.locher.upgrades;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.SimLocher;
import de.wolc.spiel.locher.upgrades.LocherUpgrade;
import junit.framework.TestCase;

public class LocherUpgradeTest extends TestCase {
    private class DemoUpgrade extends LocherUpgrade {

        private static final long serialVersionUID = 1L;

        public DemoUpgrade() {
            super("Demo Upgrade", new Preis(Farbe.ROT, 10));
        }

        @Override
        public String toString() {
            return "Demo Upgrade toString";
        }

    }

    public LocherUpgradeTest() {
        super("Locher Upgrade");
    }
    
    public void testStandardOhneEffekt() {
        SimLocher locher = new SimLocher();
        DemoUpgrade upgrade = new DemoUpgrade();
        locher.getUpgrades().add(upgrade);

        Lochprozess prozessVanilla = new Lochprozess(locher);
        Lochprozess prozessUpgrade = locher.lochen();

        assertEquals(prozessVanilla.getLocher(), prozessUpgrade.getLocher());
        assertEquals(prozessVanilla.getCooldown(), prozessUpgrade.getCooldown());
        assertEquals(prozessVanilla.getWarZuGross(), prozessUpgrade.getWarZuGross());
        assertEquals(prozessVanilla.getKonfetti().size(), prozessUpgrade.getKonfetti().size());
    }
    
    public void testHatGuiName() {
        DemoUpgrade upgrade = new DemoUpgrade();

        assertEquals(upgrade.getGuiName(), "Demo Upgrade");
    }
    
    public void testPreis() {
        DemoUpgrade upgrade = new DemoUpgrade();

        assertEquals(upgrade.getPreis().getKosten().size(), 1);
        assertEquals((int)upgrade.getPreis().getKosten().get(Farbe.ROT), 10);
    }

}
