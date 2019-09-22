public class A {

    B b;
    int y;

    public A() {
        b = new B();
        y = 0;
    }

    public int sumValues(int x) {
        return x + y + b.z;
    }

}
