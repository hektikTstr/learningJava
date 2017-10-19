public class ArrayQueue<E> {
    private E[] arr;
    private int capacity = 8;
    private int size;
    private int queueHead;

    public ArrayQueue() {
        arr = (E[]) new Object[capacity];
        size = 0;
        queueHead = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full.");
        }
        arr[(queueHead + size) % capacity] = item;
        size++;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return arr[queueHead];
    }

    public E remove() {
        if (isEmpty()) {
            return null;
        }

        E ret = arr[queueHead];
        queueHead = (queueHead + 1) % capacity;
        size--;
        return ret;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("size = " + arrayQueue.size());
        System.out.println("isEmpty() = " + arrayQueue.isEmpty());

        System.out.println("peek() = " + arrayQueue.peek());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("add 10");
        arrayQueue.add(10);
        System.out.println("size = " + arrayQueue.size());
        System.out.println("isEmpty() = " + arrayQueue.isEmpty());
        System.out.println("peek() = " + arrayQueue.peek());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("size = " + arrayQueue.size());
        System.out.println("remove() = " + arrayQueue.remove());
        arrayQueue.add(9);
        arrayQueue.add(8);
        arrayQueue.add(7);
        arrayQueue.add(6);
        arrayQueue.add(5);
        arrayQueue.add(4);
        arrayQueue.add(3);
        arrayQueue.add(2);
        System.out.println("peek() = " + arrayQueue.peek());
        System.out.println("size = " + arrayQueue.size());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("size = " + arrayQueue.size());
        arrayQueue.add(1);
        arrayQueue.add(10);
        arrayQueue.add(11);
        arrayQueue.add(12);
        arrayQueue.add(13);
        System.out.println("size = " + arrayQueue.size());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("remove() = " + arrayQueue.remove());
        System.out.println("size = " + arrayQueue.size());
    }
}
