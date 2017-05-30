package com.company.priority_queues;

import com.company.queue.Queue;

// C-9.26
public class PriorityQueueFIFOQueue<E> implements Queue<E> {
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
    public void enqueue(E e) {
        priorityQueue.insert(priority, e);
        priority++;
    }

    @Override
    public E first() {
        return priorityQueue.min().getValue();
    }

    @Override
    public E dequeue() {
        E value = priorityQueue.removeMin().getValue();
//        priority--;
        return value;
    }
}
