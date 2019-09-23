#!/bin/bash

git clone https://github.com/javapathfinder/jpf-core.git
cp src/*.java jpf-core/src/examples/
cp src/*Listener.java jpf-core/src/main/gov/nasa/jpf/listener/
cd jpf-core
./gradlew
bin/jpf +classpath=. +listener=CodeCoverageListener,MemoizationListener ExampleRunner
mv coverage.txt ../
cd ..
rm -rf jpf-core
