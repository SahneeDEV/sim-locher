echo Compiling World of Lochercraft ...
cd ..
dir *.java /s /B > scripts/sources.txt
cd scripts
javac @sources.txt
