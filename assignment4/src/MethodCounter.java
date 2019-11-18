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
    }

    public static void writeResults() {
        
    }

}
