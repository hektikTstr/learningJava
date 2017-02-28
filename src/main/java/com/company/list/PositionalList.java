package com.company.list;

import java.util.Iterator;

public interface PositionalList<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    Position<E> first();
    Position<E> last();
    Position<E> before(Position<E> p) throws IllegalArgumentException;
    Position<E> after(Position<E> p) throws IllegalArgumentException;
    Position<E> addFirst(E e);
    Position<E> addLast(E e);
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
    E set(Position<E> p, E e) throws IllegalArgumentException;
    E remove(Position<E> p) throws IllegalArgumentException;
    Iterator<E> iterator();
    // R-7.12
    int indexOf(Position<E> p);
    // R-7.13
    Position<E> findPosition(E e);
}
