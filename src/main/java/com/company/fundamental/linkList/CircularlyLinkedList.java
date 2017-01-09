package com.company.fundamental.linkList;

public class CircularlyLinkedList<T> {
    public class Node<T> {
        public T element;
        public Node<T> next;
        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
    public int size = 0;
    public Node<T> tail = null;
    public boolean isEmpty() {
        return size == 0;
    }
    public void addFirst(T element) {
        Node<T> node = new Node<T>(element, null);
        if (isEmpty()) {
            tail = node;
            tail.next = tail;
        } else {
            node.next = tail.next;
            tail.next = node;
        }
        size++;
    }
    public void addLast(T element) {
        Node<T> node = new Node<T>(element, null);
        if (isEmpty()) {
            tail = node;
            tail.next = tail;
        } else {
            node.next = tail.next;
            tail.next = node;
            tail = node;
        }
        size++;
    }
    public T first() {
        if (isEmpty()) {
            return null;
        }
        return tail.next.element;
    }
    public T last() {
        if (isEmpty()) {
            return null;
        }
        return tail.element;
    }
    public void rotate() {
        tail = tail.next;
    }
    public T removeFirst() {
        T element = null;
        if (isEmpty()) {
            return null;
        } else {
            element = tail.next.element;
            tail.next = tail.next.next;
            size--;
            if (size == 0) {
                tail = null;
            }
            return element;
        }
    }

    public static void main(String[] args) {
        CircularlyLinkedList<String> linkedList = new CircularlyLinkedList<String>();
        linkedList.addFirst("is");
        linkedList.addLast("good");
        linkedList.addFirst("shawn");
        linkedList.addFirst("mr.");
        linkedList.rotate();
        linkedList.rotate();
        linkedList.rotate();
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.addFirst("hi");
        linkedList.addFirst("hello");
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.removeFirst();
    }
}
