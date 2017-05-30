package com.company.priority_queues;

import java.util.Comparator;

public class BinaryRepresentationComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        a = get1Representations(a);
        b = get1Representations(b);
        return a - b;
    }

    private Integer get1Representations(Integer a) {
        int count = 0;
        do {
            if ((a & 1) == 1) {
                count++;
            }
            a = a >> 1;
        } while (a != 0);
        return count;
    }
}
