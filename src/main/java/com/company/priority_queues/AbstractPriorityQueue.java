package com.company.priority_queues;

import com.company.list.PositionalList;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
    protected static class PQEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;

        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        protected void setKey(K key) {
            k = key;
        }

        protected void setValue(V value) {
            v = value;
        }
    }

    private Comparator<K> comp;

    protected AbstractPriorityQueue(Comparator<K> c) { comp = c; }

    protected AbstractPriorityQueue() { this(new DefaultComparator<K>()); }

    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key,key) == 0); // see if key can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    public boolean isEmpty() { return size() == 0; }

    public static <E> void pqSort(PositionalList<E> S, PriorityQueue<E,?> P) {
        int n = S.size();
        for (int j = 0; j < n; j++) {
            E element = S.remove(S.first());
            P.insert(element, null); // element is key; null value
        }
        for (int j = 0; j < n; j++) {
            E element = P.removeMin().getKey();
            S.addLast(element); // the smallest key in P is next placed in S
        }
    }
}
