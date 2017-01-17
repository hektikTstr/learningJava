package com.company.recursion;

public class BinarySearch {
    public static boolean binarySearch(int[] data, int target, int low, int high) {
        if (low > high) {
            return false;
        } else {
            int mid = (low + high) / 2;
            if (target == data[mid]) {
                return true;
            } else if (target < data[mid]) {
                return binarySearch(data, target, low, mid - 1);
            } else {
                return binarySearch(data, target, mid + 1, high);
            }
        }
    }

    public static void main(String[] arg) {
        int[] a = {2, 9, 16, 23};
        binarySearch(a, 5, 0, 3);
    }
}
