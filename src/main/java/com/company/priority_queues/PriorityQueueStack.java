package com.company.priority_queues;

import com.company.stack.Stack;

// C-9.25
public class PriorityQueueStack<E> implements Stack<E> {
    private PriorityQueue<Integer, E> priorityQueue = new HeapPriorityQueue<>();
    private int priority = 0;

    @Override
    public int size() {
        return priorityQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public void push(E e) {
        priorityQueue.insert(priority, e);
        priority--;
    }

    @Override
    public E top() {
        return priorityQueue.min().getValue();
    }

    @Override
    public E pop() {
        E value = priorityQueue.removeMin().getValue();
//        priority++;
        return value;
    }
}
