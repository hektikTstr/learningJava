package com.company.recursion;

public class Ruler {
    /* Draws an English ruler for the given number of inches and major tick length. */
    public static void drawRuler(int nInches, int majorLength) {
        drawLine(majorLength, 0);
        for (int i = 1; i < nInches; i++) {
            drawInterval(majorLength -1);
            drawLine(majorLength, i);
        }
    }

    private static void drawInterval(int centralLength) {
        if (centralLength >= 1) {
            drawInterval(centralLength - 1);
            drawLine(centralLength);
            drawInterval(centralLength - 1);
        }
    }

    private static void drawLine(int tickLength, int tickLabel) {
        for (int j = 0; j < tickLength; j++) {
            System.out.print("-");
        }
        if (tickLabel >= 0) {
            System.out.print(" " + tickLabel);
        }
        System.out.print("\n");
    }

    /** Draws a line with the given tick length (but no label). */
    private static void drawLine(int tickLength) {
        drawLine(tickLength, -1);
    }

    public static void main(String[] args) {
        drawRuler(2, 4);
    }
}
