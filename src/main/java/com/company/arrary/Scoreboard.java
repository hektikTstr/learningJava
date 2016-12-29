package com.company.arrary;

public class Scoreboard {
    private int numEntries = 0;
    private GameEntry[] board;
    public Scoreboard(int capacity) {
        board = new GameEntry[capacity];
    }
    public void add(GameEntry e) {
        int newScore = e.getScore();
        int index = numEntries;
        if (index < board.length || newScore > board[index - 1].getScore()) {
            if (index < board.length) {
                board[numEntries++] = e;
            }
            while (index > 0 && newScore > board[index - 1].getScore()) {
                if (index < board.length) {
                    board[index] = board[index - 1];
                }
                board[index - 1] = e;
                index--;
            }
        }
    }
}