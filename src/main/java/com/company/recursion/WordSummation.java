package com.company.recursion;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordSummation {
    private static int index = 0;
    private static void wordSum(int n, List<Integer> list, List<Integer> all) {
        for (int i = 0; i < all.size(); i++) {
            if (n <= 0) {
                continue;
            }
            Integer j = all.remove(i);
            list.add(j);
            if (n == 1 &&
                100 * list.get(0) + 10 * list.get(1) + list.get(2) + 1000 * list.get(3) + 100 * list.get(4) + 10 * list.get(5) + list.get(6)
                        == 1000 * list.get(0) + 100 * list.get(7) + 10 * list.get(0) + list.get(2)) {
                list.subList(0, 7).forEach(System.out::print);
                System.out.println();
                index++;
            } else {
                wordSum(n - 1, list, all);
            }
            all.add(i, list.remove(list.indexOf(j)));
        }
    }
    public static void main(String[] args) {
        List<Integer> set = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).collect(Collectors.toList());
        wordSum(8, new ArrayList<>(), set);
        System.out.println(index);
    }
}
