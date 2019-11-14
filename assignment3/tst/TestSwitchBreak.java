public class TestSwitchBreak {
    public static void main(String[] args) {
        int x = 0;
        switch(x) {
            case 0:
                x++;
                break;
            case 1:
                x--;
                x--;
                break;
            case 2:
                x += x;
                break;
            default:
                System.out.println(x);
        }
        x++;
    }
}