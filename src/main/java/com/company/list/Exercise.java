package com.company.list;

import org.testng.annotations.Test;

import java.util.*;

public class Exercise {

    @Test
    public void test() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8}; // allowed by autoboxing
        java.util.List listArr = (java.util.List) Arrays.asList(arr);
        Collections.shuffle(listArr);

        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[][] arrChance = new int[8][8];
        for (int i = 0; i < 1000; i++) {
            shuffle(arr1);
            for (int k = 0; k < arr1.length; k++) {
                arrChance[arr1[k] - 1][k]++;
            }
        }
    }

    // C-7.28
    public static void shuffle(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomInt = random.nextInt(arr.length);
            int temp = arr[randomInt];
            arr[randomInt] = arr[i];
            arr[i] = temp;
        }
    }

    @Test
    public void test1() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(8));
        }
    }

}
