package com.company.list;

import org.testng.annotations.Test;

import java.util.*;
import java.util.List;

public class LinkedPositionalList<E> implements PositionalList<E> {
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (next == null) {
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList( ) {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid p");
        }
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getNext() == null) {// convention for defunct node
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null; // do not expose user to the sentinels
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() {
        return header.getNext();
    }

    @Override
    public Position<E> last() {
        return trailer.getPrev();
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ); // create and link a new node
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null); node.setNext(null);
        node.setPrev(null);
        return answer;
    }

    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first(); // position of the next element to report
        private Position<E> recent = null; // position of last reported element

        @Override
        public boolean hasNext() {
            return (cursor != null);
        }

        @Override
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) {
                throw new NoSuchElementException("nothing left");
            }
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() {
            if (recent == null) {
                throw new IllegalStateException("nothing to remove");
            }
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }

    private class PositionIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable(); // create a new instance of the inner class
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Test
    public void test() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8}; // allowed by autoboxing
        List listArr = (java.util.List) Arrays.asList(arr);
        Collections.shuffle(listArr);
    }

    public static void insertionSort(PositionalList<Integer> list) {
        Position<Integer> marker = list.first(); // last position known to be sorted
        while (marker != list.last()) {
            Position<Integer> pivot = list.after(marker);
            int value = pivot.getElement(); // number to be placed
            if (value > marker.getElement()) { // pivot is already sorted
                marker = pivot;
            } else {
                Position<Integer> walk = marker;
                while (walk != list.first() && list.before(walk).getElement() > value) {
                    walk = list.before(walk);
                }
                list.remove(pivot);
                list.addBefore(walk, value);
            }
        }
    }
}
