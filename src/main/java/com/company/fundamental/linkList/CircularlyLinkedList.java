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
        if (isEmpty()) {
            return;
        }
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
    // R-3.10
    public int sizeReimplementation() {
        if (tail == null) {
            return 0;
        }
        int counter = 1;
        Node node = tail;
        while (node.next != tail) {
            counter++;
            node = node.next;
        }
        return counter;
    }

    //R-3.15
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass() || this.size != ((CircularlyLinkedList)o).size) {
            return false;
        }
        if ((size == 0) && (tail == null) && (((CircularlyLinkedList) o).tail == null)) {
            return true;
        }
        Node cur = tail;
        Node walker = ((CircularlyLinkedList)o).tail;
        do {
            if (!cur.element.equals(walker.element)) {
                return false;
            }
            cur = cur.next;
            walker = walker.next;
        } while (cur != tail);
        return true;
    }
    public static void main(String[] args) {
        CircularlyLinkedList<String> linkedList = new CircularlyLinkedList<String>();
        CircularlyLinkedList<String> linkedList2 = new CircularlyLinkedList<String>();
        System.out.println(linkedList.equals(linkedList2));
        linkedList.addFirst("is");
        linkedList2.addFirst("is");
        System.out.println(linkedList.equals(linkedList2));
        linkedList.addLast("good");
        System.out.println(linkedList.equals(linkedList2));
        linkedList.addFirst("shawn");
        System.out.println(linkedList.equals(linkedList2));
        linkedList.addFirst("mr.");
        System.out.println(linkedList.equals(linkedList2));
        linkedList2.addLast("good");
        System.out.println(linkedList.equals(linkedList2));
        linkedList2.addFirst("shawn");
        System.out.println(linkedList.equals(linkedList2));
        linkedList2.addFirst("mr.");
        System.out.println(linkedList.equals(linkedList2));

        System.out.println(linkedList.sizeReimplementation());
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
