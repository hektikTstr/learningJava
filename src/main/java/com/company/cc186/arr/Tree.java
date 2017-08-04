package com.company.cc186.arr;

public interface Tree<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    Position<E> root();
    Position<E> parent(Position<E> p);
    Iterable<Position<E>> children(Position<E> p);
    int numChildren(Position<E> p);
    boolean isInternal(Position<E> p);
    boolean isExternal(Position<E> p);
    Iterable<Position<E>> positions();
}

