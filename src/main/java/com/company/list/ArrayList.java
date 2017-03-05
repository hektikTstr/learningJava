package com.company.list;

import org.testng.annotations.Test;

import java.util.*;

public class ArrayList<E> implements List<E> {
    public static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    public ArrayList() {
        this(CAPACITY);
    }

    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    @Deprecated
    public void add2(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length) {
            throw new IllegalStateException("Array is full");
        }
        for (int k = size; k > i; k--) {
            data[k] = data[k - 1];
        }
        data[i] = e;
        size++;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int k = size; k > i; k--) {
            data[k] = data[k - 1];
        }
        data[i] = e;
        size++;
    }

    // R-7.9
    public void addNewImplementation(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length) {
            E[] temp = (E[]) new Object[2 * data.length];
            for (int k = 0; k < i; k++) {
                temp[k] = data[k];
            }
            for (int k = i; k < size; k++) {
                temp[k + 1] = data[k];
            }
            data = temp;
        } else {
            for (int k = size; k > i; k--) {
                data[k] = data[k - 1];
            }
        }
        data[i] = e;
        size++;
    }


    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k++) {
            data[k] = data[k + 1];
        }
        data[size - 1] = null;
        size--;
        return temp;
    }

    protected void checkIndex(int i, int n) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private class ArrayIterator implements Iterator<E> {
        private int j = 0; // index of the next element to report
        private boolean removable = false; // can remove be called at this time?
        @Override
        public boolean hasNext() {
            return j < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (j == size) {
                throw new NoSuchElementException("No next element");
            }
            removable = true; // this element can subsequently be removed
            return data[j++]; // post-increment j, so it is ready for future call to next
        }


        /**
         * Removes the element returned by most recent call to next.
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since recent next
         */
        @Override
        public void remove() {
            if (!removable) {
                throw new IllegalStateException("nothing to remove");
            }
            ArrayList.this.remove(j - 1); // that was the last one returned
            j--; // next element has shifted one cell to the left
            removable = false; // do not allow remove again until next is called
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    // R-7.5
    public void trimToSize() {
        E[] temp = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    // R-7.18
    public boolean contains(Object o) {
        for (E e : (Iterable<E>) this) {
            if (e.equals(o)) {
                return true;
            }
        }
        return false;
    }

    // R-7.19
    public void clear() {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @Test
    public void test() {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(0, 1);
        a.add(1, 2);
        a.add(2, 3);
        a.clear();

        // R-7.20
        Integer[] arr = new Integer[]{1, 2, 3};
        Collections.reverse(Arrays.asList(arr));
    }
}
