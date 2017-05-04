package com.company.trees;

import com.company.list.Position;
import org.testng.annotations.Test;

public class Exercise {

    private <E> boolean isIsomorphic(LinkedBinaryTree<E> t1, Position<E> n1, LinkedBinaryTree<E> t2, Position<E> n2) {
        // Both roots are NULL, trees isomorphic by definition
        if (n1 == null && n2 == null) {
            return true;
        }

        // Exactly one of the n1 and n2 is NULL, trees not isomorphic
        if (n1 == null || n2 == null) {
            return false;
        }

        if (!n1.getElement().equals(n2.getElement())) {
            return false;
        }

        // There are two possible cases for n1 and n2 to be isomorphic
        // Case 1: The subtrees rooted at these nodes have NOT been "Flipped".
        // Both of these subtrees have to be isomorphic, hence the &&
        // Case 2: The subtrees rooted at these nodes have been "Flipped"
        return (isIsomorphic(t1, t1.left(n1), t2, t2.left(n2)) && isIsomorphic(t1, t1.right(n1), t2, t2.right(n2)))
                || (isIsomorphic(t1, t1.left(n1), t2, t2.right(n2)) && isIsomorphic(t1, t1.right(n1), t2, t2.left(n2)));
    }

    // C-8.33
    @Test
    public void c_8_33() {
        // create 2 binary trees
        LinkedBinaryTree<Integer> tree1 = new LinkedBinaryTree<>();
        tree1.addRoot(1);
        Position<Integer> tempPos = tree1.addLeft(tree1.root(), 2);
        tree1.addLeft(tempPos, 4);
        tempPos = tree1.addRight(tempPos, 5);
        tree1.addLeft(tempPos, 7);
        tree1.addRight(tempPos, 8);
        tempPos = tree1.addRight(tree1.root(), 3);
        tree1.addLeft(tempPos, 6);

        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        tree2.addRoot(1);
        tempPos = tree2.addRight(tree2.root(), 2);
        Position<Integer> p2 = tempPos;
        tree2.addLeft(tempPos, 4);
        Position<Integer> p1 = tree2.root();
        tempPos = tree2.addRight(tempPos, 5);
        tree2.addRight(tempPos, 7);
        tree2.addLeft(tempPos, 8);
        tempPos = tree2.addLeft(tree2.root(), 3);
        tree2.addRight(tempPos, 6);
        tree2.swap(p1, p2);
        boolean isomorphic = isIsomorphic(tree1, tree1.root(), tree2, tree2.root());

        int i = tree2.numChildrenRecursive(tree2.root());
        tree2.pruneSubtree(tempPos);
    }

    @Test
    public void c_8_37_1() {
        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        tree2.addRoot(1);
        Position<Integer> tempPos = tree2.addRight(tree2.root(), 2);
        Position<Integer> p2 = tempPos;
        tree2.addLeft(tempPos, 4);
        Position<Integer> p1 = tree2.root();
        tempPos = tree2.addRight(tempPos, 5);
        tree2.addRight(tempPos, 7);
        tree2.addLeft(tempPos, 8);
        tempPos = tree2.addLeft(tree2.root(), 3);
        tree2.addRight(tempPos, 6);
        tree2.swap(p1, p2);
    }

    @Test
    public void c_8_37_2() {
        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        tree2.addRoot(1);
        Position<Integer> tempPos = tree2.addRight(tree2.root(), 2);
        Position<Integer> p2 = tempPos;
        Position<Integer> p1 = tree2.addLeft(tempPos, 4);
        tempPos = tree2.addRight(tempPos, 5);
        tree2.addRight(tempPos, 7);
        tree2.addLeft(tempPos, 8);
        tempPos = tree2.addLeft(tree2.root(), 3);
        tree2.addRight(tempPos, 6);
        tree2.swap(p1, p2);
    }

    @Test
    public void c_8_37_3() {
        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        tree2.addRoot(1);
        Position<Integer> tempPos = tree2.addRight(tree2.root(), 2);
        tree2.addLeft(tempPos, 4);
        tempPos = tree2.addRight(tempPos, 5);
        Position<Integer> p2 = tempPos;
        tree2.addRight(tempPos, 7);
        tree2.addLeft(tempPos, 8);
        tempPos = tree2.addLeft(tree2.root(), 3);
        Position<Integer> p1 = tempPos;
        tree2.addRight(tempPos, 6);
        tree2.swap(p1, p2);
    }

    @Test
    public void c_8_39() {
        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        tree2.addRoot(1);
        Position<Integer> tempPos = tree2.addRight(tree2.root(), 2);
        tree2.addLeft(tempPos, 4);
        tempPos = tree2.addRight(tempPos, 5);
        tree2.addRight(tempPos, 7);
        tree2.addLeft(tempPos, 8);
        tree2.addLeft(tree2.root(), 3);
        LinkedBinaryTree<Integer> newTree = tree2.clone();
    }
}
