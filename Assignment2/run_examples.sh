git clone https://github.com/javapathfinder/jpf-core.git
cp src/*.java jpf-core/src/
./gradelw
jpf-core/bin/jpf +classpath=jpf-core/ +listener=CodeCoverageListener,MemoizationListener ExampleRunner
