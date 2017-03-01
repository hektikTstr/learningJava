package com.company.list;

public interface PositionalQueue<E> {
    int size();
    boolean isEmpty();
    Position<E> enqueue(E e);
    Position<E> first();
    E dequeue();
    E remove(Position<E> p);
}
