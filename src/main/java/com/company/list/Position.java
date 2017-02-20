package com.company.list;

public interface Position<E> {
    E getElement() throws IllegalStateException;
}
