#!/bin/bash

java -cp javacc-7.0.4.jar javacc -OUTPUT_DIRECTORY=src lexparse.jj
javac src/*.java
