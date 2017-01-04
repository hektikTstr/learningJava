package com.company.linkList;

public class DoublyLinkedList<T> {
    public int size = 0;
    public Node<T> header = null;
    public Node<T> trailer = null;
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
    public DoublyLinkedList() {
        header = new Node(null, null, null);
        trailer = new Node(null, header, null);
        header.next = trailer;
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
        return header.next.element;
    }
    public T last() {
        if (isEmpty()) {
            return null;
        }
        return trailer.prev.element;
    }
    private void addBetween(Node successor, Node predecessor, T element) {
        Node<T> node = new Node(successor, predecessor, element);
        successor.prev = node;
        predecessor.next = node;
        size++;
    }

    public void addFirst(T element) {
        addBetween(header.next, header, element);
    }
    public void addLast(T element) {
        addBetween(trailer, trailer.prev, element);
    }
    private void removeBetween(Node successor, Node predecessor) {
        successor.prev = predecessor;
        predecessor.next = successor;
        size--;
    }
    private void remove(Node<T> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T element = header.next.element;
        remove(header.next);
        //removeBetween(header.next.next, header);
        return element;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T element = trailer.prev.element;
        remove(trailer.prev);
        //removeBetween(trailer, trailer.prev.prev);
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
        System.out.println(list.removeLast());
    }
}
