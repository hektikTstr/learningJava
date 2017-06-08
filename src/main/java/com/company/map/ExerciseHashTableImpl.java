package com.company.map;


import com.company.list.ArrayList;
import com.company.priority_queues.Entry;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ExerciseHashTableImpl<K, V> implements Map<K, V> {
    private class MapEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;

        public MapEntry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        public void setKey(K k) {
            this.k = k;
        }

        @Override
        public V getValue() {
            return v;
        }

        public void setValue(V v) {
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new ExerciseHashTableImpl<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
    }

    private class AuxUnsortedTableMap<K, V> implements Map<K, V> {
        private int ORIGINAL_SIZE = 4;
        private MapEntry<K, V>[] mapEntry = (MapEntry<K, V>[]) new MapEntry[ORIGINAL_SIZE];
        private int mapSize = 0;

        @Override
        public int size() {
            return mapSize;
        }

        @Override
        public boolean isEmpty() {
            return mapSize == 0;
        }

        private int findIndex(K key) {
            for (int i = 0; i < mapSize; i++) {
                if (mapEntry[i].getKey().equals(key)) {
                    return i;
                }
            }
            return -1;
        }

        private void resize(int newSize) {
            MapEntry[] newMapEntry = (MapEntry[]) new Object[newSize];
            for (int i = 0; i < mapSize; i++) {
                newMapEntry[i] = mapEntry[i];
            }
            mapEntry = newMapEntry;
        }

        @Override
        public V get(K key) {
            int index = findIndex(key);
            return index != -1 ? mapEntry[index].getValue() : null;
        }

        @Override
        public V put(K key, V value) {
            int index = findIndex(key);
            if (index != -1) {
                V oldValue = mapEntry[index].getValue();
                mapEntry[index].setValue(value);
                return oldValue;
            }
            if (mapSize > mapEntry.length / 2) {
                resize(mapEntry.length * 2);
            }
            mapEntry[mapSize] = new MapEntry<>(key, value);
            mapSize++;
            return null;
        }

        @Override
        public V remove(K key) {
            int index = findIndex(key);
            if (index != -1) {
                V value = mapEntry[index].getValue();
                for (int i = index; i < mapSize - 1; i++) {
                    mapEntry[i] = mapEntry[i + 1];
                }
                mapEntry[mapSize - 1] = null;
                mapSize--;
                return value;
            }
            return null;
        }

        private class EntryIterator implements Iterator<Entry<K, V>> {
            private int counter = 0;
            @Override
            public boolean hasNext() {
                return counter < mapSize;
            }

            @Override
            public Entry<K, V> next() {
                if (counter == mapSize) {
                    throw new NoSuchElementException();
                }
                return mapEntry[counter++];
            }
        }

        private class EntryIterable implements Iterable<Entry<K, V>> {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new EntryIterator();
            }
        }

        private class KeyIterator implements Iterator<K> {
            private Iterator<Entry<K, V>> entries = entrySet().iterator();

            @Override
            public boolean hasNext() {
                return entries.hasNext();
            }

            @Override
            public K next() {
                return entries.next().getKey();
            }
        }

        private class KeyIterable implements Iterable<K> {
            @Override
            public Iterator<K> iterator() {
                return new ExerciseHashTableImpl.KeyIterator();
            }
        }

        private class ValueIterator implements Iterator<V> {
            private Iterator<Entry<K, V>> entries = entrySet().iterator();

            @Override
            public boolean hasNext() {
                return entries.hasNext();
            }

            @Override
            public V next() {
                return entries.next().getValue();
            }
        }

        private class ValueIterable implements Iterable<V> {
            @Override
            public Iterator<V> iterator() {
                return new ExerciseHashTableImpl.ValueIterator();
            }
        }

        @Override
        public Iterable<K> keySet() {
            return new KeyIterable();
        }

        @Override
        public Iterable<V> values() {
            return new ValueIterable();
        }

        @Override
        public Iterable<Entry<K, V>> entrySet() {
            return new EntryIterable();
        }
    }

    public ExerciseHashTableImpl() {
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
    }

    private int size = 0;
    private int capacity = 3;
    private int prime = 109345121;
    private int scale;
    private int shift;

    private AuxUnsortedTableMap<K, V>[] dataBucket = new AuxUnsortedTableMap[capacity];

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hashValue(K key) {
        return ((Math.abs(scale * key.hashCode() + shift)) % prime) % capacity;
    }

    @Override
    public V get(K key) {
        AuxUnsortedTableMap<K, V> bucket = dataBucket[hashValue(key)];
        if (bucket == null)
            return null;
        return bucket.get(key);
    }

    @Override
    public V put(K key, V value) {
        int hashValue = hashValue(key);
        AuxUnsortedTableMap<K, V> bucket = dataBucket[hashValue];
        if (bucket == null) {
            bucket = dataBucket[hashValue] = new AuxUnsortedTableMap<>();
        }
        int oldSize = bucket.size();
        V oldValue = bucket.put(key, value);
        size += (bucket.size() - oldSize);
        if (size >= capacity / 2) {
            resize(capacity * 2 - 1);
        }
        return oldValue;
    }

    private void resize(int newSize) {
        ArrayList<Entry<K, V>> arrayList = new ArrayList<>();
        for (Entry<K, V> entry : entrySet()) {
            arrayList.add(entry);
        }
        capacity = newSize;
        dataBucket = new AuxUnsortedTableMap[newSize];
        size = 0;
        for (Entry<K, V> entry: arrayList) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(K key) {
        int hashValue = hashValue(key);
        AuxUnsortedTableMap<K, V> bucket = dataBucket[hashValue];
        if (bucket == null) {
            return null;
        }
        int oldSize = bucket.size();
        V oldValue = bucket.remove(key);
        size -= (bucket.size() - oldSize);
        return oldValue;
    }

    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();
        }
    }

    private class KeyIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();
        }
    }

    private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }


    @Override
    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (AuxUnsortedTableMap<K, V> map : dataBucket) {
            if (map != null) {
                for (Entry<K, V> entry : map.entrySet()) {
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }
}
