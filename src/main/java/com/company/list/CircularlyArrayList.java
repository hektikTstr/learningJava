package com.company.list;

import org.testng.annotations.Test;

import java.util.Iterator;

// C-7.25
public class CircularlyArrayList<E> implements List<E> {
    private static final int CAPACITY = 8;
    private int size = 0;
    private E[] data;
    private int f = 0;

    public CircularlyArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public CircularlyArrayList() {
        this(CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected void checkIndex(int i, int n) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[(f + i) % CAPACITY];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[(f + i) % CAPACITY];
        data[(f + i) % CAPACITY] = e;
        return temp;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length) {
            throw new IllegalStateException("Array is full");
        }
        if (i == 0 && size != 0) {
            f = (f - 1 + CAPACITY) % CAPACITY;
        } else {
            for (int k = size; k > i; k--) {
                data[(f + k + CAPACITY) % CAPACITY] = data[(f + k + CAPACITY - 1) % CAPACITY];
            }
        }
        data[(f + i) % CAPACITY] = e;
        size++;
    }

    @Override
    public void add(E e) throws IndexOutOfBoundsException {
        add(size, e);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[(f + i) % CAPACITY];
        if (i == 0) {
            int tempIndex = f;
            f = (f + 1) % CAPACITY;
            data[tempIndex] = null;
        } else {
            for (int k = i; k < size - 1; k++) {
                data[(f + k) % CAPACITY] = data[(f + k + 1) % CAPACITY];
            }
            data[(f + size - 1) % CAPACITY] = null;
        }
        size--;
        return temp;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Test
    public void test() {
        List<Integer> list = new CircularlyArrayList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(1, 4);
        list.add(0, 0);
        list.add(0, 9);
        list.get(0);
        list.get(1);
        list.get(3);
        list.get(4);
        list.remove(0);
        list.remove(1);
        list.remove(list.size() - 1);
    }
}
