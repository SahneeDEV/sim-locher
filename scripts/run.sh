#!/bin/sh
mvn compile
echo Running World of Lochercraft ...
find ../src -name "*.class" -delete
find ../src -name "*.ctx" -delete
cd ../target/classes
java de/wolc/gui/Gui
cd ../../scripts
