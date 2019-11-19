import java.lang.Override;

public class Input {

    public static void main(String[] args) {
        Calculator.multiply(10,10);
        Calculator.divide(55, 10);
        for(int i = 0; i < 9; i++) {
            recursion(10);
        }
        fib(7);
        fibMemoized(7, new int[8]);
        Cat cat = new Cat(10);
        for(int i = 0; i < cat.getAge(); i++) {
            cat.makeSound();
        }
        Dog dog = new Dog(10, "Retriever");
        int age = dog.getAge();
        while(age > 0) {
            dog.makeSound();
            age--;
        }
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

interface Age {
    public int getAge();
}

interface Sound {
    public String makeSound();
}

abstract class Animal implements Age {
    int age;

    public Animal(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }

}

class Dog extends Animal implements Sound {
    String breed;

    public Dog(int age, String breed) {
        super(age);
        this.breed = breed;
    }

    @Override
    public int getAge() {
        return 7 * age;
    }

    @Override
    public String makeSound() {
        return "bark";
    }
}

class Cat extends Animal implements Sound {
    public Cat(int age) {
        super(age);
    }

    @Override
    public String makeSound() {
        return "meow";
    }
}

