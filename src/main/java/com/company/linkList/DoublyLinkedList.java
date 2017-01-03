package com.company.linkList;

public class DoublyLinkedList<T> {
    public int size = 0;
    public Node<T> head = null;
    public Node<T> tail = null;
    public class Node<T> {
        public Node<T> next = null;
        public Node<T> prev = null;
        public T element;
        public Node(Node next, Node prev, T element) {
            this.next = next;
            this.prev = prev;
            this.element = element;
        }
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public T first() {
        if (isEmpty()) {
            return null;
        }
        return head.element;
    }
    public T last() {
        if (isEmpty()) {
            return null;
        }
        return tail.element;
    }
    public void addFirst(T element) {
        Node<T> node = new Node(null, null, element);
        if (isEmpty()) {
            tail = node;
            node.next = null;
            node.prev = null;
        } else {
            node.next = head;
            node.prev = null;
            head.prev = node;
        }
        head = node;
        node.element = element;
        size++;
    }
    public void addLast(T element) {
        Node<T> node = new Node(null, null, element);
        if (isEmpty()) {
            head = node;
            node.next = null;
            node.prev = null;
        } else {
            node.next = null;
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
        node.element = element;
        size++;
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T element = head.element;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return element;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T element = tail.element;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return element;
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        System.out.println(list.size());
        list.removeFirst();
        list.removeLast();
        list.addFirst("is");
        list.addLast("good");
        list.addFirst("shawn");
        list.addFirst("mr.");
        list.addLast("boy");
        System.out.println(list.removeFirst());
        System.out.println(list.removeLast());
        System.out.println(list.removeFirst());
        System.out.println(list.removeFirst());
        System.out.println(list.removeLast());
    }
}
