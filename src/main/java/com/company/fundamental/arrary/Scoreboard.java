package com.company.fundamental.arrary;

public class Scoreboard {
    private int numEntries = 0;
    private GameEntry[] board;
    public Scoreboard(int capacity) {
        if (capacity <= 0) {
            throw new UnsupportedOperationException("capacity must be a positive integer");
        }
        board = new GameEntry[capacity];
    }
    public void add(GameEntry e) {
        int newScore = e.getScore();
        if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
            if (numEntries < board.length) {
                numEntries++;
            }
            int index = numEntries - 1;
            while (index > 0 && newScore > board[index - 1].getScore()) {
                board[index] = board[index - 1];
                index--;
            }
            board[index] = e;
        }
        System.out.println("#############################");
        System.out.println("-> add: " + e);
        printBoard();
    }

    private void printBoard() {
        for (GameEntry g : board) {
            if (g != null) {
                System.out.println(g);
            }
        }
    }

    public GameEntry remove(int i) {
        int index = i;
        if (index < 0 || index >= board.length) {
            throw new IndexOutOfBoundsException("index should be in range");
        }
        GameEntry temp = board[index];
        while (index < numEntries - 1) {
            board[index] = board[index + 1];
            index++;
        }
        board[index] = null;
        numEntries--;
        System.out.println("#############################");
        System.out.println("-> remove: " + i);
        printBoard();
        return temp;
    }
}