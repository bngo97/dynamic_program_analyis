# LexParse

Lexing, Parsing, and Semantic Analysis

I created a new parser, as specified by the instruction, called MyParser. It is defined in lexparse.jj. To compile the .jj file, run the compile script using ./compile.sh. This will run javacc on the .jj file using the javacc 7.0.4 jar located in this repo, output the files into the src/ folder, and then compile all the .java files in the source folder.  Also located in the src/ folder are Node classes that I used to build the Abstract Syntax Tree. Each Node class is prefixed with "Node" in the class name. I also created a visitor called MyVisitor that traverses the abstract syntax tree for semantic analysis. The syntax follows exactly what is specified in the instructions. The semantic analyzer does a lot of things such as check for a single main method, mulitple variables with the same name in the same scope, variable usage without declaration, types (can't use non-primitive type if class is not defined), inherited and extended classes are actually defined, all functions are implemented correctly for implemented interfaces, and more.  

There are 2 testing scripts included in the repo. The run_all_tests.sh will run all the tests in the tst/ folder. Inside the tst/ folder, there are 2 more folders, one for tests that should pass and another for tests that should fail. 

The main function in MyParser takes multiple inputs, including the input mode, the actual input, and the expected behaviour of the test (pass/fail). The first 2 flags are mandatory, while the third is optional. The first flag is either "-t" for text input from the console from or "-f" to specify a file path. The second argument is either the text input typed in between "" or the file path to a file. The third argument is either 0 if the test should fail, or it is 1 if the test should pass. If the third option is designated, you will not see why the test failed/passed, but only see if the behaviour is as expected. Running it without the third flag will give you error messages. To run a single test, you can use the test.sh script, which takes the first two arguments.


./compile.sh <br/>
./run_all_tests.sh <br/>
./test.sh -f filepath <br/>
./test.sh -t "program P {void main(){}}" <br/>

EE 379K Dynamic Program Analysis 

Brendan Ngo
