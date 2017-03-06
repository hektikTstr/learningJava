package com.company.list;

import org.testng.annotations.Test;

import java.util.Iterator;

// C-7.26
public class CircularlyResizableArrayList<E> implements List<E> {
    private static final int CAPACITY = 8;
    private int size = 0;
    private E[] data;
    private int f = 0;

    public CircularlyResizableArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public CircularlyResizableArrayList() {
        this(CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = data[(f + i) % data.length];
        }
        data = temp;
        f = 0;
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
        return data[(f + i) % data.length];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[(f + i) % data.length];
        data[(f + i) % data.length] = e;
        return temp;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length) {
            resize(2 * data.length);
        }
        if (i == 0 && size != 0) {
            f = (f - 1 + data.length) % data.length;
        } else {
            for (int k = size; k > i; k--) {
                data[(f + k) % data.length] = data[(f + k + data.length - 1) % data.length];
            }
        }
        data[(f + i) % data.length] = e;
        size++;
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[(f + i) % data.length];
        if (i == 0) {
            int tempIndex = f;
            f = (f + 1) % data.length;
            data[tempIndex] = null;
        } else {
            for (int k = i; k < size - 1; k++) {
                data[(f + k) % data.length] = data[(f + k + 1) % data.length];
            }
            data[(f + size - 1) % data.length] = null;
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
        List<Integer> list = new CircularlyResizableArrayList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(0, 0);
        list.add(0, 9);
        list.add(3, 8);
        list.add(1, 7);
        list.add(2, 6);
        list.add(0, 5);
        list.get(0);
        list.get(1);
        list.get(3);
        list.get(4);
        list.remove(0);
        list.remove(1);
        list.remove(list.size() - 1);
    }
}
