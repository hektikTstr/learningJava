package com.company.priority_queues;

import com.company.list.ArrayList;
import com.company.list.List;
import com.company.queue.Queue;
import com.company.stack.Stack;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Exercise {

    @Test
    public void testHeapQueueConstruction() {
        Integer[] integers = {2, 8, 6, 3, 5, 7, 1, 0, 4};
        String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        PriorityQueue<Integer, String> priorityQueue = new HeapPriorityQueue<>(integers, strings);
    }


    @Test
    public void test_R_9_13() {
        Integer[] integers = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        PriorityQueue<Integer, String> priorityQueue = new HeapPriorityQueue<>(integers, strings);
    }

    @Test
    public void test_C_9_25() {
        Stack<String> stack = new PriorityQueueStack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.push("f");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push("g");
        stack.push("h");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    @Test
    public void test_C_9_26() {
        Queue<String> queue = new PriorityQueueFIFOQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("f");
        queue.enqueue("g");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    @Test
    public void test_Exercise6_INFO1905() {
        Integer[] integers = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        HeapPriorityQueue<Integer, String> priorityQueue = new HeapPriorityQueue<>(integers, strings);
        priorityQueue.getAllKeysLessThanOrEqualTo(15);
    }

    private int getNthSmallestNum(Integer[] integers, int n) {
        if (integers.length == 0 || n < 0 || n > integers.length - 1) {
            throw new IllegalArgumentException();
        }
        Object[] objects = new Object[integers.length];
        HeapPriorityQueue<Integer, Object> priorityQueue = new HeapPriorityQueue<>(integers, objects);
        int ret = 0;
        while (n-- >= 0) {
            ret = priorityQueue.removeMin().getKey();
        }
        return ret;
    }

    @Test
    public void test_Exercise7_INFO1905() {
        Integer[] integers = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        int counter = getNthSmallestNum(integers, 0);

    }

    @Test
    public void test_Exercise9_INFO1905() {
        BinaryRepresentationComparator comparator = new BinaryRepresentationComparator();
        System.out.println(comparator.compare(7, 8));
    }
}
