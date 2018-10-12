@echo off
mvn compile
echo Running World of Lochercraft ...
cd ../target/classes
java de/wolc/gui/Gui
cd ../../scripts
