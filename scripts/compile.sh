#!/bin/sh
echo Compiling World of Lochercraft ...
find .. -name "*.class" -delete
find .. -name "*.ctx" -delete
find .. -name "*.java" > sources.txt
javac @sources.txt
