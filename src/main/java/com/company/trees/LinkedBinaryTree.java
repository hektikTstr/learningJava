package com.company.trees;

import com.company.list.Position;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> implements Cloneable {
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
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
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
        sentinel = createNode(null, null, root, null);
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
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    // C-8.38
    /** Attaches trees t1 and t2 as left and right subtrees of external p. */
    public void attachSentinel(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        } else {
            node.setLeft(new Node<E>(null, node, null, null));
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setLeft(t2.root);
            t2.root = null;
            t2.size = 0;
        } else {
            node.setRight(new Node<E>(null, node, null, null));
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

    private Node<E> sentinel;
    /** Removes the node at Position p and replaces it with its child, if any. */
    public E removeNodeSentinel(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("p has two children");
        }
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) {
            child.setParent(node.getParent());
        } else {
            child = new Node<E>(null, node.getParent(), null, null);
        }
        if (node == root) {
            root = child;
            sentinel.setLeft(root);
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

    // C-8.36
    public void pruneSubtree(Position<E> position) {
        if (position == root) {
            root = null;
            size = 0;
            return;
        }
        Node<E> node = validate(position);
        int num = numChildrenRecursive(position);
        if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(null);
        } else {
            node.getParent().setRight(null);
        }
        size -= num + 1;
    }

    // C-8.37
    public void swap(Position<E> p1, Position<E> p2) {
        Node<E> n1 = validate(p1);
        Node<E> n2 = validate(p2);
        Node<E> parent1 = n1.getParent();
        Node<E> parent2 = n2.getParent();
        if (n1 == root) {
            root = n2;
        } else if (n2 == root) {
            root = n1;
        }
        if (parent1 == n2) {
            switchAdjacentNode(n2, n1, parent2);
        } else if (parent2 == n1) {
            switchAdjacentNode(n1, n2, parent1);
        } else {
            // switch parents
            n1.setParent(parent2);
            if (parent1 != null) {
                if (parent1.getLeft() == n1) {
                    parent1.setLeft(n2);
                } else {
                    parent1.setRight(n2);
                }
            }
            n2.setParent(parent1);
            if (parent2 != null) {
                if (parent2.getLeft() == n2) {
                    parent2.setLeft(n1);
                } else {
                    parent2.setRight(n1);
                }
            }
            // switch children
            Node<E> tempNode1 = n1.getLeft();
            Node<E> tempNode2 = n2.getLeft();
            n1.setLeft(tempNode2);
            if (tempNode2 != null) {
                tempNode2.setParent(n1);
            }
            n2.setLeft(tempNode1);
            if (tempNode1 != null) {
                tempNode1.setParent(n2);
            }
            tempNode1 = n1.getRight();
            tempNode2 = n2.getRight();
            n1.setRight(tempNode2);
            if (tempNode2 != null) {
                tempNode2.setParent(n1);
            }
            n2.setRight(tempNode1);
            if (tempNode1 != null) {
                tempNode1.setParent(n2);
            }
        }
    }

    private void switchAdjacentNode(Node<E> n1, Node<E> n2, Node<E> parent1) {
        Node<E> tempNode1;
        Node<E> tempNode2;
        n2.setParent(parent1);
        n1.setParent(n2);
        if (parent1 != null) {
            if (parent1.getLeft() == n1) {
                parent1.setLeft(n2);
            } else {
                parent1.setRight(n2);
            }
        }
        tempNode1 = n2.getLeft();
        tempNode2 = n2.getRight();
        if (n1.getLeft() == n2) {
            n2.setLeft(n1);
            n2.setRight(n1.getRight());
            n1.getRight().setParent(n2);
        } else {
            n2.setRight(n1);
            n2.setLeft(n1.getLeft());
            n1.getLeft().setParent(n2);
        }
        n1.setLeft(tempNode1);
        n1.setRight(tempNode2);
        if (tempNode1 != null) {
            tempNode1.setParent(n1);
        }
        if (tempNode2 != null) {
            tempNode2.setParent(n1);
        }
    }

    // C-8.39
    public LinkedBinaryTree<E> cloneByAttach() {
        LinkedBinaryTree<E> newTree = new LinkedBinaryTree<E>();
        newTree.addRoot(root.getElement());
        recursiveCloneWithAttatch(root, newTree.root, newTree);
        return newTree;
    }

    private void recursiveCloneWithAttatch(Position<E> orig, Position<E> dest, LinkedBinaryTree<E> destTree) {
        Node<E> destNode = validate(dest);
        if (left(orig) != null) {
            LinkedBinaryTree<E> newTree1 = new LinkedBinaryTree<>();
            newTree1.addRoot(left(orig).getElement());
            LinkedBinaryTree<E> newTree2 = new LinkedBinaryTree<>();
            newTree2.addRoot(right(orig).getElement());
            destTree.attach(dest, newTree1, newTree2);
            recursiveCloneWithAttatch(left(orig), destNode.getLeft(), destTree);
            recursiveCloneWithAttatch(right(orig), destNode.getRight(), destTree);
        }
    }

    // C-8.40
    public LinkedBinaryTree<E> clone() {
        LinkedBinaryTree<E> newTree = new LinkedBinaryTree<>();
        newTree.addRoot(root.getElement());
        recursiveClone(root, newTree.root, newTree);
        return newTree;
    }

    private void recursiveClone(Position<E> orig, Position<E> dest, LinkedBinaryTree<E> destTree) {
        Node<E> origNode = validate(orig);
        Node<E> destNode = validate(dest);
        if (origNode.getLeft() != null) {
            destTree.addLeft(destNode, origNode.getLeft().getElement());
            recursiveClone(origNode.getLeft(), destNode.getLeft(), destTree);
        }
        if (origNode.getRight() != null) {
            destTree.addRight(destNode, origNode.getRight().getElement());
            recursiveClone(origNode.getRight(), destNode.getRight(), destTree);
        }
    }

    public int printBalanceFactor(Position<E> p, int depth) {
        validate(p);
        int h = 0;
        String spaces = StringUtils.repeat(" ", depth);
        System.out.println(spaces + "p = " + p.getElement());
        if (isInternal(p)) {
            int heightL = printBalanceFactor(left(p), depth + 1);
            int heightR = printBalanceFactor(right(p), depth + 1);
            System.out.println(spaces + "heightL - heightR = " + (heightL - heightR));
            h = Math.max(heightL, heightR) + 1;
        }
        return h;
    }

    // R-8.5
    @Test
    public void test() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(0);
        Position<Integer> pos = tree.parent(tree.root());
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
