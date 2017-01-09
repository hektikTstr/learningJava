package com.company.fundamental.exercises;

import java.util.Random;

public class ArraysExercises {
    private static int[] genPseudoRandomNums(int seed, int amount) {
        int[] pseudoRandomNums = new int[amount];
        for (int i = 0; i < pseudoRandomNums.length; i++) {
            pseudoRandomNums[i] = (12 * seed + 5) % 100;
            seed = pseudoRandomNums[i];
        }
        return pseudoRandomNums;
    }

    private static void removeRandomArrEntryUntilEmpty(int[] arr) {
        Random random = new Random();
        while (arr.length > 0) {
            int index = random.nextInt(arr.length);
            System.out.println(arr[index]);
            int[] tempArr = new int[arr.length - 1];
            for (int i = 0; i < index; i++) {
                tempArr[i] = arr[i];
            }
            for (int i = index; i < arr.length - 1; i++) {
                tempArr[i] = arr[i + 1];
            }
            arr = tempArr;
        }
    }

    public static void main(String[] args) {
//        // R-3.1
//        int[] pseudoRandomNums = genPseudoRandomNums(92, 5);
//        for (int pseudoRandomNum : pseudoRandomNums) {
//            System.out.println(pseudoRandomNum);
//        }
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
        removeRandomArrEntryUntilEmpty(array);
    }
}
