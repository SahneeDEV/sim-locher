const dotenv = require("dotenv");
const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("express-xml-bodyparser");
const BadWordFilter = require("bad-words");
const xml2js = require("xml2js");

const js2xml = new xml2js.Builder();

// Für die lokale Entwicklung kann eine .env Datei angelegt werden welche Umgebungsvariablen beinhaltet.
// Diese wird nicht commited. z.B:
// DB=mongodb://user:password@host:port/datenbank
// PORT=12345
dotenv.config();

// Port für Kommunikation mit dem Server
const PORT = process.env.PORT || 5000

// Zur Datenbank verbinden
const dbVerbinden = () => {
  // Datenbank wird via Umgebungsvariable gesetzt da wir sonst das Password für die Datenbank in den Code schreiben 
  // müssten.
  const DB = process.env.DB || "mongodb://localhost:27017/sim-locher";
  console.log(`Verbinde zur Datenbank...`);
  mongoose.connect(DB);
};
dbVerbinden();

// Modelle für Datensätze - Name des Spielers ist ID
const Leaderboard = mongoose.model("Leaderboard", new mongoose.Schema({
  _id: String,
  punkte: Number
}));

// Bad Words Filter erstellen
const filter = new BadWordFilter();

const convertLeaderboard = leaderboard => {
  const wasArray = Array.isArray(leaderboard);
  leaderboard = wasArray ? leaderboard : [leaderboard];
  const values = leaderboard.reduce((a, value) => {
    return {...a, [value._id]: {
      name: value._id,
      punkte: value.punkte
    }}
  }, {});
  return wasArray ? values : values[leaderboard[0]._id];
};

mongoose.connection.once("open", () => {
  console.log(`Datenbank verbunden`);
  express()
    .use(bodyParser({ trim: true }))
    .use((req, res, next) => {
      res.xml = payload => res
        .contentType("text/xml")
        .send(js2xml.buildObject({ root: payload }));
      next();
    })

    // Weiterleitung auf GitHub für index Dokument
    .get("/", (req, res) => {
      return res.redirect("https://github.com/PatrickSachs/sim-locher/");
    })

    // Score per POST speichern. (POST /ap/leaderboard { "name": "Spielername", "punkte": 3431 })
    .post("/api/leaderboard", (req, res) => {
      console.log("got", req.body)
      let name = req.body.root.name[0] || req.body.root.name;
      const punkte = parseInt(req.body.root.punkte[0] || req.body.root.punkte);
      // Sicherstellen dass man keine ungültigen Datentypen sendet, was den Server zum Absturz bringen könnte.
      if (typeof name !== "string" || typeof punkte !== "number" || punkte < 0 || name.length < 1 
        || Number.isNaN(punkte) || punkte % 1 !== 0) {
        return res.xml({ fehler: "Ungültige Daten" }).status(400).end();
      }
      name = filter.clean(name);
      // Alten Datensatz suchen
      Leaderboard.findById(name, (err, leaderboard) => {
        if (err) {
          console.error(err);
          return res.xml({ fehler: err.errmsg }).status(500).end();
        }
        // Nicht speichern wenn der Spieler einen älteren, höheren Punktestand hat.
        if (leaderboard !== null && leaderboard.punkte >= punkte) {
          return res.xml({ name, punkte: leaderboard.punkte }).status(200).end();
        }
        // Neuen Datensatz erstellen oder alten anpassen
        if (leaderboard === null) {
          leaderboard = new Leaderboard({
            _id: name,
            punkte
          });
        } else {
          leaderboard.punkte = punkte;
        }
        // Speichern
        leaderboard.save((err, leaderboard) => {
          if (err) {
            console.error(err);
            return res.xml({ fehler: err.errmsg }).status(500).end();
          }
          res.xml(convertLeaderboard(leaderboard)).status(200).end();
        });
      });
    })

    .get("/api/leaderboard", (req, res) => {
      Leaderboard.find((err, leaderboard) => {
        if (err) {
          console.error(err);
          return res.xml({ fehler: err.errmsg }).status(500).end();
        }
        res.xml(convertLeaderboard(leaderboard)).status(200).end();
      });
    })

    .get("/api/leaderboard/top/:top", (req, res) => {
      const top = parseInt(req.params.top);
      if (typeof top !== "number" || top < 1 || Number.isNaN(top)) {
        return res.xml({ fehler: "Ungültige Daten" }).status(400).end();
      }
      Leaderboard.find({}).sort({ punkte: -1 }).limit(top).exec((err, leaderboard) => {
        if (err) {
          console.error(err);
          return res.xml({ fehler: err.errmsg }).status(500).end();
        }
        res.xml(convertLeaderboard(leaderboard)).status(200).end();
      });
    })

    .get("/api/leaderboard/spieler/:name", (req, res) => {
      const name = filter.clean(req.params.name);
      Leaderboard.findById(name, (err, leaderboard) => {
        if (err) {
          console.error(err);
          return res.xml({ fehler: err.errmsg }).status(500).end();
        }
        if (leaderboard == null) {
          return res.xml({ fehler: "Kein Spieler mit diesem Namen" }).status(400).end();
        }
        return res.xml(convertLeaderboard(leaderboard)).status(200).end();
      });
    })
    .listen(PORT, () => console.log(`Server auf Port ${PORT} gestartet`));
});
