public class A {

    B b;
    int x;
    double d;
    boolean bo;
    long l;
    float f;
    char c;

    static int callCount = 0;

    public A(int x, int y, int z) {
        b = new B(y, z);
        this.x = x;
        d = 6.9;
        bo = true;
        l = 1L;
        f = 0.69f;
        c = 'c';
    }

    public int sumValues(int a) {
        callCount++;
        return a + this.x + b.y + b.c.z;
    }

}
