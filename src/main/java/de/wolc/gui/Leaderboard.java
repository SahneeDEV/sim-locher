package de.wolc.gui;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import de.wolc.spiel.Spieler;

/**
 * Das sim-locher Leaderboard.
 */
public class Leaderboard {

    private String name;
    private int punkte;

    public Leaderboard(String name, int punkte) {
        this.name = name;
        this.punkte = punkte;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the punkte
     */
    public int getPunkte() {
        return punkte;
    }

    /**
     * @param punkte the punkte to set
     */
    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public static Leaderboard scoreSenden(Spieler spieler) throws Exception {
        // Request erzeugen
        URL url = new URL("https://sim-locher.herokuapp.com/api/leaderboard");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml");
            con.setUseCaches(false);
            con.setDoOutput(true);
            // XML schreiben
            Document doc = xml();
            Element root = doc.createElement("root");
            doc.appendChild(root);
            Element name = doc.createElement("name");
            name.setTextContent(spieler.getName());
            root.appendChild(name);
            Element punkte = doc.createElement("punkte");
            punkte.setTextContent(((Integer)spieler.getKonfetti().size()).toString());
            root.appendChild(punkte);
            // Senden
            xmlStream(doc, con.getOutputStream());
            con.getOutputStream().flush();
            con.getOutputStream().close();
            // Antwort
            Document antwort = xml(con.getInputStream());
            return leaderboardLesen((Element)antwort.getFirstChild());
        }
        finally {
            con.disconnect();
        }
    }

    private static Leaderboard leaderboardLesen(Element element) {
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        int punkte = Integer.parseInt(element.getElementsByTagName("punkte").item(0).getTextContent());
        return new Leaderboard(name, punkte);
    }

    private static void xmlStream(Document doc, OutputStream stream) throws Exception {
        DOMSource domSource = new DOMSource(doc);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StreamResult result = new StreamResult(stream);
        transformer.transform(domSource, result);
    }

    private static Document xml(InputStream stream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document doc = db.parse(stream);
        return doc;
    }

    private static Document xml() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document doc = db.newDocument();
        return doc;
    }
}
