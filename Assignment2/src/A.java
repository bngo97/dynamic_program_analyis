public class A {

    B b;
    int y;
    double d;
    boolean bo;
    long l;
    float f;

    public A() {
        b = new B();
        y = 0;
        d = 6.9;
        bo = true;
        l = 1L;
        f = 0.69f;
    }

    public int sumValues(int x) {
        return x + y + b.z;
    }

}
