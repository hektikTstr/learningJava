import java.util.Arrays;

// package com.company.cc186.sorting;
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        for (int i = arr.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    System.out.println(Arrays.toString(arr));
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {3, 8, 9, 2, 7, 0, 5};
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        int array[] = null;
        bubbleSort(array);
    }
}