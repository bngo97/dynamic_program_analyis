import java.lang.reflect.Field;

public class SimpleTest {

    int i;
    int j;

    public SimpleTest() {
        i = 0;
        j = 1;
    }

    public static void main(String[] args) throws IllegalAccessException {
        //System.out.println(add(1,1));
        System.out.println(add(1,1));
        int x;
        if(SimpleTest2.sub(5, 10) > 0) {
            x = 1;
        } else {
            x = -1;
        }
        //System.out.println(x);

        SimpleTest test = new SimpleTest();
        for(Field field :test.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(test);
        }

    }

    public static int add(int a, int b) {
        return a + b;
    }

}
