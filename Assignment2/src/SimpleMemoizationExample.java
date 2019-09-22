public class SimpleMemoizationExample {

    public static void main(String[] args) {
        A a = new A();
        a.b.c.z = 20;
        //System.out.println(a.sumValues(0));
        System.out.println(a.compareFields());
        a.b.c.z = 10;
        //System.out.println(a.sumValues(0));
        System.out.println(a.compareFields());
        a.b.c.z = 20;
        //System.out.println(a.sumValues(0));
        System.out.println(a.compareFields());
    }

}
