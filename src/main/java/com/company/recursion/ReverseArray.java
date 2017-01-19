package com.company.recursion;

import java.util.Arrays;

public class ReverseArray {
    public static int[] reverseArray(int[] data, int low, int high) {
        if (low < high) {
            int temp = data[low];
            data[low] = data[high];
            data[high] = temp;
            reverseArray(data, low + 1, high - 1);
        }
        return data;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(reverseArray(a, 0, a.length - 1)));
    }
}
