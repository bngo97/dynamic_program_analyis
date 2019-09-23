public class SimpleMemoizationExample {

    public static void main(String[] args) {
        A a = new A(5, 10, 15);
        a.sumValues(0);
        a.sumValues(0);

        a = new A(5, 10, 15);
        a.sumValues(0);

        System.out.println("Instance method int A.sumValues() called 3 times, executed " + a.callCount + " time");

        RandomFunctions.charAt("Hello!", 0);
        RandomFunctions.charAt("Hello!", 0);
        RandomFunctions.charAt("Hello!", 0);
        RandomFunctions.charAt("Hello!", 0);
        RandomFunctions.charAt("Hello!", 0);
        System.out.println("char charAt called 5 times, executed " + RandomFunctions.charAtCount + " time");

        RandomFunctions.isPositive(100);
        RandomFunctions.isPositive(100);
        RandomFunctions.isPositive(100);
        RandomFunctions.isPositive(100);
        RandomFunctions.isPositive(100);
        System.out.println("boolean isPositive called 5 times, executed " + RandomFunctions.isPositiveCount + " time");

        RandomFunctions.areaTriangle(10.0, 20.0);
        RandomFunctions.areaTriangle(10.0, 20.0);
        RandomFunctions.areaTriangle(10.0, 20.0);
        RandomFunctions.areaTriangle(10.0, 20.0);
        RandomFunctions.areaTriangle(10.0, 20.0);
        System.out.println("double areaTriangle called 5 times, executed " + RandomFunctions.areaTriangleCount + " time");

        RandomFunctions.floatMath(5f, 10f, 15f, 2);
        RandomFunctions.floatMath(5f, 10f, 15f, 2);
        RandomFunctions.floatMath(5f, 10f, 15f, 2);
        RandomFunctions.floatMath(5f, 10f, 15f, 2);
        RandomFunctions.floatMath(5f, 10f, 15f, 2);
        System.out.println("float floatMath called 5 times, executed " + RandomFunctions.floatMathCount + " time");

        RandomFunctions.longMath(2L, 22L, 222L, 2);
        RandomFunctions.longMath(2L, 22L, 222L, 2);
        RandomFunctions.longMath(2L, 22L, 222L, 2);
        RandomFunctions.longMath(2L, 22L, 222L, 2);
        RandomFunctions.longMath(2L, 22L, 222L, 2);
        System.out.println("long longMath called 5 times, executed " + RandomFunctions.floatMathCount + " time");

    }

}
