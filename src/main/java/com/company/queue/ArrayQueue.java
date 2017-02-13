package com.company.queue;

public class ArrayQueue<E> implements CircularQueue<E>, Cloneable {
    private static final int CAPACITY = 1000;
    private E[] data;
    private int f = 0;
    private int sz = 0;

    public ArrayQueue() { this(CAPACITY); }
    public ArrayQueue(int capacity) {
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

    @Override
    public void enqueue(E e) {
        if (size() == data.length) {
            throw new IllegalStateException("Queue is full");
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
    public ArrayQueue<E> clone() throws CloneNotSupportedException {
        ArrayQueue<E> queue = (ArrayQueue<E>) super.clone();
        queue.data = (E[]) new Object[this.data.length];
        for (int i = 0; i < size(); i++) {
            queue.data[(f + i) % data.length] = this.data[(f + i) % data.length];
        }
        return queue;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayQueue<Integer> queue = new ArrayQueue<>(6);
        queue.enqueue(1);
        queue.rotate();
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.rotate();
        queue.rotate();
        queue.rotate();
        queue.rotate();
        ArrayQueue<Integer> queueNew = queue.clone();
    }
}
