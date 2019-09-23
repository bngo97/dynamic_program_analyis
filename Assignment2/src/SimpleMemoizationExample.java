public class SimpleMemoizationExample {

    public static void main(String[] args) {
        A a = new A(5, 10, 15);
        a.sumValues(0);
        a.sumValues(0);

        a = new A(5, 10, 15);
        a.sumValues(0);

        System.out.println("Instance method A.sumValues() called 3 times, executed " + a.callCount);
    }

}
