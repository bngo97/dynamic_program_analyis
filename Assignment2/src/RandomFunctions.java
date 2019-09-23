public class RandomFunctions {

    static int charAtCount = 0;
    static int areaTriangleCount = 0;
    static int isPositiveCount = 0;
    static int floatMathCount = 0;
    static int longMathCount = 0;

    public static int add(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a*b;
    }

    public static int divide(int a, int b) {
        return a/b;
    }

    public static char charAt(String s, int i) {
        charAtCount++;
        return s.charAt(i);
    }

    public static double areaTriangle(double b, double h) {
        areaTriangleCount++;
        return 0.5 * b * h;
    }

    public static boolean isPositive(int i) {
        isPositiveCount++;
        return i > 0;
    }

    public static float floatMath(float a, float b, float c, int i) {
        floatMathCount++;
        float sum = 0;
        for(; i >=0; i--) {
            sum += a + b + c;
        }
        return sum;
    }

    public static long longMath(long a, long b, long c, int i) {
        longMathCount++;
        long sum = 0;
        for(; i >= 0; i--) {
            sum += a + b + c;
        }
        return sum;
    }

}
