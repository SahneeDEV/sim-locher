package de.wolc.spiel.locher.upgrades;

import de.wolc.spiel.locher.SimLocher;

/**
 * Der Panzer Stanzer erhöht die Stärke der Stanzer um diesen zu erlauben über 10000 Blätter auf einal zu lochen.
 */
public class UpgradePanzerStanzer extends LocherUpgrade {
    @Override
    public int upgradeStaerke(SimLocher locher, int staerke) {
        return staerke + 10000;
    }
}
