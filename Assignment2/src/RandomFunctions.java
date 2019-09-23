public class RandomFunctions {

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
        return s.charAt(i);
    }

    public static double areaTriangle(double b, double h) {
        return 0.5 * b * h;
    }

    public static boolean isPositive(int i) {
        return i > 0;
    }

    public static float floatMath(float a, float b, float c, int i) {
        float sum = 0;
        for(; i >=0; i--) {
            sum += a + b + c;
        }
        return sum;
    }

    public static long longMath(long a, long b, long c, int i) {
        long sum = 0;
        for(; i >= 0; i--) {
            sum += a + b + c;
        }
        return sum;
    }

}
