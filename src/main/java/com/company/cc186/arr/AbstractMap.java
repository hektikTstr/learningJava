package com.company.cc186.arr;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    public boolean isEmpty() { return size() == 0; }

    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public K setKey(K key) {
            K temp = this.key;
            this.key = key;
            return temp;
        }

        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }
    }

    public class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K, V>> iterator = entrySet().iterator();

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public K next() {
            return iterator.next().getKey();
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    public class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    public class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> iterator = entrySet().iterator();

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public V next() {
            return iterator.next().getValue();
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    public class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    public Iterable<V> values() {
        return new ValueIterable();
    }

}
