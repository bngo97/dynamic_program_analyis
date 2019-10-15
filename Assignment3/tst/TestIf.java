public class TestIf {
    public static void main(String[] args) {
        int x = 0;
        x--;
        if(x == 0) {
            x++;
            x++;
            x++;
            x--;
        }
        x--;
        x++;
        x++;
    }
}