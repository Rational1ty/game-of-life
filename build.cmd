@echo off

if not exist ./bin mkdir bin
cd src
javac -d ../bin -cp ../bin *.java
cd ..