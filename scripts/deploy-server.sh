#!/bin/bash
cd ..
git subtree --prefix server push heroku master
echo -n 'Warte auf Serverneustart(15s), lese dann logs aus.'
sleep 15s
heroku logs
