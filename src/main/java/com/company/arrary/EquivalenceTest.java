package com.company.arrary;

import java.util.Arrays;

public class EquivalenceTest {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("Arrays.equals(a, b): " + Arrays.equals(a, b));

        String[] c = {"ab", "cd", "ef"};
        String[] d = {"ab", "cd", "ef"};
        System.out.println("c == d: " + (c == d));
        System.out.println("c.equals(d): " + c.equals(d));
        System.out.println("Arrays.equals(c, d): " + Arrays.equals(c, d));

        int[][] e = {{1, 2}, {3, 4}};
        int[][] f = {{1, 2}, {3, 4}};
        System.out.println("e == f: " + (e == f));
        System.out.println("e.equals(f): " + e.equals(f));
        System.out.println("Arrays.equals(e, f): " + Arrays.equals(e, f));
        System.out.println("Arrays.equals(e, f): " + Arrays.deepEquals(e, f));
    }
}
