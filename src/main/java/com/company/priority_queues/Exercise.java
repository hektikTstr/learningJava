package com.company.priority_queues;

import com.company.list.ArrayList;
import com.company.list.List;
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
}
