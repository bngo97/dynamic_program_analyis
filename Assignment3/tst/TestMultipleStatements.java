public class TestMultipleStatements {
    public static void main(String[] args) {
        int x = 0;
        for(int i = 0; i < 10; i++) {
            if(i == 5) {
                x++;
                while(x > 0) {
                    x--;
                    System.out.println(x);
                }
            } else {
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
            }
            x++;
        }
        x++;
        x--;
        while(x > 0) {
            x--;
            System.out.println(x);
        }
        x = 10;
    }
}