import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.vm.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CodeCoverageListener extends ListenerAdapter {

    Map<String, Map<String, Set<Integer>>> classLineCoverage;
    Set<String> packages;

    public CodeCoverageListener() {
        classLineCoverage = new HashMap<>();
        packages = new HashSet<>();
    }

    public CodeCoverageListener(Config config, JPF jpf) {
        //jpf.addPublisherExtension();
    }

    // java.*, gov.*, sun.*, java.*

    @Override
    public void executeInstruction(VM vm, ThreadInfo currentThread, Instruction instructionToExecute) {
        MethodInfo mi = currentThread.getTopFrameMethodInfo();
        String methodName = mi.getName();
        ClassInfo ci = mi.getClassInfo();
        String className = ci.getName();
        String packageName = ci.getPackageName();
        packages.add(ci.getPackageName());
        if(packageName.equals("")) {
            int lineNumber = instructionToExecute.getLineNumber();
            Map<String, Set<Integer>> methods = classLineCoverage.getOrDefault(className, new HashMap<String, Set<Integer>>());
            Set<Integer> lineNumbers = methods.getOrDefault(methodName, new HashSet<Integer>());
            lineNumbers.add(lineNumber);
            methods.put(methodName, lineNumbers);
            classLineCoverage.put(className, methods);
        }
    }

    public void classLoaded(VM vm, ClassInfo ci) {
        
    }

    @Override
    public void gcEnd(VM vm) {
        System.out.println(classLineCoverage);
//        System.out.println(packages);
        // OUTPUT TO FILE
    }



}

