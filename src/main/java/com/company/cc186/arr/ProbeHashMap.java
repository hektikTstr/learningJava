package com.company.cc186.arr;


import com.company.list.List;
import org.testng.annotations.Test;

import java.util.Random;

public class ProbeHashMap<K, V> extends AbstractMap<K, V> {
    private MapEntry<K, V> DEFUNCT_CELL = new MapEntry<>(null, null);
    private static final int DEFAULT_CAPACITY = 17;
    private static final int DEFAULT_PRIME = 109345121;
    private int capacity;
    private int size;
    private int prime;
    private long scale, shift;
    private MapEntry<K, V> table[];

    public ProbeHashMap() {
        capacity = DEFAULT_CAPACITY;
        prime = DEFAULT_PRIME;
        size = 0;
        Random rand = new Random();
        scale = rand.nextInt(prime);
        shift = rand.nextInt(prime - 1) + 1;
        table = new MapEntry[DEFAULT_CAPACITY];
    }

    private int hashCode(int original_code) {
        return (int) ((scale * Math.abs(original_code) + shift) % prime) % capacity;
    }
//
//    private int findSlot(K key) {
//        int orig_index = hashCode(key.hashCode());
//        int index = orig_index;
//        //
//        while (table[index] != null && (table[index] == DEFUNCT_CELL || !table[index].getKey().equals(key))) {
//            index = (index + 1) % capacity;
//            if (index == orig_index) {
//                break;
//            }
//        }
//        if (index == orig_index && table[orig_index] != null && table[index] != DEFUNCT_CELL && !table[orig_index].getKey().equals(key)) {
//            return -1;
//        } else {
//            return index;
//        }
//    }

    private boolean isAvailable(int index) {
        return table[index] == null || table[index] == DEFUNCT_CELL;
    }

    private int findSlot(K key, int start_index) {
        int avail = -1;
        int index = start_index;

        do {
            // Check if the current slot is null or defunc, then set it as the first available slot.
            if (isAvailable(index)) {
                if (avail == -1) {
                    avail = index;
                }
                // The slot is null means the search is over, there is no such
                // entry with the key, then break and return a less than -1 index.
                // If it is defunc, the search will continue to the next loop,
                // but the avail slot index will be recorded and converted to
                // a negative number and returned if doesn't find the key
                // after all loops being executed.
                if (table[index] == null) {
                    break;
                }
            // If the key is found, return the slot immediately.
            } else if (table[index].getKey().equals(key)) {
                return index;
            }
            index = (index + 1) % capacity;
        } while (start_index != index);

        // If the key isn't found and there is no either null slot and no defunc slot,
        // it will return -1.
        return - avail - 2;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int index = findSlot(key, hashCode(key.hashCode()));
        if (index < 0) {
            return null;
        }
        return table[index].getValue();
    }

    private void resize(int new_cap) {
        if (new_cap <= capacity) {
            throw new IllegalArgumentException();
        }

        List<Entry<K, V>> list = new ArrayList<>();
        for (Entry<K, V> entry : entrySet()) {
            list.add(entry);
        }
        table = new MapEntry[new_cap];
        capacity = new_cap;
        size = 0;
        for (int i = 0; i < list.size(); i++) {
            put(list.get(i).getKey(), list.get(i).getValue());
        }
        System.out.println("******* resize: " + new_cap);
    }

    public V put(K key, V value) {
        int index = findSlot(key, hashCode(key.hashCode()));
        if (index < 0) {
            if (index == -1) {
                // not found and table should be resized
                resize(2 * capacity - 1);
                return put(key, value);
            } else {
                size++;
                table[-(index+2)] = new MapEntry<>(key, value);
                return null;
            }
        } else {
            return table[index].setValue(value);
        }
    }

    public V remove(K key) {
        int index = findSlot(key, hashCode(key.hashCode()));
        if (index < 0) {
            return null;
        }
        V value = table[index].getValue();
        table[index] = DEFUNCT_CELL;
        size--;
        return value;
    }

    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> list = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i] != DEFUNCT_CELL) {
                list.add(table[i]);
            }
        }
        return list;
    }


    @Test
    public void test() {
        Map<String, String> map = new ProbeHashMap<>();
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
        System.out.println(map.remove("zhang"));
        System.out.println(map.remove("ye"));
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
        System.out.println(map.put("o", "p"));
        System.out.println(map.remove("jiang"));
        System.out.println(map.size());
        System.out.println(map.put("m", "n"));
        System.out.println(map.size());
        System.out.println(map.put("r", "s"));
        System.out.println(map.put("1", "2"));
        System.out.println(map.put("2", "3"));
        System.out.println(map.put("3", "4"));
        System.out.println(map.put("4", "5"));
        System.out.println(map.put("5", "6"));
        System.out.println(map.put("6", "7"));
        System.out.println(map.put("7", "8"));
        System.out.println(map.put("8", "9"));
        System.out.println(map.put("9", "0"));
        System.out.println(map.put("0", "1"));
        System.out.println(map.put("10", "11"));
        System.out.println(map.put("11", "12"));
        System.out.println(map.put("12", "1"));
        System.out.println(map.put("13", "1"));
        System.out.println(map.put("14", "1"));
        System.out.println(map.put("15", "1"));
        System.out.println(map.put("16", "1"));
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

