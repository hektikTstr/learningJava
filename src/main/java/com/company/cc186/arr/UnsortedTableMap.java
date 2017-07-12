package com.company.cc186.arr;


import com.company.list.List;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    private List<MapEntry<K, V>> table = new ArrayList<>();

    public UnsortedTableMap() {}

    public int size() {
        return table.size();
    }

    private int findIndex(K key) {
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public V get(K key) {
        int index = findIndex(key);
        return index == -1 ? null : table.get(index).getValue();
    }

    public V put(K key, V value) {
        int index = findIndex(key);
        if (index == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else {
            return table.get(index).setValue(value);
        }
    }

    public V remove(K key) {
        int index = findIndex(key);
        if (index == -1)
            return null;
        int lastElemIndex = size() - 1;
        V tempValue = table.get(index).getValue();
        if (index != lastElemIndex) {
            table.set(index, table.get(lastElemIndex));
        }
        table.remove(lastElemIndex);
        return tempValue;
    }

    public class EntryIterator implements Iterator<Entry<K, V>> {
        private int index = 0;

        public boolean hasNext() {
            return index < table.size();
        }

        public Entry<K, V> next() {
            if (index == table.size())
                throw new NoSuchElementException();
            return table.get(index++);
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    public class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    @Test
    public void test() {
        Map<String, String> unsorted_map = new UnsortedTableMap<>();
        System.out.println(unsorted_map.get("zhang"));
        System.out.println(unsorted_map.put("zhang", "xiaoyang"));
        System.out.println(unsorted_map.size());
        System.out.println(unsorted_map.remove("zhang"));
        System.out.println(unsorted_map.size());
        System.out.println(unsorted_map.get("zhang"));
        System.out.println(unsorted_map.put("zhang", "xiaoyang"));
        System.out.println(unsorted_map.put("ye", "xiaomin"));
        System.out.println(unsorted_map.size());
        System.out.println(unsorted_map.put("ye", "xiaomin"));
        System.out.println(unsorted_map.size());
        System.out.println(unsorted_map.put("zhang1", "qiaoyi"));
        System.out.println(unsorted_map.size());
        for (String key : unsorted_map.keySet()) {
            System.out.println(key);
        }
        for (String value : unsorted_map.values()) {
            System.out.println(value);
        }
        for (Entry<String, String> entry : unsorted_map.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }
}
