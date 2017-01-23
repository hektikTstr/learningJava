package com.company.recursion;

public class Exercise {
    private static int index = 0;
    // R-5.1
    private static int findMax(int[] a, int low, int high) {
        index++;
        if (low >= high) {
            return a[low];
        } else {
            int mid = (low + high) / 2;
            int temp1 = findMax(a, low, mid);
            int temp2 = findMax(a, mid + 1, high);
            return Math.max(temp1, temp2);
        }
    }

    private static double harmonic(int n) {
        if (n == 1) {
            return 1.0;
        } else {
            return 1.0 / n + harmonic(n - 1);
        }
    }

    private static int stringToInt(String str, int index) {
        if (index == str.length() - 1) {
            return str.charAt(index) - '0';
        } else {
            double power = str.length() - index - 1;
            return (int) Math.pow(10.0, power) * (str.charAt(index) - '0') + stringToInt(str, index + 1);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 0, 4, 9, 2, 6, 5, 4, 10, 3};
        System.out.println(findMax(a, 0, a.length - 1));
        System.out.println(index);
        System.out.println(harmonic(3));
        System.out.println(stringToInt("012340550", 0));
    }
}
