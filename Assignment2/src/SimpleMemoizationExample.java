public class SimpleMemoizationExample {

    public static void main(String[] args) {
        A a = new A();
        a.y = 20;
        System.out.println(a.sumValues(0));
        SimpleCalculator.add(1,1);
        a.y = 10;
        System.out.println(a.sumValues(0));
        a.y = 20;
        System.out.println(a.sumValues(0));
    }

}
