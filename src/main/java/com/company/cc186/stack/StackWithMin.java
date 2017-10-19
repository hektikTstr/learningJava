
public class StackWithMin<E> extends LinkedStack<E> {
    private LinkedStack<E> minStack = new LinkedStack<>();
    private DefaultComparator<E> comp = new DefaultComparator<>();

    public E min() {
       return minStack.top();
    }

    public E pop() {
        E ret = super.pop();
        if (ret != null && compare(ret, min()) == 0) {
            minStack.pop();
        }
        return ret;
    }

    public void push(E value) {
        super.push(value);
        if (minStack.isEmpty() || compare(minStack.top(), value) > 0) {
            minStack.push(value);
        }
    }

    private int compare(E a, E b) {
        return comp.compare(a, b);
    }

    public static void main(String[] args) {
        StackWithMin<Integer> stack = new StackWithMin<>();
        System.out.println("stack min = " + stack.min());
        stack.push(10);
        System.out.println("stack.push(10)");
        System.out.println("stack min = " + stack.min());
        stack.push(9);
        System.out.println("stack.push(9)");
        System.out.println("stack min = " + stack.min());
        stack.push(12);
        System.out.println("stack.push(12)");
        System.out.println("stack min = " + stack.min());
        stack.push(7);
        System.out.println("stack.push(7)");
        System.out.println("stack min = " + stack.min());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack min = " + stack.min());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack min = " + stack.min());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack min = " + stack.min());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack min = " + stack.min());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack min = " + stack.min());
    }
}

