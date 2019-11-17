#!/bin/bash
  
javac -cp libs/* ClassMethodVisitor.java CFT.java MethodCoverageAgent.java C.java
jar cfvm agent.jar META-INF/MANIFEST.MF *.class

java -javaagent:agent.jar -cp libs/* C
