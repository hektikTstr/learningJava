package com.company.recursion;

import java.util.Arrays;

public class Fibonacci {
    public static long[] fibonacci(int n) {
        if (n <= 1) {
            long[] answer = {n, 0};
            return answer;
        } else {
            long[] temp = fibonacci(n - 1);
            long[] answer = {temp[0] + temp[1], temp[0]};
            return answer;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(fibonacci(4)));
    }
}
