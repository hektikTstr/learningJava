package com.company.arrary;

public class TicTacToe {
    public static final int X = 1, O = -1;
    public static final int EMPTY = 0;
    private int board[][] = new int[3][3];
    private int player;

    public TicTacToe() {
        clearBoard();
    }

    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
        player = X;
    }

    public void putMark(int i, int j) throws IllegalArgumentException {
        if ((i < 0) || (i > 2) || (j < 0) || (j > 2)) {
            throw new IllegalArgumentException("Invalid board position");
        }
        if (board[i][j] != EMPTY) {
            throw new IllegalArgumentException("Board position occupied");
        }
        board[i][j] = player;
        player = -player;
        System.out.println(this + "\n");
    }

    public boolean isWin(int mark) {
        return ((board[0][0] + board[0][1] + board[0][2] == mark * 3)
                || (board[1][0] + board[1][1] + board[1][2] == mark * 3)
                || (board[2][0] + board[2][1] + board[2][2] == mark * 3)
                || (board[0][0] + board[1][0] + board[2][0] == mark * 3)
                || (board[0][1] + board[1][1] + board[2][1] == mark * 3)
                || (board[0][2] + board[1][2] + board[2][2] == mark * 3)
                || (board[0][0] + board[1][1] + board[2][2] == mark * 3)
                || (board[2][0] + board[1][1] + board[0][2] == mark * 3));
    }

    public int winner() {
        if (isWin(X)) {
            return X;
        } else if (isWin(O)) {
            return O;
        } else {
            return EMPTY;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case X:
                        sb.append("X");
                        break;
                    case O:
                        sb.append("O");
                        break;
                    case EMPTY:
                        sb.append(" ");
                        break;
                }
                if (j < 2) {
                    sb.append("|");
                }
            }
            if (i < 2) {
                sb.append("\n-----\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[ ] args) {
        TicTacToe game = new TicTacToe();
        String[] outcome={"O wins", "Tie", "X wins"};
        int winningPlayer = game.winner();
        System.out.println(outcome[1 + winningPlayer]);
        game.putMark(1, 1);
        winningPlayer = game.winner();
        System.out.println(outcome[1 + winningPlayer]);
        game.putMark(2, 2);
        winningPlayer = game.winner();
        System.out.println(outcome[1 + winningPlayer]);
        game.putMark(0, 1);
        winningPlayer = game.winner();
        System.out.println(outcome[1 + winningPlayer]);
        game.putMark(1, 2);
        winningPlayer = game.winner();
        System.out.println(outcome[1 + winningPlayer]);
        game.putMark(2, 1);
        winningPlayer = game.winner();
        System.out.println(outcome[1 + winningPlayer]);
        game.putMark(0, 2);
        winningPlayer = game.winner();
        System.out.println(outcome[1 + winningPlayer]);
    }
}
