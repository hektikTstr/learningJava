package com.company.queue;

import com.company.fundamental.linkList.SinglyLinkedList;

public class LinkedQueue<E> implements Queue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedQueue() {}
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }
}
