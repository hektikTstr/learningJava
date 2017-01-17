package com.company.recursion;

public class LinerSum {
    public static int linearSum(int[] data, int n) {
        if (n == 0) {
            return 0;
        } else {
            return linearSum(data, n - 1) + data[n - 1];
        }
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4};
        System.out.println(linearSum(data, 3));
    }
}
