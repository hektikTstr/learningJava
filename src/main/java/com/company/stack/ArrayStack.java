package com.company.stack;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {
    public static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1;
    public ArrayStack() { this(CAPACITY); }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return t + 1;
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }

    @Override
    public void push(E e) {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[++t] = e;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[t];
        data[t--] = null;
        return answer;
    }

    public static <E> void reverse(E[] a) {
        Stack<E> buffer = new ArrayStack<E>(a.length);
        for (int i = 0; i < a.length; i++) {
            buffer.push(a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = buffer.pop();
        }
    }

    public static void main(String args[]) {
        Stack<Integer> stack = new ArrayStack<>();
        stack.push(5);
        stack.push(3);
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        stack.push(7);
        stack.push(9);
        System.out.println(stack.top());
        stack.push(4);
        System.out.println(stack.size());
        System.out.println(stack.pop());
        stack.push(6);
        stack.push(8);
        System.out.println(stack.pop());

        Integer[ ] a = {4, 8, 15, 16, 23, 42};
        String[ ] s = {"Jack", "Kate", "Hurley", "Jin", "Michael"};
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
        System.out.println("Reversing...");
        reverse(a);
        reverse(s);
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
    }
}
