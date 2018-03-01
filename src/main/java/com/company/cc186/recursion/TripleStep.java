public class TripleStep {
    public static int countWays(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
        }
    }

    public static int countWays2(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        } else {
            return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
        }
    }
    public static void main(String[] args) {
        System.out.println("countWays(11) = " + countWays(11));
        System.out.println("countWays(5) = " + countWays(5));
        System.out.println("countWays(4) = " + countWays(4));
        System.out.println("countWays2(4) = " + countWays2(4));
        System.out.println("countWays2(5) = " + countWays2(5));
        System.out.println("countWays2(11) = " + countWays2(11));
    }
}