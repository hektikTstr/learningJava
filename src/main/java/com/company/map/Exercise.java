package com.company.map;

import org.testng.annotations.Test;

import java.util.*;

//Exercise3: (INFO1105 and INFO1905)
public class Exercise {

    @Test
    public void testSort() {
        List<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(9);
        list.add(2);
        Collections.sort(list);

        Integer[] a = {3, 5, 2, 8, 9, 3, 2, 9, 3, 3, 9};
        removeDuplicatesWithSort(a);

    }

    private void removeDuplicatesWithSort(Integer[] a) {
        Arrays.sort(a);

        int j = 0;
        for (int i = 1; i < a.length; i++) {
            if (!a[i].equals(a[j])) {
                ++j;
                a[j] = a[i];
            }
        }

        while (++j < a.length) {
            a[j] = null;
        }
    }

    // this version changes the array order
    private <E> void removeDuplicates(E[] arr) {
        Map<E, Integer> map = new ChainHashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], i);
            }
        }

        int i = 0;
        for (E e : map.keySet()) {
            arr[i++] = e;
        }
        for (; i < arr.length; i++) {
            arr[i] = null;
        }
    }

    private <E> void removeDuplicatesWithHashMap(E[] arr, Map<E, Integer> map) {
        int order = 0;
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], order++);
            }
        }

        for (E e : map.keySet()) {
            arr[map.get(e)] = e;
        }

        for (; order < arr.length; order++) {
            arr[order] = null;
        }
    }

    private <E> void removeDuplicatesWithJaveHashMap(E[] arr, java.util.Map<E, Integer> map) {
        int order = 0;
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], order++);
        }

        for (E e : map.keySet()) {
            arr[map.get(e)] = e;
        }

        for (; order < arr.length; order++) {
            arr[order] = null;
        }
    }

    @Test
    public void testRemoveDuplicates() {
        Character arr[] = {'a', 'e', 'b', 'e', 'a', 'd', 'e', 'f'};
        removeDuplicatesWithHashMap(arr, new ChainHashMap<>());
    }

    @Test
    public void testBenchMark() {
        Integer arr[] = new Integer[1000000];
        Long timeStart;
        for (int i = 0; i < 10; i++) {
            randomlyGenerateArrData(arr);
            Map<Integer, Integer> map = new ChainHashMap<>(2000000);
            timeStart = System.nanoTime();
            removeDuplicatesWithHashMap(arr, map);
            System.out.println(System.nanoTime() - timeStart);
        }

        System.out.println("\n");

        for (int i = 0; i < 10; i++) {
            randomlyGenerateArrData(arr);
            java.util.Map<Integer, Integer> map1 = new HashMap<>();
            timeStart = System.nanoTime();
            removeDuplicatesWithJaveHashMap(arr, map1);
            System.out.println(System.nanoTime() - timeStart);
        }

        System.out.println("\n");

//        randomlyGenerateArrData(arr);
//        timeStart = System.nanoTime();
//        removeDuplicatesWithHashMap(arr, new ChainHashMapWithNewHashCode<>());
//        System.out.println(System.nanoTime() - timeStart);

        for (int i = 0; i < 10; i++) {
            randomlyGenerateArrData(arr);
            timeStart = System.nanoTime();
            removeDuplicatesWithSort(arr);
            System.out.println(System.nanoTime() - timeStart);
        }
    }

    private void randomlyGenerateArrData(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(10000000);
        }
    }
}
