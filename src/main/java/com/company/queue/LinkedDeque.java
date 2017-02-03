package com.company.queue;

import com.company.fundamental.linkList.DoublyLinkedList;

public class LinkedDeque<E> implements Deque<E> {
    private DoublyLinkedList<E> list = new DoublyLinkedList<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E last() {
        return list.last();
    }

    @Override
    public void addFirst(E e) {
        list.addFirst(e);
    }

    @Override
    public void addLast(E e) {
        list.addLast(e);
    }

    @Override
    public E removeFirst() {
        return list.removeFirst();
    }

    @Override
    public E removeLast() {
        return list.removeLast();
    }

    public static void main(String[] args) {
        Deque<Integer> queue = new LinkedDeque<>();
        queue.addLast(5);
        queue.addFirst(3);
        queue.addFirst(7);
        System.out.println(queue.first());
        queue.removeLast();
        System.out.println(queue.size());
        queue.removeLast();
        queue.removeFirst();
        System.out.println(queue.size());
        queue.addFirst(6);
        System.out.println(queue.first());
        System.out.println(queue.last());
        queue.addFirst(8);
        System.out.println(queue.first());
        System.out.println(queue.last());
    }
}
