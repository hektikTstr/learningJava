package com.company.cc186.sorting;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class BinarySearch {
    public static int search(int[] arr, int value) {
        if (arr == null) {
            throw new IllegalArgumentException("The array should not be null.");
        }
        return search(arr, 0, arr.length - 1, value);
    }

    private static int search(int[] arr, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int middle = (low + high) / 2;
        if (arr[middle] == value) {
            return middle;
        } else if (value < arr[middle]) {
            return search(arr, low, middle - 1, value);
        } else {
            return search(arr, middle + 1, high, value);
        }
    }

    @Test
    public void test() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(100);
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int pick = 22;
        System.out.println(search(arr, pick));
    }
}
