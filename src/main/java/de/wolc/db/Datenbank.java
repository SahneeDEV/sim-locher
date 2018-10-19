package de.wolc.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Eine simple, dateibasierte Datenbank. Objekte die hierin gespeichert werden müssen das {@link Serializable} Interface 
 * korrekt implementieren.
 * Diese Datenbank ist langsam da sie komplett Festplatten basierend ist.
 */
public class Datenbank {
    /**
     * Speichert das Objekt in der Datenabnk ab.
     * @param key Der Schlüssel.
     * @param objekt Das Objekt, dass gespeichert wird.
     * @throws IOException Ein Fehler ist beim Speichern aufgetreten.
     */
    public void speichern(String key, Serializable objekt) throws IOException {
        ObjectOutputStream stream = null;
        try {
            File file = new File(".db/" + key + ".ser");
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdir();
            }
            System.out.println(file.getAbsolutePath());
            stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(objekt);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     * Liest ein Objekt aus der Datenbank aus.
     * @param key Der Schlüssel des Objekts.
     * @return Das Objekt, oder null wenn kein Eintrag mit dem Schlüssel in der Datenbank existiert.
     * @throws IOException Ein Fehler ist beim Laden aufgetreten.
     * @throws ClassNotFoundException Ungültige Daten in der Datenbank.
     */
    public Serializable laden(String key) throws IOException, ClassNotFoundException {
        File file = new File(".db/" + key + ".ser");
        Serializable objekt = null;
        if (file.exists()) {
            ObjectInputStream stream = null;
            try {
                stream = new ObjectInputStream(new FileInputStream(file));
                objekt = (Serializable) stream.readObject();
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
        }
        return objekt;
    }
}
