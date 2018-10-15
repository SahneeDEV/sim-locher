package de.wolc.spiel.locher.upgrades;

import java.util.Random;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.locher.Lochprozess;
import de.wolc.spiel.locher.SimLocher;
import de.wolc.spiel.papier.Konfetti;

/**
 * Erzeugt bei jedem Lochprozess welcher bereits Konfetti erzeugt hat eine
 * Zufällige Menge an zusätzlichem Konfetti.
 */
public class UpgradeWeissesLoch extends LocherUpgrade {
    private static final Random ZUFALL = new Random();

    private int min, max;

    public UpgradeWeissesLoch(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Wieviel Konfetti wird minimal zusätzlich erzeugt?
     * @return Die minimale Anzahl
     */
    public int getMin() {
        return min;
    }

    /**
     * Wieviel Konfetti wird maximal zusätzlich erzeugt? 
     * @return Die maximale Anzahl
     */
    public int getMax() {
        return max;
    }

    @Override
    public void upgradeLochprozess(SimLocher locher, Lochprozess prozess) {
        // Nur Konfetti erzeugen wenn wir bereits welches haben.
        if (prozess.getKonfetti().size() == 0) {
            return;
        }
        // Wir erzeugen eine zufällige Anzahl an Konfetti.
        int anzahl = min + ZUFALL.nextInt(max);
        for (int i = 0; i < anzahl; i++) {
            // Jedes Konfetti (Zufällige Farbe) dem Lochprozess hinzufügen.
            Konfetti konfetti = new Konfetti(Farbe.zufallsfarbe());
            prozess.getKonfetti().add(konfetti);
        }
    }
}