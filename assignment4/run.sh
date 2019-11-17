#!/bin/bash
  
javac -cp libs/* src/*.java
jar cfvm agent.jar META-INF/MANIFEST.MF src/*.class

javac C.java
java -javaagent:demo-agent.jar -cp libs/* C
