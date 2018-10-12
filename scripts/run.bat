@echo off
call compile.bat
echo Running World of Lochercraft ...
cd ../de/wolc/gui/
java Gui
cd ../../../scripts
