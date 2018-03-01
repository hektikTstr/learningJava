import java.util.Arrays;
import java.util.Stack;

public class HanoiTower {
    public static void move(int n, char start, char end, char aux) {
        if (n == 1) {
            System.out.println(String.format("move 1 from %c to %c", start, end));
        } else {
            move(n - 1, start, aux, end);
            move(1, start, end, aux);
            move(n - 1, aux, end, start);
        }
    }

    public static void main(String[] args) {
        // move(3, 'A', 'B', 'C');
        Tower a = new Tower(5);
        Tower b = new Tower(0);
        Tower c = new Tower(0);
        move(5, a, b, c);
        System.out.println(Arrays.toString(b.stack.toArray()));
        System.out.println(Arrays.toString(a.stack.toArray()));
        System.out.println(Arrays.toString(c.stack.toArray()));
        System.out.println(b.stack.peek());
    }

    public static class Tower {
        public Stack<Integer> stack = new Stack<>();
        public Tower(int n) {
            while (n > 0) {
                stack.push(n--);
            }
        }

    }

    public static void move(int n, Tower a, Tower b, Tower c) {
        if (n == 1) {
            b.stack.push(a.stack.pop());
        } else {
            move(n - 1, a, c, b);
            move(1, a, b, c);
            move(n - 1, c, b, a);
        }
    }
}