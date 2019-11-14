#!/bin/bash

javac -cp asm-all-5.2.jar src/*.java
javac tst/*.java
mkdir results
for test in tst/*.class
do
    echo $test
    java -cp asm-all-5.2.jar:./src CfgVisitor $test
done
