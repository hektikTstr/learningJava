package com.company.recursion;

public class BinarySum {
    public static int BinarySum(int[] data, int low, int high) {
        if (low > high) {
            return 0;
        } else if (low == high) {
            return data[low];
        } else {
            int mid = (low + high) / 2;
            return BinarySum(data, low, mid) + BinarySum(data, mid + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        System.out.println(BinarySum(a, 0, 3));
    }
}
