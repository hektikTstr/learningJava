package com.company.arrary;

import java.util.Arrays;

public class ArrayTest {
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

        char[] chars = new char[] {'f', 'b', 'c', 'a', 'd', 'a', 'e'};
        insertionSort(chars);
        System.out.println(Arrays.toString(chars));
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
}
