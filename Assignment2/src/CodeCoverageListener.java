import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.report.ConsolePublisher;
import gov.nasa.jpf.report.Publisher;
import gov.nasa.jpf.report.PublisherExtension;
import gov.nasa.jpf.vm.*;

import java.util.*;

public class CodeCoverageListener extends ListenerAdapter implements PublisherExtension {

    Map<String, Set<Integer>> classLineCoverage;
    Set<String> packages;

    public CodeCoverageListener() {
        classLineCoverage = new HashMap<>();
        packages = new HashSet<>();
    }

    public CodeCoverageListener(Config config, JPF jpf) {
        //jpf.addPublisherExtension();
        classLineCoverage = new HashMap<>();
        packages = new HashSet<>();
        jpf.addPublisherExtension(ConsolePublisher.class, this);
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
        if(filterPackageName(packageName)) {
            int lineNumber = instructionToExecute.getLineNumber();
            Set<Integer> lineNumbers = classLineCoverage.getOrDefault(className, new TreeSet<Integer>());
            lineNumbers.add(lineNumber);
            classLineCoverage.put(className, lineNumbers);
        }
    }

    private boolean filterPackageName(String packageName) {
        return !packageName.startsWith("java.") &&
                !packageName.startsWith("sun.") &&
                !packageName.startsWith("gov.nasa.jpf");
    }

    @Override
    public void publishFinished (Publisher publisher) {
        writeCoverageResults();
    }

    private void writeCoverageResults() {
        System.out.println(packages);
        System.out.println(classLineCoverage);
    }

}

