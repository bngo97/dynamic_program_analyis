import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.report.ConsolePublisher;
import gov.nasa.jpf.report.Publisher;
import gov.nasa.jpf.report.PublisherExtension;
import gov.nasa.jpf.vm.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class CodeCoverageListener extends ListenerAdapter implements PublisherExtension {

    Map<String, Set<Integer>> classLineCoverage;

    public CodeCoverageListener(Config config, JPF jpf) {
        classLineCoverage = new TreeMap<>(
                (a, b) -> a.toLowerCase().compareTo(b.toLowerCase())
        );
        jpf.addPublisherExtension(ConsolePublisher.class, this);
    }

    @Override
    public void executeInstruction(VM vm, ThreadInfo currentThread, Instruction instructionToExecute) {
        MethodInfo mi = currentThread.getTopFrameMethodInfo();
        ClassInfo ci = mi.getClassInfo();
        String className = ci.getName();
        String packageName = ci.getPackageName();
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
                !packageName.startsWith("gov.");
    }

    @Override
    public void publishFinished (Publisher publisher) {
        writeCoverageResults();
    }

    private void writeCoverageResults() {
        try {
            PrintWriter pw = new PrintWriter(new File("coverage.txt"));
            for(Map.Entry<String, Set<Integer>> coverages : classLineCoverage.entrySet()) {
                pw.println(coverages.getKey() + " class covered lines:");
                for(Integer line : coverages.getValue()) {
                    pw.println(line);
                }
                pw.println();
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

