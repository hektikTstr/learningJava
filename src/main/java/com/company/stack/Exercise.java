package com.company.stack;

import com.company.queue.Deque;
import com.company.queue.LinkedDeque;
import com.company.queue.Queue;

public class Exercise {
    //R-6.4
    public static <E> void transfer(Stack<E> orig, Stack<E> target) {
        E e = orig.pop();
        while (e != null) {
            target.push(e);
            e = orig.pop();
        }
    }

    //R-6.5
    public static <E> void removeAllElemInStack(Stack<E> stack) {
        if (stack.isEmpty()) {
            return;
        } else {
            stack.pop();
            removeAllElemInStack(stack);
        }
    }

    //R-6.10
    public class StackAdapter<E> implements Stack<E> {
        private Deque<E> deque = null;

        public StackAdapter() {
            deque = new LinkedDeque<>();
        }

        public StackAdapter(Deque<E> deque) {
            this.deque = deque;
        }

        @Override
        public int size() {
            return deque.size();
        }

        @Override
        public boolean isEmpty() {
            return deque.isEmpty();
        }

        @Override
        public void push(E e) {
            deque.addFirst(e);
        }

        @Override
        public E top() {
            return deque.first();
        }

        @Override
        public E pop() {
            return deque.removeFirst();
        }
    }

    //R-6.11
    public class QueueAdapter<E> implements Queue<E> {
        private Deque<E> deque = null;

        public QueueAdapter() {
            deque = new LinkedDeque<>();
        }

        public QueueAdapter(Deque<E> deque) {
            this.deque = deque;
        }

        @Override
        public int size() {
            return deque.size();
        }

        @Override
        public boolean isEmpty() {
            return deque.isEmpty();
        }

        @Override
        public void enqueue(E e) {
            deque.addLast(e);
        }

        @Override
        public E first() {
            return deque.first();
        }

        @Override
        public E dequeue() {
            return deque.removeFirst();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> orig = new Exercise().new StackAdapter<>();
        Stack<Integer> target = new ArrayStack<>();
        orig.push(5);
        orig.push(3);
        orig.push(7);
        orig.push(8);
        transfer(orig, target);
        removeAllElemInStack(target);
    }
}
