public class Input {

    public static void main(String[] args) {
        Calculator.multiply(10,10);
        Calculator.divide(55, 10);
        for(int i = 0; i < 9; i++) {
            recursion(10);
        }
    }

    public static void recursion(int x) {
        if(x <= 0) {
            return;
        }
        recursion(x - 1);
    }

}

class Calculator {

    public static int add(int x, int y) {
        return x + y;
    }

    public static int subtract(int x, int y) {
        return x - y;
    }

    public static int multiply(int x, int y) {
        int result = 0;
        for(int i = 0; i < y; i++) {
            result = add(result, x);
        }
        return result;
    }

    public static int divide(int x, int y) {
        int cnt = 0;
        while(x > y) {
            x = subtract(x, y);
        }
        return cnt;
    }

}
