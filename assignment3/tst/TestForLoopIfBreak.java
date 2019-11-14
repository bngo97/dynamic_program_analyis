public class TestForLoopIfBreak {
    public static void main(String[] args) {
        int x = 0;
        for(int i = 0; i < 10; i++) {
            if(i == 5) {
                x++;
            } else {
                break;
            }
            x++;
        }
        x--;
    }
}