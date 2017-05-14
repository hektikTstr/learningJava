package com.company.trees;

import com.company.list.ArrayList;
import com.company.list.List;
import com.company.list.Position;
import javafx.geometry.Pos;

import java.util.Iterator;

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

    private class InorderNext implements NextTreePosition<E> {
        @Override
        public Position<E> next(Position<E> position) {
            return inorderNext(position);
        }

        @Override
        public Position<E> startNode() {
            Position<E> p = root();
            while (left(p) != null) {
                p = left(p);
            }
            return p;
        }
    }

    // C-8.49
    Iterable<Position<E>> inorderLazy() {
        return new AbstractTreeIterable(new InorderNext());
    }

    @Override
    public Position<E> inorderNext(Position<E> p) throws IllegalArgumentException {
        Position<E> tempNode = right(p);
        if (tempNode != null) {
            while (left(tempNode) != null) {
                tempNode = left(tempNode);
            }
            return tempNode;
        } else {
            while (p != null) {
                Position<E> parent = parent(p);
                if (parent == null) {
                    break;
                }
                if (left(parent) == p) {
                    return parent;
                }
                if (right(parent) == p) {
                    p = parent;
                }
            }
        }
        return null;
    }

    @Override
    public Position<E> postorderNext(Position<E> p) throws IllegalArgumentException {
        Position<E> parent = parent(p);
        if (parent == null) {
            return null;
        }
        if (right(parent) == p) {
            return parent;
        }
        if (left(parent) == p) {
            Position<E> tempNode = right(parent);
            if (tempNode == null) {
                return parent;
            }
            while (true) {
                while (left(tempNode) != null) {
                    tempNode = left(tempNode);
                }
                if (right(tempNode) == null) {
                    return tempNode;
                }
                tempNode = right(tempNode);
            }
        }
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

    public void inorderLoop() {
        Position<E> p = root();
        while (true) {
            while (left(p) != null) {
                p = left(p);
            }
            System.out.println(p.getElement());
            if (right(p) != null) {
                p = right(p);
            }
            else {
                Position<E> parent = parent(p);
                while (parent != null) {
                    if (left(parent) == p) {
                        System.out.println(parent.getElement());
                        if (right(parent) != null) {
                            p = right(parent);
                            break;
                        } else {
                            p = parent;
                        }
                    }
                    if (right(parent) == p) {
                        p = parent;
                        if (p == root()) {
                            return;
                        }
                    }
                    parent = parent(p);
                }
            }
        }
    }

    public Iterable<Position<E>> positions() {
        return inorder();
    }
}
