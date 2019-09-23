
public class SimpleCoverageExample {

    public static void main(String[] args) throws IllegalAccessException {
        A a = new A(1,2,3);
        int sum = a.sumValues(4);
        if(sum < 0) {
            sum *= -1;
        }

        int add = RandomFunctions.add(1, 1);
        int multiply = RandomFunctions.multiply(1, 1);
        int sub = RandomFunctions.sub(add, multiply);
        RandomFunctions.sub(sub, sub);
        RandomFunctions.areaTriangle(10.0, 10.0);

        int[] arr = {1, 2, 3, 4, 5};
        SortTools.find(arr, 5, 3);
        SortTools.isSorted(arr, 5);
    }

}
