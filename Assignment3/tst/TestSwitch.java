public class TestSwitch {
    public static void main(String[] args) {
        int x = 0;
        switch(x) {
            case 0:
                x++;
            case 1:
                x--;
                x--;
            case 2:
                x += x;
            default:
                System.out.println(x);
        }
    }
}