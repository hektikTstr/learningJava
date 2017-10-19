package com.company.cc186.stack;

import java.util.ArrayList;

public class SetOfStacks<E> {
    public ArrayList<ArrayStack<E>> stackList = new ArrayList<>();
    private int size = 0;
    private static final int sizeLimit = 5;

    private int getTopElementStackNum() {
        return (size - 1) / sizeLimit;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return stackList.get(getTopElementStackNum()).top();
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E ret = stackList.get(getTopElementStackNum()).pop();
        size--;
        return ret;
    }

    public void push(E value) {
        if (stackList.size() <= size / sizeLimit) {
            ArrayStack<E> stack = new ArrayStack<>();
            stack.push(value);
            stackList.add(stack);
        } else {
            stackList.get(getTopElementStackNum()).push(value);
        }
        size++;
    }

    public static void main(String[] args) {
        SetOfStacks<Integer> setOfStacks = new SetOfStacks<>();
        System.out.println("setOfStacks top = " + setOfStacks.top());
        System.out.println("setOfStacks size = " + setOfStacks.size());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        setOfStacks.push(15);
        setOfStacks.push(14);
        setOfStacks.push(13);
        setOfStacks.push(12);
        setOfStacks.push(11);
        setOfStacks.push(10);
        setOfStacks.push(9);
        setOfStacks.push(8);
        setOfStacks.push(7);
        setOfStacks.push(6);
        setOfStacks.push(5);
        setOfStacks.push(4);
        setOfStacks.push(3);
        setOfStacks.push(2);
        setOfStacks.push(1);
        System.out.println("stackList size = " + setOfStacks.stackList.size());
        setOfStacks.push(0);
        System.out.println("stackList size = " + setOfStacks.stackList.size());
        setOfStacks.push(-1);
        setOfStacks.push(-2);
        System.out.println("stackList size = " + setOfStacks.stackList.size());
        System.out.println("setOfStacks top = " + setOfStacks.top());
        System.out.println("setOfStacks size = " + setOfStacks.size());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks size = " + setOfStacks.size());
        System.out.println("setOfStacks top = " + setOfStacks.top());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks size = " + setOfStacks.size());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks size = " + setOfStacks.size());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks size = " + setOfStacks.size());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks size = " + setOfStacks.size());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks pop = " + setOfStacks.pop());
        System.out.println("setOfStacks size = " + setOfStacks.size());
        setOfStacks.push(10);
    }

}
