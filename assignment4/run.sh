#!/bin/bash
  
javac -cp lib/* *.java
jar cfvm agent.jar META-INF/MANIFEST.MF *.class

java -javaagent:agent.jar -cp .:lib/* Input

