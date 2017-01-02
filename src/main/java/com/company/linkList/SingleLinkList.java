package com.company.linkList;

public class SingleLinkList<T> {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T value) {
        this.head = new Node(value, head);
        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(T value) {
        Node newNode = new Node(value, null);
        this.tail.setNext(newNode);
        this.tail = newNode;
        if (isEmpty()) {
            head = tail;
        }
        size++;
    }

    public Node first() {
        return head;
    }

    public Node last() {
        return tail;
    }

    public void removeFirst() {
        if (first() == null) {
            throw new UnsupportedOperationException("The list is empty.");
        }
        head = head.getNext();
        size--;
        if (isEmpty()) {
            tail = null;
        }
    }

    private class Node<T> {
        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
        private T value;
        private Node<T> next;
        private void setNext(Node<T> next) {
            this.next = next;
        }

        private Node<T> getNext() {
            return this.next;
        }

        private T getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        SingleLinkList<String> stringSingleLinkList = new SingleLinkList<String>();
        stringSingleLinkList.addFirst("xiao");
        stringSingleLinkList.addLast("yang");
        stringSingleLinkList.addFirst("zhang");
        stringSingleLinkList.addLast("is");
        stringSingleLinkList.addLast("good");
        stringSingleLinkList.addFirst("mr. ");
        System.out.println("first() = " + stringSingleLinkList.first().getValue());
        System.out.println("last() = " + stringSingleLinkList.last().getValue());
        stringSingleLinkList.removeFirst();
        System.out.println("first() = " + stringSingleLinkList.first().getValue());
        stringSingleLinkList.removeFirst();
        System.out.println("first() = " + stringSingleLinkList.first().getValue());
        stringSingleLinkList.removeFirst();
        stringSingleLinkList.removeFirst();
        stringSingleLinkList.removeFirst();
        System.out.println("first() = " + stringSingleLinkList.first().getValue());
        System.out.println("last() = " + stringSingleLinkList.last().getValue());
        stringSingleLinkList.removeFirst();
    }
}

