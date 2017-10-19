
public class LinkedStack<E> {
    public static class StackNode<E> {
        public E data;
        public StackNode<E> next;
        public StackNode(E data, StackNode<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size = 0;
    private StackNode<E> topNode = null;

    public E top() {
        if (topNode == null) {
            return null;
        }
        return topNode.data;
    }

    public E pop() {
        if (topNode == null) {
            return null;
        }
        E ret = topNode.data;
        topNode = topNode.next;
        size--;
        return ret;
    }

    public void push(E value) {
        StackNode<E> node = new StackNode<>(value, topNode);
        topNode = node;
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        System.out.println("stack size = " + stack.size());
        System.out.println("stack isEmpty = " + stack.isEmpty());
        System.out.println("stack top = " + stack.top());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
        System.out.println("stack push 10");
        stack.push(10);
        System.out.println("stack size = " + stack.size());
        System.out.println("stack top = " + stack.top());
        stack.push(11);
        System.out.println("stack push 11");
        stack.push(12);
        System.out.println("stack push 12");
        stack.push(13);
        System.out.println("stack push 13");
        stack.push(14);
        System.out.println("stack push 14");
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack top = " + stack.top());
        System.out.println("stack size = " + stack.size());
        stack.push(15);
        System.out.println("stack push 15");
        stack.push(16);
        System.out.println("stack push 16");
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
        System.out.println("stack pop = " + stack.pop());
        System.out.println("stack size = " + stack.size());
    }
}

