Assignment 3: Control Flow Graphs with ASM

I created a class and method visitor using ASM that will create a control flow graph representation of a single main method that does not contain any method invocations, but can have any other code such as if/else statements, for loops, while loops, do while loops, switch cases, and breaks. CfgVisitor is a class visitor that takes a .class file from the command line and will produce a control flow graph for the main method in that class file. CfgMethodVisitor does the actual logic of producing the control flow graph. The main methodology is looking at Labels and Jumps to Labels to determine execution branches.


To run my code, you can use the run_tests.sh script provided. It will compile all the files in the src folder (my code) and all the test samples in the tst folder. It will then run the control flow graph program on every .class file produced in the tst folder and output the results to .txt files in the results folder. 


TLDR: ./run_tests.sh
