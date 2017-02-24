package com.company.list;

import com.company.stack.Stack;
import org.testng.annotations.Test;

//R-7.2
public class ArrayListStack<E> implements Stack<E> {
    private List<E> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.add(size(), e);
    }

    @Deprecated // commented as this add will cause shifting
    public void push1(E e) {
        list.add(0, e);
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return list.remove(size() - 1);
    }

    @Test
    public void test() {
        ArrayListStack<Integer> arrayListStack = new ArrayListStack<>();
        Integer a = arrayListStack.pop();
        a = arrayListStack.top();
        arrayListStack.push(1);
        arrayListStack.push(2);
        arrayListStack.push(3);
        arrayListStack.push(4);
        arrayListStack.push(5);
        arrayListStack.push(6);
        arrayListStack.push(7);
        arrayListStack.push(8);
        a = arrayListStack.pop();
        a = arrayListStack.pop();
        a = arrayListStack.pop();
        arrayListStack.push(4);
        arrayListStack.push(3);
        a = arrayListStack.pop();
    }
}
