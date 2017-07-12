package com.company.cc186.arr;

import com.company.list.List;
import org.testng.annotations.Test;

import java.util.Random;

public class ChainHashMap<K, V> extends AbstractMap<K, V> {
    private static final int DEFAULT_CAPACITY = 17;
    private static final int DEFAULT_PRIME = 109345121;
    private int capacity;
    private int prime;
    private long scale, shift;
    private int size;
    private UnsortedTableMap<K, V>[] table;

    public ChainHashMap() {
        capacity = DEFAULT_CAPACITY;
        prime = DEFAULT_PRIME;
        size = 0;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        table = new UnsortedTableMap[DEFAULT_CAPACITY];
    }

    private int countSlotIndex(int original_code) {
        return (int)((scale * Math.abs(original_code) + shift) % prime) % capacity;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int index = countSlotIndex(key.hashCode());
        if (table[index] == null) {
            return null;
        } else {
            return table[index].get(key);
        }
    }

    private void resize(int new_cap) {
        UnsortedTableMap<K, V>[] new_table = new UnsortedTableMap[new_cap];
        List<Entry<K, V>> list = new ArrayList<>();
        for (Entry<K, V> entry : entrySet()) {
            list.add(entry);
        }
        capacity = new_cap;
        size = 0;
        table = new_table;
        for (Entry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
        System.out.println("******* resize: " + new_cap);
    }

    public V put(K key, V value) {
        int index = countSlotIndex(key.hashCode());
        if (table[index] != null) {
            int orig_size = table[index].size();
            V old_value = table[index].put(key, value);
            size += table[index].size() - orig_size;
            return old_value;
        } else {
            if (size >= capacity / 2) {
                resize(2 * capacity - 1);
                index = countSlotIndex(key.hashCode());
            }
            UnsortedTableMap<K, V> unsorted_map = new UnsortedTableMap<>();
            unsorted_map.put(key, value);
            table[index] = unsorted_map;
            size++;
            return null;
        }
    }

    public V remove(K key) {
        int index = countSlotIndex(key.hashCode());
        if (table[index] == null) {
            return null;
        }
        int old_size = table[index].size();
        V value = table[index].remove(key);
        size -= old_size - table[index].size();
        return value;
    }

    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> list = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i].entrySet()) {
                    list.add(entry);
                }
            }
        }
        return list;
    }
//
//    private class EntryIterator implements Iterator<Entry<K, V>> {
//        private List<Entry<K, V>> list = new ArrayList<>();
//        private int index;
//
//        public EntryIterator() {
//            index = 0;
//            for (int i = 0; i < table.length; i++) {
//                if (table[i] != null) {
//                    for (Entry<K, V> entry : table[i].entrySet()) {
//                        list.add(entry);
//                    }
//                }
//            }
//        }
//
//        @Override
//        public boolean hasNext() {
//            return index != size;
//        }
//
//        @Override
//        public Entry<K, V> next() {
//            return list.get(index++);
//        }
//
//        public void remove(K key) {
//            throw new UnsupportedOperationException();
//        }
//    }

//    private class EntryIterator implements Iterator<Entry<K, V>> {
//        private int slot_index = 0;
//        private Iterator<Entry<K, V>> bucket_iter;
//        private int cur_pos = 0;
//
//        public boolean hasNext() {
//            return cur_pos != size;
//        }
//
//        private void pointToAvailSlot() {
//            for (int i = slot_index; i < table.length; i++) {
//                if (table[i] == null) {
//                    slot_index++;
//                } else {
//                    return;
//                }
//            }
//        }
//
//        public Entry<K, V> next() {
//            if (slot_index == capacity) {
//                throw new NoSuchElementException();
//            }
//
//            while (bucket_iter == null || !bucket_iter.hasNext()) {
//                if (bucket_iter != null && !bucket_iter.hasNext()) {
//                    slot_index++;
//                }
//                pointToAvailSlot();
//                bucket_iter = table[slot_index].entrySet().iterator();
//            }
//
//            cur_pos++;
//            return bucket_iter.next();
//        }
//
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//    }
//
//    private class EntryIterable implements Iterable<Entry<K, V>> {
//        public Iterator<Entry<K, V>> iterator() {
//            return new EntryIterator();
//        }
//    }

    @Test
    public void test() {
        Map<String, String> map = new ChainHashMap<>();
        System.out.println(map.get("zhang"));
        System.out.println(map.put("zhang", "xiaoyang"));
        System.out.println(map.size());
        System.out.println(map.remove("zhang"));
        System.out.println(map.size());
        System.out.println(map.get("zhang"));
        System.out.println(map.put("zhang", "xiaoyang"));
        System.out.println(map.put("ye", "xiaomin"));
        System.out.println(map.size());
        System.out.println(map.put("ye", "xiaomin"));
        System.out.println(map.size());
        System.out.println(map.put("zhang1", "qiaoyi"));
        System.out.println(map.put("yuan", "honghong"));
        System.out.println(map.put("chen", "qiaoen"));
        System.out.println(map.put("xi", "jinping"));
        System.out.println(map.put("jiang", "zemin"));
        System.out.println(map.put("li", "kaifu"));
        System.out.println(map.put("zeng", "yiduan"));
        System.out.println(map.put("weng", "xinxing"));
        System.out.println(map.put("liu", "jingjing"));
        System.out.println(map.put("andy", "lai"));
        System.out.println(map.put("nil", "norum"));
        System.out.println(map.put("hello", "world"));
        System.out.println(map.put("a", "b"));
        System.out.println(map.put("c", "d"));
        System.out.println(map.put("e", "f"));
        System.out.println(map.put("g", "h"));
        System.out.println(map.put("u", "v"));
        System.out.println(map.put("i", "j"));
        System.out.println(map.put("k", "l"));
        System.out.println(map.size());
        for (Entry<String, String> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
        for (String key : map.keySet()) {
            System.out.println(key);
        }
        for (String value : map.values()) {
            System.out.println(value);
        }
        System.out.println(map.remove("jiang"));
        System.out.println(map.size());
        System.out.println(map.remove("zhang"));
        System.out.println(map.size());
        System.out.println(map.remove("liu"));
        System.out.println(map.size());
        System.out.println(map.remove("zeng"));
        System.out.println(map.size());
        System.out.println(map.remove("xi"));
        System.out.println(map.size());
        for (Entry<String, String> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }
}
