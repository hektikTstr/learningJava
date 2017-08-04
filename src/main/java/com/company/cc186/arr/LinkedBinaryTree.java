package com.company.cc186.arr;


import com.company.list.List;

import java.util.Iterator;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
    private int size;
    private TreeNode<E> root;

    protected static class TreeNode<E> implements Position<E> {
        private E element;
        private TreeNode<E> parent;
        private TreeNode<E> left;
        private TreeNode<E> right;

        public TreeNode(E element, TreeNode<E> parent, TreeNode<E> left, TreeNode<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public E getElement() {
            return element;
        }

        public E setElement(E element) {
            E temp = this.element;
            this.element = element;
            return temp;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public TreeNode<E> setParent(TreeNode<E> parent) {
            TreeNode<E> temp = this.parent;
            this.parent = parent;
            return temp;
        }

        public TreeNode<E> getLeft() {
            return left;
        }

        public TreeNode<E> getRight() {
            return right;
        }

        public TreeNode<E> setLeft(TreeNode<E> left) {
            TreeNode<E> temp = this.left;
            this.left = left;
            return temp;
        }

        public TreeNode<E> setRight(TreeNode<E> right) {
            TreeNode<E> temp = this.right;
            this.right = right;
            return temp;
        }
    }

    private TreeNode<E> validatePosition(Position<E> p) {
        if (!(p instanceof TreeNode)) {
            throw new IllegalArgumentException("Not valid position type");
        }
        TreeNode<E> treeNode = (TreeNode<E>) p;
        if (treeNode == treeNode.getParent()) {
            throw new IllegalArgumentException("The treeNode is no longer in the tree");
        }
        return treeNode;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> root() {
        return root;
    }

    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    public int numChildren(Position<E> p) {
        TreeNode<E> parent = validatePosition(p);
        int count = 0;
        if (parent.getLeft() != null) {
            count++;
        }
        if (parent.getRight() != null) {
            count++;
        }
        return count;
    }

    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    private TreeNode<E> createNode(E e, TreeNode<E> parent, TreeNode<E> left, TreeNode<E> right) {
        return new TreeNode(e, parent, left, right);
    }

    public Position<E> left(Position<E> p) {
        TreeNode<E> parent = validatePosition(p);
        return parent.getLeft();
    }

    public Position<E> right(Position<E> p) {
        TreeNode<E> parent = validatePosition(p);
        return parent.getRight();
    }

    public Position<E> parent(Position<E> p) {
        TreeNode<E> treeNode = validatePosition(p);
        return treeNode.getRight();
    }

    public Position<E> sibling(Position<E> p) {
        TreeNode<E> treeNode = validatePosition(p);
        if (treeNode.getParent() == null) {
            return null;
        }
        if (treeNode.getParent().getLeft() == treeNode) {
            return treeNode.getParent().getRight();
        } else {
            return treeNode.getParent().getLeft();
        }
    }

    public Position<E> addRoot(E e) {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree is not empty.");
        }
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E e) {
        TreeNode<E> parent = validatePosition(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("Parent left child isn't null");
        }
        TreeNode<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e) {
        TreeNode<E> parent = validatePosition(p);
        if (parent.getRight() != null) {
            throw new IllegalArgumentException("Parent right child isn't null");
        }
        TreeNode<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> p, E e) {
        TreeNode<E> treeNode = validatePosition(p);
        return treeNode.setElement(e);
    }

    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) {
        TreeNode<E> treeNode = validatePosition(p);
        if (isInternal(treeNode)) {
            throw new IllegalArgumentException("The position is not external.");
        }
        if (!t1.isEmpty()) {
            treeNode.setLeft(t1.root);
            ((TreeNode<E>)t1.root()).setParent(treeNode);
            t1.root = null;
            size += t1.size();
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            treeNode.setRight(t2.root);
            ((TreeNode<E>)t2.root()).setParent(treeNode);
            t2.root = null;
            size += t2.size();
            t2.size = 0;
        }
    }

    public E remove(Position<E> p) {
        TreeNode<E> treeNode = validatePosition(p);
        if (numChildren(treeNode) == 2) {
            throw new IllegalArgumentException("The position has 2 children");
        }
        E ret = treeNode.getElement();
        TreeNode<E> child = treeNode.getLeft() != null ? treeNode.getLeft() : treeNode.getRight();
        if (child != null) {
            child.setParent(treeNode.getParent());
        }
        if (treeNode == root) {
            root = child;
        } else {
            if (treeNode == treeNode.getParent().getLeft()) {
                treeNode.getParent().setLeft(child);
            } else {
                treeNode.getParent().setRight(child);
            }
        }
        size--;
        treeNode.setElement(null);
        treeNode.setLeft(null);
        treeNode.setRight(null);
        treeNode.setParent(treeNode);
        return ret;
    }

    public int depth(Position<E> p) {
        TreeNode<E> treeNode = validatePosition(p);
        if (treeNode == root) {
            return 0;
        } else {
            return depth(treeNode.getParent()) + 1;
        }
    }

    public int height(Position<E> p) {
        int height = 0;
        for (Position<E> child : children(p)) {
            height = Math.max(height, 1 + height(child));
        }
        return height;
    }

    public Iterable<Position<E>> children(Position<E> p) {
        TreeNode<E> treeNode = validatePosition(p);
        List<Position<E>> list = new ArrayList<>();
        if (treeNode.getLeft() != null) {
            list.add(treeNode.getLeft());
        }
        if (treeNode.getRight() != null) {
            list.add(treeNode.getRight());
        }
        return list;
    }

    public Iterable<Position<E>> positions() {
        List<Position<E>> list = new ArrayList<>();
        // preorder
        preorderTraversal(root, list);

        // inorder
        inorderTraversal(root, list);

        // postorder
        postorderTraversal(root, list);

        return list;
    }

    public Iterator<E> iterator() {
        List<E> list = new ArrayList<>();
        for (Position<E> p : positions()) {
            list.add(p.getElement());
        }
        return list.iterator();
    }

    private void preorderTraversal(TreeNode<E> treeNode, List<Position<E>> list) {
        list.add(treeNode);
        if (treeNode.getLeft() != null) {
            preorderTraversal(treeNode.getLeft(), list);
        }
        if (treeNode.getRight() != null) {
            preorderTraversal(treeNode.getRight(), list);
        }
    }

    private void inorderTraversal(TreeNode<E> treeNode, List<Position<E>> list) {
        if (treeNode.getLeft() != null) {
            inorderTraversal(treeNode.getLeft(), list);
        }
        list.add(treeNode);
        if (treeNode.getRight() != null) {
            inorderTraversal(treeNode.getRight(), list);
        }
    }

    private void postorderTraversal(TreeNode<E> treeNode, List<Position<E>> list) {
        if (treeNode.getLeft() != null) {
            postorderTraversal(treeNode.getLeft(), list);
        }
        if (treeNode.getRight() != null) {
            postorderTraversal(treeNode.getRight(), list);
        }
        list.add(treeNode);
    }
}

