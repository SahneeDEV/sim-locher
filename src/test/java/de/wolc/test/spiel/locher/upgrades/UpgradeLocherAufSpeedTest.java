package de.wolc.test.spiel.locher.upgrades;

import de.wolc.MultiUse;
import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.SimLocher;
import de.wolc.spiel.locher.upgrades.UpgradeLocherAufSpeed;
import junit.framework.TestCase;

public class UpgradeLocherAufSpeedTest extends TestCase {
    public UpgradeLocherAufSpeedTest() {
        super("Upgrade: Locher auf Speed");
    }
    
    public void testReduziertCooldown() {
        SimLocher locher = new SimLocher();
        UpgradeLocherAufSpeed upgrade = new UpgradeLocherAufSpeed(1, 1);
        locher.getUpgrades().add(upgrade);

        Lochprozess prozessVanilla = new Lochprozess(locher);
        Lochprozess prozessUpgrade = locher.lochen();

        assertTrue((prozessVanilla.getCooldown() - 1) == prozessUpgrade.getCooldown());
    }
    
    public void testHatMaxUndMin() {
        UpgradeLocherAufSpeed upgrade = new UpgradeLocherAufSpeed(2, 3);

        assertEquals(upgrade.getMin(), 2d);
        assertEquals(upgrade.getMax(), 3d);
    }
    
    public void testHatToString() {
        UpgradeLocherAufSpeed upgrade = new UpgradeLocherAufSpeed(2, 3);

        assertFalse(upgrade.toString().equals(MultiUse.standardToString(upgrade)));

    }

}
