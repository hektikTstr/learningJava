package com.company.trees;

import com.company.list.ArrayList;
import com.company.list.List;
import com.company.list.Position;
import com.company.queue.LinkedQueue;
import com.company.queue.Queue;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int depth(Position<E> p) {
        if (isRoot(p)) {
            return 0;
        } else {
            return 1 + depth(parent(p));
        }
    }

    private int heightBad() {
        int h = 0;
        for (Position<E> p : positions()) {
            if (isExternal(p)) {
                h = Math.max(h, depth(p));
            }
        }
        return h;
    }

    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    public int printElementAndSubtreeHeight(Position<E> p, int depth) {
        int h = 0;
        String spaces = StringUtils.repeat(" ", depth);
        System.out.println(spaces + "p = " + p.getElement());
        for (Position<E> c : children(p)) {
            int height = printElementAndSubtreeHeight(c, depth + 1);
            System.out.println(spaces + "subtree height = " + height);
            h = Math.max(h, 1 + height);
        }
        return h;
    }

    public void printAllNodesDepth(Position<E> p, int currentDepth) {
        System.out.println("p = " + p.getElement());
        System.out.println("depth = " + currentDepth);
        for (Position<E> c : children(p)) {
            printAllNodesDepth(c, currentDepth + 1);
        }
    }

    /** This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();
        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> positions() {
        return preorder();
    }

    /** Adds positions of the subtree rooted at Position p to the given snapshot. */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c : children(p)) {
            preorderSubtree(c, snapshot);
        }
    }

    /** Returns an iterable collection of positions of the tree, reported in preorder. */
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    // C-8.47
    public Iterable<Position<E>> preorderLazy() {
        return new AbstractTreeIterable(new PreorderNext());
    }

    // C-8.48
    public Iterable<Position<E>> postorderLazy() {
        return new AbstractTreeIterable(new PostorderNext());
    }

    protected class AbstractTreeIterable implements Iterable<Position<E>> {
        private NextTreePosition<E> nextTreePosition;
        public AbstractTreeIterable(NextTreePosition<E> nextTreePosition) {
            this.nextTreePosition = nextTreePosition;
        }
        @Override
        public Iterator<Position<E>> iterator() {
            return new AbstractTreeIterator(nextTreePosition);
        }
    }

    private class AbstractTreeIterator implements Iterator<Position<E>> {
        private Position<E> cursor = null;
        private Position<E> recent = null;
        private NextTreePosition<E> nextTreePosition = null;

        public AbstractTreeIterator(NextTreePosition<E> nextTreePosition) {
            this.nextTreePosition = nextTreePosition;
            cursor = nextTreePosition.startNode();
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Position<E> next() {
            if (cursor == null) {
                throw new NoSuchElementException("nothing left");
            }
            recent = cursor;
            cursor = nextTreePosition.next(recent);
            return recent;
        }
    }

    protected interface NextTreePosition<E> {
        Position<E> next(Position<E> position);
        Position<E> startNode();
    }

    private class PreorderNext implements NextTreePosition<E> {
        @Override
        public Position<E> next(Position<E> position) {
            return preorderNext(position);
        }

        @Override
        public Position<E> startNode() {
            return root();
        }
    }

    private class PostorderNext implements NextTreePosition<E> {
        @Override
        public Position<E> next(Position<E> position) {
            return postorderNext(position);
        }

        @Override
        public Position<E> startNode() {
            Position<E> p = root();
            Iterator<Position<E>> iterator = children(p).iterator();
            while (iterator.hasNext()) {
                p = iterator.next();
                iterator = children(p).iterator();
            }
            return p;
        }
    }

    protected Position<E> preorderNext(Position<E> p) {
        Iterator<Position<E>> children = children(p).iterator();
        if (children.hasNext()) {
            return children.next();
        }
        Position<E> parent = parent(p);
        while (p != null && parent != null) {
            Iterator<Position<E>> iterator = children(parent).iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == p && iterator.hasNext()) {
                    return iterator.next();
                }
            }
            p = parent;
            parent = parent(p);
        }
        return null;
    }

    // C-8.48
    protected Position<E> postorderNext(Position<E> p) {
        Position<E> parent = parent(p);
        // root
        if (parent == null) {
            return null;
        }
        Iterator<Position<E>> iterator = children(parent).iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == p && iterator.hasNext()) {
                p = iterator.next();
                iterator = children(p).iterator();
                while (iterator.hasNext()) {
                    p = iterator.next();
                    iterator = children(p).iterator();
                }
                return p;
            }
        }
        // p is the right most node of its parent, so return the parent
        return parent;
    }

    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p)) {
            postorderSubtree(c, snapshot);
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    public Iterable<Position<E>> breadthfirst() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.dequeue();
                snapshot.add(p);
                for (Position<E> c : children(p)) {
                    fringe.enqueue(c);
                }
            }
        }
        return snapshot;
    }
}
