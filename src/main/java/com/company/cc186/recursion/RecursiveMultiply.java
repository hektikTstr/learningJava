public class RecursiveMultiply {
    public static int multiply(int x, int y) {
        if (y == 1) {
            return x;
        } else {
            return x + multiply(x, y - 1);
        }
    }
}