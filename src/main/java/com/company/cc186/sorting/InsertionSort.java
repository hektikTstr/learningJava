package com.company.cc186.sorting;

import org.testng.annotations.Test;

import java.util.Arrays;

public class InsertionSort {
    public static void insertionSortArrBased(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int hole = i;
            while (hole > 0 && arr[hole - 1] > temp) {
                arr[hole] = arr[hole - 1];
                hole--;
            }
            arr[hole] = temp;
        }
    }

    @Test
    public void test() {
        int[] a = {5, 4, 3, 2, 1, 0};
        insertionSortArrBased(a);
        System.out.println(Arrays.toString(a));
    }
}
