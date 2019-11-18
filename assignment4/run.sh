#!/bin/bash
  
javac -cp libs/* *.java
jar cfvm agent.jar META-INF/MANIFEST.MF *.class

java -javaagent:agent.jar -cp .:libs/* Input

