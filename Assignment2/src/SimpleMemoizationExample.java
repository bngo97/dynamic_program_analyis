public class SimpleMemoizationExample {

    public static void main(String[] args) {
        A a = new A();
        a.sumValues(0);
        SimpleCalculator.add(1,1);
        //System.out.println("TEST B = " + testB());
        //System.out.println("TEST D = " + testD());
        //testB();
        //testD();
        //testL();
        //testF();
        //System.out.println(testC());
    }

    public static boolean testB() {
        return false;
    }

    public static double testD() {
        return 51.0;
    }

    public static long testL() {
        return 0L;
    }

    public static float testF() {
        return 96f;
    }

    public static char testC() {
        return 'a';
    }

}
