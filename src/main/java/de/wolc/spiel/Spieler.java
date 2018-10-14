package de.wolc.spiel;

import java.util.ArrayList;

import de.wolc.spiel.locher.SimLocher;
import de.wolc.spiel.papier.Konfetti;

public class Spieler {
    private final SimLocher locher;
    private final ArrayList<Konfetti> konfetti;

    public Spieler() {
        this.locher = new SimLocher();
        this.konfetti = new ArrayList<Konfetti>();
    }

    public SimLocher getLocher() {
        return this.locher;
    }

    public ArrayList<Konfetti> getKonfetti() {
        return this.konfetti;
    }
}
