package com.company.cc186.stack;

public class ArrayStack<E> {
    private E[] arr = null;
    private int capacity = 8;
    private int stackTopIdx = 0;

    public ArrayStack() {
        arr = (E[]) new Object[capacity];
    }

    public int size() {
        return stackTopIdx;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(E element) {
        if (size() == capacity) {
            throw new IllegalStateException("Stack is full");
        }
        arr[stackTopIdx++] = element;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return arr[--stackTopIdx];
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }

        return arr[stackTopIdx - 1];
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("top() = " + stack.top());
        System.out.println("pop() = " + stack.pop());
        stack.push(8);
        System.out.println("push()");
        System.out.println("size() = " + stack.size());
        System.out.println("isEmpty() = " + stack.isEmpty());
        System.out.println("top() = " + stack.top());
        System.out.println("pop() = " + stack.pop());
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("pop() = " + stack.pop());
        System.out.println("top() = " + stack.top());
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("top() = " + stack.top());
        stack.push(0);
    }
}
