package com.company.list;

import com.company.deque.Deque;

// R-7.3
public class ArrayListDeque<E> implements Deque<E> {
    List<E> list = new ArrayList<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return list.get(size() - 1);
    }

    @Override
    public void addFirst(E e) {
        list.add(0, e);
    }

    @Override
    public void addLast(E e) {
        list.add(size(), e);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return list.remove(size() - 1);
    }
}
