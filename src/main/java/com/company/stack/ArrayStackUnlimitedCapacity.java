package com.company.stack;

// R-7.10
public class ArrayStackUnlimitedCapacity<E> implements Stack<E> {
    public static final int CAPACITY = 16;
    E[] data = (E[]) new Object[CAPACITY];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E top() {
        return null;
    }

    @Override
    public E pop() {
        return null;
    }
}
