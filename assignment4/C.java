public class C {

    public static void main(String[] args) {
        System.out.println(Input.subtract(69,0));
        m();
    }

    public static void m() {
        D.add(1,2);
    }
}

class D {

    public static int add(int x, int y) {
        return x + y;
    }

}
