package com.company.bigo;

import org.testng.annotations.Test;

public class BigOTest {
    @Test
    public void testPermutation() {
        permutation("abc", "");
        System.out.println(String.format("permutationBaseCaseCallNumber = %d\npermutationBeforeBCCallNumber = %d", permutationBaseCaseCallNumber, permutationBeforeBCCallNumber));
    }

    static int permutationBaseCaseCallNumber = 0;
    static int permutationBeforeBCCallNumber = 0;

    void permutation(String str, String prefix) {
        if (str.length() == 0) {
            permutationBaseCaseCallNumber++;
            System.out.println(prefix);
            System.out.println("# permutationBaseCaseCallNumber = " + permutationBaseCaseCallNumber + "\n");
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutationBeforeBCCallNumber++;
                System.out.println("* permutationBeforeBCCallNumber = " + permutationBeforeBCCallNumber);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

    static int recursiveNumber = 0;
    int fib(int n) {
        System.out.println(recursiveNumber++);
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    @Test
    public void testFib() {
        fib(3);
    }

    @Test
    public void testSortedString() {
        printSortedStrings(2);
    }

    int numChars = 5;

    void printSortedStrings(int remaining) {
        printSortedStrings(remaining, "");
    }

    void printSortedStrings(int remaining, String prefix) {
        if (remaining == 0) {
            if (isInOrder(prefix)) {
                System.out.println(prefix);
            } else {
                System.out.println("==" + prefix);
            }
        } else {
            for (int i = 0; i < numChars; i++) {
                char c = ithLetter(i);
                printSortedStrings(remaining - 1, prefix + c);
            }
        }
    }

    boolean isInOrder(String s) {
        for (int i = 1; i < s.length(); i++) {
            int prev = ithLetter(s.charAt(i - 1));
            int curr = ithLetter(s.charAt(i));
            if (prev > curr) {
                return false;
            }
        }
        return true;
    }

    char ithLetter(int i) {
        return (char) (((int) 'a') + i);
    }
}
