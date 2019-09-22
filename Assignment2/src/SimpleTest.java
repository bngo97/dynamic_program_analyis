
public class SimpleTest {

    int i;
    int j;

    public SimpleTest() {
        i = 0;
        j = 1;
    }

    public static void main(String[] args) throws IllegalAccessException {
        //System.out.println(add(1,1));
//        System.out.println(add(1,1));
//        int x;
//        if(SimpleTest2.sub(5, 10) > 0) {
//            x = 1;
//        } else {
//            x = -1;
//        }
//        //System.out.println(x);
//
//        SimpleTest test = new SimpleTest();
//        for(Field field :test.getClass().getDeclaredFields()) {
//            field.setAccessible(true);
//            Object value = field.get(test);
//        }
        A a1 = new A();
        a1.b.z = 5;
        System.out.println(test(new A(), 50, a1));
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int test(A a, int x, A a1) {
        System.out.println("HELLO");
        return 420;
    }

}
