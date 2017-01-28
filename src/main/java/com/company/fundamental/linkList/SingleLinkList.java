package com.company.fundamental.linkList;

public class SingleLinkList<T> implements Cloneable {
    public Node<T> head = null;
    public Node<T> tail = null;
    private int size = 0;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    // R-3.9
    public int sizeReimplementation() {
        int counter = 0;
        Node<T> node = head;
        while (node != null) {
            node = node.getNext();
            counter++;
        }
        return counter;
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
        if (isEmpty()) {
            head = newNode;
        } else {
            this.tail.setNext(newNode);
        }
        this.tail = newNode;
        size++;
    }

    public T first() {
        if (isEmpty()) {
            return null;
        }
        return head.getValue();
    }

    public T last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getValue();
    }

    public T secondToLast() {
        if (size() < 2) {
            return null;
        }
        Node<T> cur = head;
        while (cur.getNext().getNext() != null) {
            cur = cur.getNext();
        }
        return cur.getValue();
    }

    public T removeFirst() {
        if (first() == null) {
            throw new UnsupportedOperationException("The list is empty.");
        }
        T element = head.getValue();
        head = head.getNext();
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return element;
    }

    public class Node<T> {
        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
        private T value;
        private Node<T> next;
        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public T getValue() {
            return value;
        }
    }

    public boolean equals(Object o) {
        if (o == null || size() != ((SingleLinkList)o).size() || getClass() != o.getClass()) {
            return false;
        }
        Node n1 = head;
        Node n2 = ((SingleLinkList)o).head;
        while (n1 != null) {
            if (!n1.getValue().equals(n2.getValue())) {
                return false;
            }
            n1 = n1.getNext();
            n2 = n2.getNext();
        }
        return true;
    }

    public SingleLinkList<T> clone() throws CloneNotSupportedException {
        SingleLinkList<T> newList = (SingleLinkList<T>) super.clone();
        if (!isEmpty()) {
            Node<T> node = new Node<T>(head.getValue(), null);
            newList.head = node;
            Node<T> walker = head.getNext();
            Node<T> cur = node;
            while (walker != null) {
                node = new Node<T>(walker.getValue(), null);
                cur.setNext(node);
                cur = node;
                walker = walker.getNext();
            }
            newList.tail = cur;
        }
//
//        Node<T> temp = this.head;
//        for (int i = 0; i < size(); i++) {
//            newList.addLast(temp.getValue());
//            temp = temp.getNext();
//        }
//        newList.size = size();
        return newList;
    }

    // R-3.12
    public void rotate() {
        if (isEmpty()) {
            return;
        }
        tail.setNext(head);
        tail = head;
        head = head.getNext();
        tail.setNext(null);
    }
    public static void main(String[] args) throws CloneNotSupportedException {
        SingleLinkList<String> stringSingleLinkList = new SingleLinkList<String>();
        SingleLinkList<String> stringSingleLinkList2 = new SingleLinkList<String>();
        stringSingleLinkList.addFirst("xiao");
        stringSingleLinkList.addLast("yang");
        stringSingleLinkList.addFirst("zhang");
        stringSingleLinkList.addLast("is");
        stringSingleLinkList.addLast("good");
        stringSingleLinkList.addFirst("mr. ");
        stringSingleLinkList.rotate();
        stringSingleLinkList2.addFirst("xiao");
        stringSingleLinkList2.addLast("yang");
        stringSingleLinkList2.addFirst("zhang");
        stringSingleLinkList2.addLast("is");
        stringSingleLinkList2.addLast("good");
        stringSingleLinkList2.addFirst("mrs. ");
        System.out.println(stringSingleLinkList.equals(stringSingleLinkList2));
        SingleLinkList<String> stringSingleLinkList3 = stringSingleLinkList.clone();
        stringSingleLinkList.secondToLast();
//        System.out.println("first() = " + stringSingleLinkList.first().getValue());
//        System.out.println("last() = " + stringSingleLinkList.last().getValue());
//        stringSingleLinkList.removeFirst();
//        System.out.println("first() = " + stringSingleLinkList.first().getValue());
//        stringSingleLinkList.removeFirst();
//        System.out.println("first() = " + stringSingleLinkList.first().getValue());
//        stringSingleLinkList.removeFirst();
//        stringSingleLinkList.removeFirst();
//        stringSingleLinkList.removeFirst();
//        System.out.println("first() = " + stringSingleLinkList.first().getValue());
//        System.out.println("last() = " + stringSingleLinkList.last().getValue());
//        stringSingleLinkList.removeFirst();
    }
}

