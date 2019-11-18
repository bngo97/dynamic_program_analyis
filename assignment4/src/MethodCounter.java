import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MethodCounter {

    static Map<String, Integer> counts;

    public static void initialize() {
        counts = new HashMap<>();
    }

    public static void incrementCount(String className, String methodName) {
        String key = className + "." + methodName;
        counts.put(key, counts.getOrDefault(key, 0));
        System.out.println(key);
    }

    public static void writeResults() {
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
