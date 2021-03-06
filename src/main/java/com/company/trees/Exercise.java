package com.company.trees;

import com.company.list.Position;
import org.testng.annotations.Test;

import java.util.Iterator;

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
        LinkedBinaryTree<Integer> newTree = tree2.cloneByAttach();
    }

    @Test
    public void c_8_40_41() {
        LinkedBinaryTree<Integer> tree2 = createTree();
        LinkedBinaryTree<Integer> newTree = tree2.clone();
    }

    @Test
    public void c_8_42() {
        LinkedBinaryTree<Integer> tree2 = createTree();
        tree2.printElementAndSubtreeHeight(tree2.root(), 0);
    }

    @Test
    public void c_8_43() {
        LinkedBinaryTree<Integer> tree2 = createTree();
        tree2.printAllNodesDepth(tree2.root(), 0);
    }

    @Test
    public void c_8_44() {
        LinkedBinaryTree<Integer> tree2 = createProperTree();
        tree2.printBalanceFactor(tree2.root(), 0);
    }

    @Test
    public void c_8_45() {
        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        Position<Integer> p1 = tree2.addRoot(1);
        Position<Integer> p2 = tree2.addRight(tree2.root(), 2);
        Position<Integer> p4 = tree2.addLeft(p2, 4);
        Position<Integer> p5 = tree2.addRight(p2, 5);
        Position<Integer> p7 = tree2.addRight(p5, 7);
        Position<Integer> p8 = tree2.addLeft(p5, 8);
        Position<Integer> p9 = tree2.addLeft(p8, 9);
        Position<Integer> p10 = tree2.addRight(p9, 10);
        Position<Integer> p11 = tree2.addLeft(p10, 11);
        Position<Integer> p12 = tree2.addRight(p11, 12);
        Position<Integer> p3 = tree2.addLeft(tree2.root(), 3);
        Position<Integer> p6 = tree2.addRight(p3, 6);
        Position<Integer> tempPos;
        tempPos = tree2.inorderNext(p1);
        tempPos = tree2.inorderNext(p2);
        tempPos = tree2.inorderNext(p3);
        tempPos = tree2.inorderNext(p4);
        tempPos = tree2.inorderNext(p5);
        tempPos = tree2.inorderNext(p6);
        tempPos = tree2.inorderNext(p7);
        tempPos = tree2.inorderNext(p8);
        tempPos = tree2.postorderNext(p1);
        tempPos = tree2.postorderNext(p2);
        tempPos = tree2.postorderNext(p3);
        tempPos = tree2.postorderNext(p4);
        tempPos = tree2.postorderNext(p5);
        tempPos = tree2.postorderNext(p6);
        tempPos = tree2.postorderNext(p7);
        tempPos = tree2.postorderNext(p4);
        Iterable<Position<Integer>> iterable = tree2.inorderLazy();
        Iterator<Position<Integer>> iterator = iterable.iterator();
        iterator.hasNext();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        tree2.preorderNext(tempPos);
    }

    @Test
    public void c_8_46() {
        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        Position<Integer> p1 = tree2.addRoot(1);
        Position<Integer> p2 = tree2.addRight(tree2.root(), 2);
        Position<Integer> p4 = tree2.addLeft(p2, 4);
        Position<Integer> p5 = tree2.addRight(p2, 5);
        Position<Integer> p7 = tree2.addRight(p5, 7);
        Position<Integer> p8 = tree2.addLeft(p5, 8);
        Position<Integer> p9 = tree2.addLeft(p8, 9);
        Position<Integer> p11 = tree2.addLeft(p9, 11);
        Position<Integer> p10 = tree2.addRight(p8, 10);
        Position<Integer> p3 = tree2.addLeft(tree2.root(), 3);
        Position<Integer> p6 = tree2.addRight(p3, 6);
        tree2.inorderLoop();
    }

    private LinkedBinaryTree<Integer> createTree() {
        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        tree2.addRoot(1);
        Position<Integer> tempPos = tree2.addRight(tree2.root(), 2);
        tree2.addLeft(tempPos, 4);
        tempPos = tree2.addRight(tempPos, 5);
        tree2.addRight(tempPos, 7);
        tree2.addLeft(tempPos, 8);
        tempPos = tree2.addLeft(tree2.root(), 3);
        tree2.addRight(tempPos, 6);
        return tree2;
    }

    private LinkedBinaryTree<Integer> createProperTree() {
        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        tree2.addRoot(1);
        Position<Integer> tempPos = tree2.addRight(tree2.root(), 2);
        tree2.addLeft(tempPos, 4);
        tempPos = tree2.addRight(tempPos, 5);
        Position<Integer> tempPos1 = tree2.addRight(tempPos, 7);
        tree2.addLeft(tempPos1, 11);
        tree2.addRight(tempPos1, 12);
        tree2.addLeft(tempPos, 8);
        tempPos = tree2.addLeft(tree2.root(), 3);
//        tree2.addRight(tempPos, 6);
//        tree2.addLeft(tempPos, 10);
        return tree2;
    }
}
