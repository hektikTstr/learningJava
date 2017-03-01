package com.company.list;

import org.testng.annotations.Test;

public class LinkedPositionalQueue<E> implements PositionalQueue<E> {
    PositionalList<E> positionalList = new LinkedPositionalList<>();
    @Override
    public int size() {
        return positionalList.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Position<E> enqueue(E e) {
        return positionalList.addLast(e);
    }

    @Override
    public Position<E> first() {
        return positionalList.first();
    }

    @Override
    public E dequeue() {
        return remove(first());
    }

    @Override
    public E remove(Position<E> p) {
        return positionalList.remove(p);
    }

    @Test
    public void test() {
        PositionalQueue<Integer> positionalQueue = new LinkedPositionalQueue<>();
        positionalQueue.enqueue(1);
        positionalQueue.enqueue(2);
        Position<Integer> position = positionalQueue.enqueue(3);
        positionalQueue.enqueue(4);
        positionalQueue.remove(position);
        positionalQueue.dequeue();
    }
}
