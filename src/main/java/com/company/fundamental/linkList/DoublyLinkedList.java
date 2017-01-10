package com.company.fundamental.linkList;

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
    // R-3.11
    public int sizeReimplementation() {
        int counter = 0;
        Node node = header;
        while (node.next != trailer) {
            counter++;
            node = node.next;
        }
        return counter;
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
    // R-3.8
    public T middle() {
        if (isEmpty()) {
            return null;
        }
        Node<T> forwardWalker = header.next;
        Node<T> backwardWalker = trailer.prev;
        // size is odd
        if (size() % 2 == 1) {
            while (forwardWalker != backwardWalker) {
                forwardWalker = forwardWalker.next;
                backwardWalker = backwardWalker.prev;
            }
            return forwardWalker.element;
        } else {
            // size is even
            while (forwardWalker != backwardWalker.prev) {
                forwardWalker = forwardWalker.next;
                backwardWalker = backwardWalker.prev;
            }
            return forwardWalker.element;
        }
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
        System.out.println(list.sizeReimplementation());
        list.addLast("a");
        System.out.println(list.sizeReimplementation());
        list.addLast("good");
        System.out.println(list.sizeReimplementation());
        list.addFirst("shawn");
        System.out.println(list.sizeReimplementation());
        list.addFirst("mr.");
        System.out.println(list.sizeReimplementation());
        list.addLast("boy");
        System.out.println(list.sizeReimplementation());
        System.out.println(list.middle());
        System.out.println(list.removeFirst());
        System.out.println(list.sizeReimplementation());
        System.out.println(list.removeLast());
        System.out.println(list.sizeReimplementation());
        System.out.println(list.removeFirst());
        System.out.println(list.removeFirst());
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list.sizeReimplementation());
    }
}
