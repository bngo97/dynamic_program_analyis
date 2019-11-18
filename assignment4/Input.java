public class Input {

    public static void main(String[] args) {
        add(0,0);
        subtract(0,0);
        AnotherClass.divide(0,0);
    }

    public static void add(int x1, int x2) {
        AnotherClass.multiply(0, 0);
    }

    public static int subtract(int x1, int x2) {
        AnotherClass.multiply(0,0);
        return x1 - x2;
    }
}

class AnotherClass {

    public static void multiply(int x1, int x2) {
    }

    public static void divide(int x1, int x2) {
        Lego lego = new Lego(x1);
        lego.getX();
    }

}

class Lego {

    int x;

    public Lego(int x) {
        this.x = x;
        System.out.println("CONSTRUCTOR");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}
