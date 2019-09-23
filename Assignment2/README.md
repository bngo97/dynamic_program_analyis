Assignment 2: Code Coverage and Memoization in JPF


I created two new listeners using the JPF framework, testing classes, and a script to help run the program.  


The first listener, CodeCoverageListener, determines which lines of code are executed within a class file and outputs the results to a text file named coverage.txt. The results are ordered based on class name and then based on line number. This is implemented by listening on every instruction executed and storing which lines were executed in a set for each class file that is executed. I filter out classes and packages that come from java, jpf, and sun so only my classes will show, without any of the setup/built-in classes. This listener implements the PublisherExtension so that I can publish the results after the code is finished executing. 


The second listener, MemoizationListener, is a memoization framework that captures the results of methods and returns those results if the method is invoked with the same arguments. It works for any method (instance or static) that returns a primitive type of int, double, float, long, char, or boolean. This is done by listening when a method is entered and exited. When a method is entered, I traverse the heap starting from "this" (if instance method) and then all of the arguments given to the method. I create a string key of all the values, represented as the string concatenation of each field's name and value. For nested reference values, I recursively search the heap to find the field values of these nested objects. I then store the result of the function mapped from this input key for each method in a class. So when a method is entered, I calculate the input key, check if the result is calculated already, and if it is, I set the return value to the already computed value I have stored and return immediately, not executing the function again. When a method exits, I store the result alongside the input key used to call the function. 


I then implemented a tester class, called ExampleRunner, that calls a series of functions I implemented in order to test the functionality of the listeners. For the CodeCoverageListener, you can see the results in the coverage.txt file outputted. For the memoization listener, I call the functions repeatedly and track how many times the function is actually executed by putting a counter in the function. I do this for static methods and instance methods of every single primitive return type. I also test a nested object input on an instance method that has 3 total objects nested within each other. 


There is a script that allows you to run my code directly called run_examples.sh. The script clones the jpf-core repository into the current directory and moves all my source files into the jpf-core source folder. The listeners are copied into jpf-core/src/main/gov/nasa/jpf/listener/ while the example classes are copied into jpf-core/src/examples/. (I decided to clone the jpf repo in this script because when working locally, there are a bunch of path dependencies that are involved, so I found it easiest for new users to just use the jpf directory directly so that there are no path dependencies). I then build the repository by running ./gradlew in the jpf-core folder and then run the code using bin/jpf and attaching both my listeners to the ExampleRunner main. I copy the code coverage results back to the main Assignment2 directory while output for the memoization listener is outputted to the screen. For memoization, you can see I call each function 5 times but each one is only executed once. You can see the lines covered in coverage.txt. 


TLDR: ./run_examples.sh
