package com.company.cc186.sorting;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SelectionSort {
    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void selectionSort(int[] arr) {
        if (arr.length == 1)
            return;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    @Test
    public void testSingleElem() {
        int[] a = {0};
        selectionSort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void testTwoElems() {
        int[] a = {2, 0};
        selectionSort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void testMultipleElems() {
        int[] a = new int[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(1000);
        }
        selectionSort(a);
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < a.length - 1; i++) {
            Assert.assertTrue(a[i] <= a[i + 1]);
        }
    }

    @Test
    public void testDuplicateElems() {
        int[] a = {2, 5, 5, 2, 3, 2, 0};
        selectionSort(a);
        System.out.println(Arrays.toString(a));
    }
}
