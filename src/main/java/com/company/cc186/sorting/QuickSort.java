package com.company.cc186.sorting;

import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort<E> {
    private Comparator<E> comp = new DefaultComparator<>();


    /** Determines whether an element is valid. */
    protected boolean checkIfComparable(E element) throws IllegalArgumentException {
        try {
            return (comp.compare(element, element) == 0);   // see if element can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible element");
        }
    }

    /** Method for comparing two elements */
    protected int compare(E a, E b) {
        return comp.compare(a, b);
    }

    public void quickSort(E[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void validIndex(E[] arr, int index) {
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("");
        }
    }

    private void swap(E[] arr, int x, int y) {
        validIndex(arr, x);
        validIndex(arr, y);
        E temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private void quickSort(E[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = ThreadLocalRandom.current().nextInt(start, end + 1);
        swap(arr, pivot, end);
        int partitionIndex = partition(arr, start, end);
        quickSort(arr, start, partitionIndex - 1);
        quickSort(arr, partitionIndex + 1, end);
//        swap(arr, partitionIndex, end);
    }

    private int partition(E[] arr, int start, int end) {
        E pivot = arr[end];
        int partitionIndex = start;
        for (int i = start; i <= end - 1; i++) {
            if (compare(arr[i], pivot) < 0) {
                if (i != partitionIndex) {
                    swap(arr, i, partitionIndex);
                }
                partitionIndex += 1;
            }
        }
        swap(arr, partitionIndex, end);
        return partitionIndex;
    }

    @Test
    public void testRandom() {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(5));
        }
    }

    @Test
    public void testQuickSort() {
//        Integer[] intArr = new Integer[]{8, 2, 6, 3, 5, 1, 3, 7, 6};
        Integer[] intArr = new Integer[100];
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            intArr[i] = random.nextInt(1000);
        }
        QuickSort<Integer> quickSort = new QuickSort<>();
        quickSort.quickSort(intArr);
        System.out.println(Arrays.toString(intArr));
        for (int i = 0; i < 99; i++) {
            Assert.assertTrue("intArr[i] = " + intArr[i], intArr[i] <= intArr[i + 1]);
        }
    }
}
