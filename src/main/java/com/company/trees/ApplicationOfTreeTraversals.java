package com.company.trees;

import com.company.list.ArrayList;
import com.company.list.Position;

import java.util.Arrays;

public class ApplicationOfTreeTraversals {

    private static String spaces(int length) {
        char[] charArray = new char[length];
        Arrays.fill(charArray, ' ');
        return new String(charArray);
    }

    /** Prints preorder representation of subtree of T rooted at p having depth d. */
    public static <E> void printPreorderIndent(Tree<E> T, Position<E> p, int d) {
        System.out.println(spaces(2 * d) + p.getElement());
        for (Position<E> c : T.children(p)) {
            printPreorderIndent(T, c, d + 1);
        }
    }

    /** Prints labeled representation of subtree of T rooted at p having depth d. */
    public static <E> void printPreorderLabeled(Tree<E> T, Position<E> p, ArrayList<Integer> path) {
        int d = path.size(); // depth equals the length of the path
        System.out.print(spaces(2 * d)); // print indentation, then label
        for (int j=0; j < d; j++) {
            System.out.print(path.get(j) + (j == d - 1 ? " " : "."));
        }
        System.out.println(p.getElement());
        path.add(1); // add path entry for first child
        for (Position<E> c : T.children(p)) {
            printPreorderLabeled(T, c, path);
            path.set(d, 1 + path.get(d)); // increment last entry of path
        }
        path.remove(d); // restore path to its incoming state
    }


    /** Returns total disk space for subtree of T rooted at p. */
    public static int diskSpace(Tree<Integer> T, Position<Integer> p) {
        int subtotal = p.getElement(); // we assume element represents space usage
        for (Position<Integer> c : T.children(p)) {
            subtotal += diskSpace(T, c);
        }
        return subtotal;
    }

    /** Prints parenthesized representation of subtree of T rooted at p. */
    public static <E> void parenthesize(Tree<E> T, Position<E> p) {
        System.out.print(p.getElement());
        if (T.isInternal(p)) {
            boolean firstTime = true;
            for (Position<E> c : T.children(p)) {
                System.out.print((firstTime ? " (" : ", ")); // determine proper punctuation
                firstTime = false; // any future passes will get comma
                parenthesize(T, c); // recur on child
            }
            System.out.print(")");
        }
    }

    public static <E> int layout(BinaryTree<E> T, Position<E> p, int d, int x) {
        if (T.left(p) != null) {
            x = layout(T, T.left(p), d + 1, x); // resulting x will be increased
        }
//        p.getElement().setX(x++); // post-increment x
//        p.getElement().setY(d);
        if (T.right(p) != null) {
            x = layout(T, T.right(p), d + 1, x); // resulting x will be increased
        }
        return x;
    }
}
