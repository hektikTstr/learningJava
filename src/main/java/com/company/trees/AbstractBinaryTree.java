package com.company.trees;

import com.company.list.ArrayList;
import com.company.list.List;
import com.company.list.Position;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(p) != null) {
            snapshot.add(left(p));
        }
        if (right(p) != null) {
            snapshot.add(right(p));
        }
        return snapshot;
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;
        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }
        return count;
    }

    public int numChildrenRecursive(Position<E> p) {
        int count = 0;
        Position<E> position = left(p);
        if (position != null) {
            count++;
            count += numChildrenRecursive(position);
        }
        position = right(p);
        if (position != null) {
            count++;
            count += numChildrenRecursive(position);
        }
        return count;
    }

    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Position<E> parent = parent(p);
        if (parent == null) return null; // p must be the root
        if (p == left(parent)) { // p is a left child
            return right(parent); // (right child might be null)
        } else { // p is a right child
            return left(parent); // (left child might be null)
        }
    }

    @Override
    public Position<E> preorderNext(Position<E> p) throws IllegalArgumentException {
        if (left(p) != null) {
            return left(p);
        }
        if (right(p) != null) {
            return right(p);
        }
        while (p != null) {
            Position<E> parent = parent(p);
            if (parent == null) {
                return null;
            }
            if (p == left(parent)) {
                if (right(parent) != null) {
                    return right(parent);
                } else {
                    p = parent(p);
                }
            } else if (p == right(parent)) {
                p = parent;
            } else {
                assert false;
            }
        }
        return null;
    }

    @Override
    public Position<E> inorderNext(Position<E> p) throws IllegalArgumentException {
        Position<E> tempNode = p;
        while (left(tempNode) != null) {
            tempNode = left(tempNode);
        }
        if (tempNode != p) {
            return tempNode;
        }
        if (right(tempNode) != null) {
            if (isInternal(right(tempNode))) {
                tempNode = inorderNext(right(tempNode));
            } else {
                return right(tempNode);
            }
        }
        if (tempNode != p) {
            return tempNode;
        }
        while (tempNode != null) {
            Position<E> parent = parent(tempNode);
            if (parent == null) {
                return null;
            }
            if (tempNode == left(parent) && (right(parent) != null)) {
                return inorderNext(right(parent));
            }
            if (tempNode == right(parent)) {
                tempNode = parent;
            }
        }
        return null;
    }

    @Override
    public Position<E> postorderNext(Position<E> p) throws IllegalArgumentException {
        return null;
    }

    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) {
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if (right(p) != null) {
            inorderSubtree(right(p), snapshot);
        }
    }

    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    public Iterable<Position<E>> positions() {
        return inorder();
    }
}
