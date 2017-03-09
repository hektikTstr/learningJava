package com.company.queue;

// C-7.35
public class ArrayResizableQueue<E> implements CircularQueue<E>, Cloneable {
    private static final int CAPACITY = 4;
    private E[] data;
    private int f = 0;
    private int sz = 0;

    public ArrayResizableQueue() { this(CAPACITY); }
    public ArrayResizableQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return sz == 0;
    }

    private void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < sz; i++) {
            temp[i] = data[(f + i) % data.length];
        }
        data = temp;
        f = 0;
    }

    @Override
    public void enqueue(E e) {
        if (size() == data.length) {
            resize(2 * data.length);
        }
        int avail = (f + sz) % data.length;
        data[avail] = e;
        sz++;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[f];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        sz--;
        return answer;
    }

    //R-6.15
    @Override
    public void rotate() {
        if (isEmpty() || size() == 1) {
            return;
        }
        int avail = (f + sz) % data.length;
        data[avail] = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
    }

    //C-6.28
    @Override
    public ArrayResizableQueue<E> clone() throws CloneNotSupportedException {
        ArrayResizableQueue<E> queue = (ArrayResizableQueue<E>) super.clone();
        queue.data = (E[]) new Object[this.data.length];
        for (int i = 0; i < size(); i++) {
            queue.data[(f + i) % data.length] = this.data[(f + i) % data.length];
        }
        return queue;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayResizableQueue<Integer> queue = new ArrayResizableQueue<>();
        queue.enqueue(1);
        queue.rotate();
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.rotate();
        queue.rotate();
        queue.rotate();
        queue.rotate();
        ArrayResizableQueue<Integer> queueNew = queue.clone();
    }
}
