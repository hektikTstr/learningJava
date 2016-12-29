package com.company.arrary;

public class ArrayTest {
    public static void main(String[] args) {
        Scoreboard board = new Scoreboard(3);
        board.add(new GameEntry("shawn", 98));
        board.add(new GameEntry("andy", 85));
        board.add(new GameEntry("ian", 100));
        board.add(new GameEntry("janice", 84));
        board.add(new GameEntry("nasen", 85));
        board.add(new GameEntry("seven", 88));
        board.add(new GameEntry("sherry", 99));
    }
}
