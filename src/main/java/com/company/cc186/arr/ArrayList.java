package com.company.cc186.arr;

import com.company.list.List;
import org.testng.annotations.Test;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private E[] elem_arr;
    private int size = 0;
    private int DEFAULT_SIZE = 8;

    public ArrayList() {
        elem_arr = (E[]) new Object[DEFAULT_SIZE];
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexValidity(int i, int upper_bound) {
        if (i >= upper_bound || i < 0) {
            throw new IndexOutOfBoundsException("bla bla bla");
        }
    }
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndexValidity(i, size);
        return elem_arr[i];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndexValidity(i, size);
        E temp = elem_arr[i];
        elem_arr[i] = e;
        return temp;
    }

    private void resize(int new_size) {
        validateResizeValue(new_size);
        E[] new_arr = (E[]) new Object[new_size];
        for (int i = 0; i < size; i++) {
            new_arr[i] = elem_arr[i];
        }
        elem_arr = new_arr;
    }

    private void validateResizeValue(int i) {
        if (i <= size) {
            throw new IllegalArgumentException("blablabla");
        }
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndexValidity(i, size + 1);
        if (size + 1 > elem_arr.length) {
            resize(2 * elem_arr.length);
        }
        for (int startIndex = size - 1; startIndex >= i; startIndex--) {
            elem_arr[startIndex + 1] = elem_arr[startIndex];
        }
        elem_arr[i] = e;
        size++;
    }

    @Override
    public void add(E e) throws IndexOutOfBoundsException {
        add(size, e);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndexValidity(i, size);
        E temp = elem_arr[i];
        for (int startIndex = i; startIndex < size - 1; startIndex++) {
            elem_arr[startIndex] = elem_arr[startIndex + 1];
        }
        elem_arr[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {
        private int next_pos = 0;

        @Override
        public E next() {
            return elem_arr[next_pos++];
        }

        @Override
        public boolean hasNext() {
            return next_pos < size;
        }
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        System.out.println(list.isEmpty());
        try {
            list.add(1, "hello");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        System.out.println(list.size());
        list.add(0, "hello");
        for (String str : list) {
            System.out.println(str);
        }

        list.add(0, "new");
        list.add(1, "world");
        list.add("hi");
        System.out.println(list.size());
        for (String str : list) {
            System.out.println(str);
        }

        list.remove(0);
        System.out.println(list.size());
        for (String str : list) {
            System.out.println(str);
        }
        try {
            list.remove(3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }


        System.out.println(list.size());
        for (String str : list) {
            System.out.println(str);
        }

        list.remove(2);


        System.out.println(list.size());
        for (String str : list) {
            System.out.println(str);
        }

        list.remove(0);


        System.out.println(list.size());
        for (String str : list) {
            System.out.println(str);
        }
    }
}
