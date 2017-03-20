package com.company.trees;

import com.company.list.Position;
import javafx.geometry.Pos;
import org.testng.annotations.Test;

import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }
        public E getElement() { return element; }
        public Node<E> getParent() { return parent; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }
        public void setElement(E e) { element = e; }
        public void setParent(Node<E> parentNode) { parent = parentNode; }
        public void setLeft(Node<E> leftChild) { left = leftChild; }
        public void setRight(Node<E> rightChild) { right = rightChild; }
    }

    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    protected Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree() {}

    // nonpublic utility
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree"); return node;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    /** Places element e at the root of an empty tree and returns its new Position. */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    /** Creates a new left child of Position p storing element e; returns its Position. */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null); parent.setLeft(child);
        size++;
        return child;
    }

    /** Creates a new right child of Position p storing element e; returns its Position. */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null); parent.setRight(child);
        size++;
        return child;
    }

    /** Replaces the element at Position p with e and returns the replaced element. */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /** Attaches trees t1 and t2 as left and right subtrees of external p. */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setLeft(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /** Removes the node at Position p and replaces it with its child, if any. */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("p has two children");
        }
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) {
            child.setParent(node.getParent());
        }
        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); // our convention for defunct node
        return temp;
    }

    // R-8.5
    public static <E> int countLeftChildLeavesNum(BinaryTree<E> tree, Position<E> startPos) {
        if (tree == null || startPos == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        Position<E> posLeft = tree.left(startPos);
        Position<E> posRight = tree.right(startPos);
        if (posLeft == null && posRight == null) {
            return 0;
        }

        Position<E> tempLeft;
        Position<E> tempRight;
        if (posLeft != null) {
            tempLeft = tree.left(posLeft);
            tempRight = tree.right(posLeft);
            if (tempLeft == null && tempRight == null) {
                count = 1;
            } else {
                count += countLeftChildLeavesNum(tree, posLeft);
            }
        }

        if (posRight != null) {
            tempLeft = tree.left(posRight);
            tempRight = tree.right(posRight);
            if (tempLeft != null || tempRight != null) {
                count += countLeftChildLeavesNum(tree, posRight);
            }
        }

        return count;
    }

    // R-8.5
    @Test
    public void test() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(0);
        Position<Integer> position1 = tree.addLeft(tree.root(), 1);
        Position<Integer> position2 = tree.addRight(tree.root(), 2);
        tree.addLeft(position1, 3);
        position1 = tree.addRight(position1, 4);
        position1 = tree.addRight(position1, 7);
        tree.addLeft(position1, 8);
        position1 = tree.addLeft(position2, 5);
        position2 = tree.addRight(position2, 6);
        tree.addLeft(position1, 9);
        tree.addRight(position1, 10);
        tree.addLeft(position2, 11);
        tree.addRight(position2, 12);
        int a = countLeftChildLeavesNum(tree, tree.root());
    }
}
