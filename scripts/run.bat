@echo off
call compile.bat
echo Running World of Lochercraft ...
cd ..
java de/wolc/gui/Gui
cd scripts
