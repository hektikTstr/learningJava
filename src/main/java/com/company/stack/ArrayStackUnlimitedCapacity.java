package com.company.stack;

// R-7.10
public class ArrayStackUnlimitedCapacity<E> implements Stack<E> {
    public static final int CAPACITY = 16;
    E[] data = (E[]) new Object[CAPACITY];
    private int t = -1;

    @Override
    public int size() {
        return t + 1;
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }

    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int k = 0; k < size(); k++) {
            temp[k] = data[k];
        }
        data = temp;
    }

    @Override
    public void push(E e) {
        if (size() == data.length) {
            resize(2 * data.length);
        }
        data[size()] = e;
        t++;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E temp = data[t];
        data[t--] = null;
        return temp;
    }
}
