package com.company.stack;

import com.company.queue.ArrayQueue;
import com.company.queue.Queue;

public class QueueStack<T> implements Stack<T> {
    private Queue<T> queue = new ArrayQueue<>();
    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void push(T t) {
        queue.enqueue(t);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.enqueue(queue.dequeue());
        }
    }

    @Override
    public T top() {
        return queue.first();
    }

    @Override
    public T pop() {
        return queue.dequeue();
    }

    // C-6.25
    public static void main(String args[]) {
        Stack<Integer> stack = new QueueStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
