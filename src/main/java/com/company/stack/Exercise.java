package com.company.stack;

import com.company.queue.*;

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

    //R-6.13
    public static <E> void transfer2(Deque<E> orig, Queue<E> dest) {
        E e = orig.removeFirst();
        while (e != null) {
            dest.enqueue(e);
            e = orig.removeFirst();
        }
    }

    //R-6.14
    public static <E> void transfer3(Deque<E> orig, Stack<E> dest) {
        E e = orig.removeLast();
        while (e != null) {
            dest.push(e);
            e = orig.removeLast();
        }
    }

    // C-6.19
    public static int postfixNotationCalculation(String expression) {
        char[] charArr = expression.toCharArray();
        Stack<Integer> stack = new LinkedStack<>();
        for (Character c : charArr) {
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            } else {
                int y = stack.pop();
                int x = stack.pop();
                int result = 0;
                switch (c) {
                    case '*':
                        result = x * y;
                        break;
                    case '/':
                        result = x / y;
                        break;
                    case '+':
                        result = x + y;
                        break;
                    case '-':
                        result = x - y;
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static void hanoiTower(int n, Stack<Integer> start, Stack<Integer> aux, Stack<Integer> end) {
        if (n == 1) {
            end.push(start.pop());
        } else {
            hanoiTower(n - 1, start, end, aux);
            hanoiTower(1, start, aux, end);
            hanoiTower(n - 1, aux, start, end);
        }
    }

    public static void main(String[] args) {
//        Stack<Integer> orig = new Exercise().new StackAdapter<>();
//        Stack<Integer> target = new ArrayStack<>();
//        Deque<Integer> deque = new ArrayDeque<>();
//        deque.addLast(1);
//        deque.addLast(2);
//        deque.addLast(3);
//        deque.addLast(4);
//        deque.addLast(5);
//        Stack<Integer> stack = new LinkedStack<>();
//        transfer3(deque, stack);
//        orig.push(5);
//        orig.push(3);
//        orig.push(7);
//        orig.push(8);
//        transfer(orig, target);
//        removeAllElemInStack(target);
//        //C-6.17
//        Stack<Integer> temp1 = new ArrayStack<>();
//        Stack<Integer> temp2 = new ArrayStack<>();
//        Stack<Integer> orig = new ArrayStack<>();
//        orig.push(5);
//        orig.push(3);
//        orig.push(7);
//        orig.push(8);
//        transfer(orig, temp1);
//        transfer(temp1, temp2);
//        transfer(temp2, orig);

//        System.out.println(postfixNotationCalculation("52+83-*4/"));
        Stack<Integer> start = new ArrayStack<>(3);
        Stack<Integer> end = new ArrayStack<>(3);
        Stack<Integer> aux = new ArrayStack<>(3);
        start.push(3);
        start.push(2);
        start.push(1);
        hanoiTower(3, start, aux, end);
    }
}
