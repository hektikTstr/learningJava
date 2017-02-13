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

    //C-6.29
    public void concatenate(LinkedQueue<E> Q2) {
        if (isEmpty()) {
            this.list.tail = Q2.list.head;
            this.list.head = this.list.tail;
        } else {
            this.list.tail.setNext(Q2.list.head);
        }
        this.list.size += Q2.size();
        Q2.list = new SinglyLinkedList<>();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        LinkedQueue<Integer> queue2 = new LinkedQueue<>();
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.concatenate(queue);
    }
}
