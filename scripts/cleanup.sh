#!/bin/bash
echo Cleaning World of Lochercraft ...
find ../src -name "*.class" -delete
find ../src -name "*.ctx" -delete
rm -rf ../target
