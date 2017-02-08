package com.company.deque;

import java.util.Arrays;

public class ArrayDeque<E> implements Deque<E> {
    private int sz = 0;
    private int f = 0;
    private E[] data;
    private static final int CAPACITY = 1000;

    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public ArrayDeque() {
        this(CAPACITY);
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
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[f];
    }

    @Override
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return data[(f + sz - 1) % data.length];
    }

    @Override
    public void addFirst(E e) {
        if (sz == data.length) {
            throw new IllegalStateException("Queue is full");
        }
        int avail = (f - 1 + data.length) % data.length;
        data[avail] = e;
        f = avail;
        sz++;
    }

    @Override
    public void addLast(E e) {
        if (sz == data.length) {
            throw new IllegalStateException("Queue is full");
        }
        int avail = (f + sz) % data.length;
        sz++;
        data[avail] = e;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[f];
        data[f] = null;
        f++;
        sz--;
        return answer;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        int last = (f + sz - 1) % data.length;
        E answer = data[last];
        data[last] = null;
        sz--;
        return answer;
    }

    public void printQueue() {
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(5);
        queue.addFirst(3);
        queue.addFirst(7);
        queue.printQueue();
        System.out.println(queue.first());
        queue.removeLast();
        System.out.println(queue.size());
        queue.removeLast();
        queue.removeFirst();
        System.out.println(queue.size());
        queue.addFirst(6);
        System.out.println(queue.first());
        System.out.println(queue.last());
        queue.addFirst(8);
        System.out.println(queue.first());
        System.out.println(queue.last());
    }
}
