Assignment 4: Dynamic Method Analysis

I used Java agents and ASM to dynamically compute method coverage for a given Input.java program. I created a new Java agent, MethodCoverageAgent.java, and a ClassFileTransformer, CFT.java, in order to transform classes defined in the input file. To actually, perform the transformation, I created a ClassVisitor, ClassMethodVisitor.java, in ASM that adds code to each method so that method coverage is dynamically computed. I collect information for each method in a separate class, MethodCounter.java, which keeps track of the methods loaded and the number of times each method in executed. When a class is loaded, the methods are added to this tracker and each method gets code added that will dynamically increment of the count at the beginning of that method. Before the main method in the Input class returns, I write the results to a file called results.txt.

To run my code, you can use the run.sh script provided. It will compile the classes, create the Java agent jar, and then execute the Input main method. The results are written to results.txt in the format of "<class_name>.<method_name> <COUNT>" in alphabetical order, showing the method coverage during execution. I tested it with static and instance methods, interfaces and abstract classes, nested method calls, and recursion.

I do not support methods within the same class with the same name (there counts would be combined) but my implementation can easily be extended to do so by also taking into account the method arguments (the method descriptor). I also do not show constructor calls ("<init>" methods), which can also be easily implemented by taking out the hardcoded filter for this in ClassMethodVisitor. 

TLDR: ./run.sh
