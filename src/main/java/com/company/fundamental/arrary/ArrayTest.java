package com.company.fundamental.arrary;

import java.util.Arrays;

public class ArrayTest<T> {
    public static void main(String[] args) {
//        Scoreboard board = new Scoreboard(4);
//        board.add(new GameEntry("shawn", 98));
//        board.add(new GameEntry("andy", 85));
//        board.add(new GameEntry("ian", 100));
//        board.add(new GameEntry("janice", 84));
//        board.add(new GameEntry("nasen", 85));
//        board.add(new GameEntry("seven", 88));
//        board.remove(1);
//        board.add(new GameEntry("sherry", 99));
//        board.remove(2);
//        board.remove(0);

//        char[] chars = new char[] {'f', 'b', 'c', 'a', 'd', 'a', 'e'};
//        insertionSort(chars);
//        System.out.println(Arrays.toString(chars));

//        Integer[] data = new Integer[10];
//        Random rand = new Random(System.currentTimeMillis());
//        for (int i = 0; i < data.length; i++) {
//            data[i] = rand.nextInt(100);
//        }
//        Integer[] orig = ArrayUtils.clone(data);
//        System.out.println("arrays equal before sort: " + ArrayUtils.isEquals(data, orig));
//        Arrays.sort(data, Collections.reverseOrder());
//        System.out.println("arrays equal before sort: " + ArrayUtils.isEquals(data, orig));
//        System.out.println("orig: " + ArrayUtils.toString(orig));
//        System.out.println("data: " + ArrayUtils.toString(data));

        char a = 'A' + 2;
        int[][] b = new int[3][];
        b[1] = new int[]{1, 2, 3};
        b[2] = new int[]{4, 5, 6};
        b[0] = new int[]{7, 8};

        int[][] c = b.clone();
        int[][] f = deepClone(b);
        String[] d = {"ab", "12"};
        String[] e = new String[3];
        e = d.clone();

    }

    private static void insertionSort(char[] chars) {
        if (chars.length <= 1) {
            return;
        }
        for (int x = 1; x < chars.length; x++) {
            int y = x;
            char cur = chars[y];
            while (y > 0 && cur < chars[y - 1]) {
                chars[y] = chars[y - 1];
                y--;
            }
            chars[y] = cur;
        }
    }

    public static int[][] deepClone(int[][] original) {
        int[][] backup = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            backup[i] = original[i].clone();
        }
        return backup;
    }
}
