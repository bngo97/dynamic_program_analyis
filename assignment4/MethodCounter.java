import java.util.TreeMap;
import java.util.Map;

public class MethodCounter {

    static Map<String, Integer> counts;

    public static void initialize() {
        //System.out.println("INITIALIZE");
        counts = new TreeMap<>();
    }

    public static void addMethod(String className, String methodName) {
        String key = className + "." + methodName;
        counts.put(key, 0);
        //System.out.println("added " + key);
    }

    public static void incrementCount(String className, String methodName) {
        String key = className + "." + methodName;
        counts.put(key, counts.getOrDefault(key, 0) + 1);
        //System.out.println("incremented " + key);
    }

    public static void writeResults() {
        //System.out.println("WRITE RESULTS");
        System.out.println(counts);
    }

}
