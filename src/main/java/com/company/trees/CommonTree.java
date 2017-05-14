package com.company.trees;

import com.company.list.Position;

public class CommonTree<E> extends AbstractTree<E> implements Tree<E> {
    @Override
    public Position<E> root() {
        return null;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }
}
