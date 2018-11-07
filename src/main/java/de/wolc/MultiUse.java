package de.wolc;

import java.awt.GraphicsEnvironment;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Pattern;
import java.awt.GraphicsDevice;


public class MultiUse{

    private static final Random RANDOM = new Random();
    // Matches strings that start with a valid URI scheme
    private static final Pattern URL_QUICKMATCH = Pattern.compile("^\\p{Alpha}[\\p{Alnum}+.-]*:.*$");

        /**
     * Holt sich die größe aller angeschlossenen Monitore
     * @return int[width_screen1, height_screen1, width_screen2, height_screen2,...]
     */
    public int[] GetScreenSize(){
        //Define 
        GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphDev = graphEnv.getScreenDevices();
        
        //Variables
        int[] screenSizes = new int[(graphDev.length * 2)];
        int count = 0;
        
        for(int i = 0; i < graphDev.length; i++){
            screenSizes[count] = graphDev[i].getDisplayMode().getWidth();
            screenSizes[count + 1] = graphDev[i].getDisplayMode().getHeight();
            count = count + 2;
        }
        
        return screenSizes;
    }

    /**
     * Rundet die gegebene Sekundenzahl auf eine Nachkommastelle.
     * @param sekunden Die Sekunden.
     * @return Die gerundeten Sekunden.
     */
    public static double sekundenRunden(double sekunden) {
        return Math.round(sekunden * 10d) / 10d;
    }

    /**
     * Wandelt eine URL in einen von Java Verwendbaren URL-String um.
     * @param Die absolute URL vom root(de/wolc/...) aus.
     * @return Der Pfad dem man AudioClips, Images übergeben kann.
     */
    public static String url(String url) {
        if (url == null) {
            throw new NullPointerException("URL must not be null");
        }
        if (url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL must not be empty");
        }
        try {
            if (!URL_QUICKMATCH.matcher(url).matches()) {
                final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                URL resource;
                if (url.charAt(0) == '/') {
                    resource = contextClassLoader.getResource(url.substring(1));
                } else {
                    resource = contextClassLoader.getResource(url);
                }
                if (resource == null) {
                    throw new IllegalArgumentException("Invalid URL or resource not found");
                }
                return resource.toString();
            }
            return new URL(url).toString();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid URL " + url, e);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL " + url, e);
        }
    }

    /**
     * Verwandelt ein Objekt in eine String Repräsentation die der Ausgabe von "toString"(ohne Überschreibung) 
     * entspricht.
     */
    public static String standardToString(Object von) {
        return von.getClass().getName() + '@' + Integer.toHexString(von.hashCode());
    }

    /**
     * Gibt einen ZUfallswert zwischen den beiden Werten zurück.
     */
    public static double zufall(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }
}