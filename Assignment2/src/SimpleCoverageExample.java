
public class SimpleCoverageExample {

    public static void main(String[] args) throws IllegalAccessException {
        A a = new A();
        a.b.z = 5;
        int sum = a.sumValues(0);
        if(sum < 0) {
            sum *= -1;
        }

        int add = SimpleCalculator.add(1, 1);
        int multiply = SimpleCalculator.multiply(1, 1);
        int sub = SimpleCalculator.sub(add, multiply);
        SimpleCalculator.sub(sub, sub);

        int[] arr = {1, 2, 3, 4, 5};
        SortTools.find(arr, 5, 3);
        SortTools.isSorted(arr, 5);
    }

}
