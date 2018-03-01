// package com.company.cc186.sorting;

import java.util.Arrays;
// import java.util.Comparator;
import java.util.Random;

public class MergeSort<E> {
    // private Comparator<E> comp = new DefaultComparator<>();

    // /** Determines whether an element is valid. */
    // protected boolean checkIfComparable(E element) throws IllegalArgumentException {
    //     try {
    //         return (comp.compare(element, element) == 0);   // see if element can be compared to itself
    //     } catch (ClassCastException e) {
    //         throw new IllegalArgumentException("Incompatible element");
    //     }
    // }

    // /** Method for comparing two elements */
    // protected int compare(E a, E b) {
    //     return comp.compare(a, b);
    // }

    // public void mergeSort(E[] origArr) {
    //     E[] helperArr = (E[]) new Object[origArr.length];
    //     mergeSort(origArr, helperArr, 0, origArr.length - 1);
    // }

    // private void mergeSort(E[] origArr, E[] helperArr, int low, int high) {
    //     if (low >= high) {
    //         return;
    //     }
    //     int middle = (low + high) / 2;
    //     mergeSort(origArr, helperArr, low, middle);
    //     mergeSort(origArr, helperArr, middle + 1, high);
    //     merge(origArr, helperArr, low, middle, high);
    // }

    // private void merge(E[] origArr, E[] helperArr, int low, int middle, int high) {
    //     int leftIndex = low;
    //     int origIndex = low;
    //     int rightIndex = middle + 1;
    //     for (int i = low; i <= high; i++) {
    //         helperArr[i] = origArr[i];
    //     }

    //     while (leftIndex <= middle && rightIndex <= high) {
    //         checkIfComparable(helperArr[leftIndex]);
    //         checkIfComparable(helperArr[rightIndex]);
    //         if (compare(helperArr[leftIndex], helperArr[rightIndex]) <= 0) {
    //             origArr[origIndex] = helperArr[leftIndex++];
    //         } else {
    //             origArr[origIndex] = helperArr[rightIndex++];
    //         }
    //         origIndex++;
    //     }
    //     while (leftIndex <= middle) {
    //         origArr[origIndex++] = helperArr[leftIndex++];
    //     }
    //     while (rightIndex <= high) {
    //         origArr[origIndex++] = helperArr[rightIndex++];
    //     }
    // }

    public static void mergeSort(int start, int end, int[] arr) {
        if (start >= end) {
            return;
        } else {
            int mid = (start + end) / 2;
            mergeSort(start, mid, arr);
            mergeSort(mid + 1, end, arr);
            int i = start;
            int j = mid + 1;
            int index = 0;
            int[] newArr = new int[end - start + 1];
            while (i != mid + 1 && j != end + 1) {
                newArr[index++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
            }
            while (i != mid + 1) {
                newArr[index++] = arr[i++];
            }
            while (j != end + 1) {
                newArr[index++] = arr[j++];
            }
            for (int x = 0; x < newArr.length; x++) {
                arr[start + x] = newArr[x];
            }
        }
    }
    public static void main(String[] args) {
        // MergeSort<Integer> mergeSort = new MergeSort<>();
        // Integer[] intArr = new Integer[100];
        // for (int i = 0; i < 100; i++) {
        //     Random random = new Random();
        //     intArr[i] = random.nextInt(1000);
        // }
        // mergeSort.mergeSort(intArr);
        int[] intArr = new int[10];
        for (int i = 0; i < intArr.length; i++) {
            Random random = new Random();
            intArr[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(intArr));
        mergeSort(0, intArr.length - 1, intArr);
        System.out.println(Arrays.toString(intArr));
        int prev = intArr[0];
        for (int var : intArr) {
            if (prev >= var)
                System.out.println("catch");
            prev = var;
        }
        assert false;
    }
}
