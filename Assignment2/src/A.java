public class A {

    B b;
    int x;
    double d;
    boolean bo;
    long l;
    float f;
    char c;

    public A() {
        b = new B();
        x = 0;
        d = 6.9;
        bo = true;
        l = 1L;
        f = 0.69f;
        c = 'c';
    }

    public int sumValues(int x) {
        System.out.println("METHOD CALLED");
        return x + x + b.y + b.c.z;
    }

    public boolean compareFields() {
        System.out.println("RUNNNNNNNNNN");
        return x > 0 && d > 0.0 && l > 0L && f > 0f && bo;
    }

}
