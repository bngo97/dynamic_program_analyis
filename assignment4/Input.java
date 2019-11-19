public class Input {

    public static void main(String[] args) {
        Calculator.multiply(10,10);
        Calculator.divide(55, 10);
        for(int i = 0; i < 9; i++) {
            recursion(10);
        }
        fib(7);
        fibMemoized(7, new int[8]);
    }

    public static void recursion(int x) {
        if(x <= 0) {
            return;
        }
        recursion(x - 1);
    }

    public static int fib(int x) {
        if(x <= 1) {
            return x;
        }
        return fib(x - 1) + fib(x - 2);
    }

    public static int fibMemoized(int x, int[] memo) {
        if(x <= 1) {
            return x;
        } else if(memo[x] != 0) {
            return memo[x];
        } else {
            int result = fibMemoized(x-1, memo) + fibMemoized(x-2, memo);
            memo[x] = result;
            return result;
        }
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
