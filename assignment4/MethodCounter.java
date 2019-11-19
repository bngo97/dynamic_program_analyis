import java.util.TreeMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MethodCounter {

    static Map<String, Integer> counts;

    public static void initialize() {
        counts = new TreeMap<>();
    }

    public static void addMethod(String className, String methodName) {
        String key = className + "." + methodName;
        counts.put(key, 0);
    }

    public static void incrementCount(String className, String methodName) {
        String key = className + "." + methodName;
        counts.put(key, counts.getOrDefault(key, 0) + 1);
    }

    public static void writeResults() {
        System.out.println(counts);
        try {
            PrintWriter pw = new PrintWriter(new File("results.txt"));
            for(Map.Entry<String, Integer> entry : counts.entrySet()) {
                pw.println(entry.getKey() + ": " + entry.getValue());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
