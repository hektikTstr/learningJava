package com.company.list;

import org.testng.annotations.Test;

import java.util.Iterator;

// C-7.57
public class FavoritesArrayList<E> {
    protected static class Item<E> {
        private E value;
        private int count = 0;
        public Item(E val) { value = val; }
        public int getCount() { return count; }
        public E getValue() { return value; }
        public void increment() { count++; }
        private void resetCount() { count = 0; }
    }

    List<Item<E>> list = new ArrayList<>();
    public FavoritesArrayList() {}

    protected E value(int i) { return list.get(i).getValue(); }

    protected int count(int i) { return list.get(i).getCount(); }

    protected int findPosition(E e) {
        for (int i = 0; i < list.size(); i++) {
            if (e.equals(list.get(i).getValue())) {
                return i;
            }
        }
        return -1;
    }

    protected void moveUp(int i) {
        int cnt = count(i);
        int index = i;
        for (int k = i - 1; k >= 0; k--) {
            if (count(k) < cnt) {
                index = k;
            } else {
                break;
            }
        }
        list.add(index, list.remove(i));
    }

    public int size() { return list.size(); }

    public boolean isEmpty() { return list.isEmpty(); }

    public void access(E e) {
        int i = findPosition(e);
        Item<E> item = new Item<>(e);
        if (i == -1) {
            list.add(size(), item);
            i = size() - 1;
        }
        list.get(i).increment();
        moveUp(i);
    }

    public void remove(E e) {
        int i = findPosition(e);
        if (i != -1) {
            list.remove(i);
        }
    }

    public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException("Invalid k");
        }
        List<E> result = new ArrayList<>();
        Iterator<Item<E>> iter = list.iterator();
        for (int j = 0; j < k; j++) {
            result.add(result.size(), iter.next().getValue());
        }
        return result;
    }

    public void resetCounts() {
        for (Item<E> e : list) {
            e.resetCount();
        }
    }

    @Test
    public void test() {
        FavoritesArrayList<String> favoritesList = new FavoritesArrayList<>();
        favoritesList.access("google");
        favoritesList.access("baidu");
        favoritesList.access("sina");
        favoritesList.access("tencent");
        favoritesList.access("tencent");
        favoritesList.access("sina");
        favoritesList.access("google");
        Iterable iterable = favoritesList.getFavorites(2);
        favoritesList.resetCounts();
    }
}
