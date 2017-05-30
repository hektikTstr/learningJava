package com.company.priority_queues;

import com.company.list.ArrayList;
import com.company.list.List;

import java.util.Comparator;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue() { super(); }

    public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for (int j = 0; j < Math.min(keys.length, values.length); j++)
            heap.add(new PQEntry<>(keys[j], values[j]));
        heapify();
    }

    protected void heapify() {
        int startIndex = parent(size() - 1);
        for (int j = startIndex; j >= 0; j--)
            downheap(j);
    }

    protected int parent(int j) { return (j - 1) / 2; }

    protected int left(int j) { return 2 * j + 1; }

    protected int right(int j) { return 2 * j + 2; }

    protected boolean hasLeft(int j) { return left(j) < heap.size(); }

    protected boolean hasRight(int j) { return right(j) < heap.size(); }

    protected void swap(int i, int j) {
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    protected void upheap(int j) {
        while (j > 0) { // continue until reaching root (or break statement)
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) break; // heap property verified
            swap(j, p);
            j = p; // continue from the parent's location }
        }
    }

    protected void downheap(int j) {
        while (hasLeft(j)) { // continue to bottom (or break statement)
            int leftIndex = left(j);
            int smallChildIndex = leftIndex; // although right may be smaller
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex; // right child is smaller
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break; // heap property has been restored
            swap(j, smallChildIndex);
            j = smallChildIndex; // continue at position of the child
        }
    }

    public int size() { return heap.size(); }

    public Entry<K,V> min( ) {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key); // auxiliary key-checking method (could throw exception)
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest); // add to the end of the list
        upheap(heap.size() - 1); // upheap newly added entry
        return newest;
    }

    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size() - 1); // put minimum item at the end
        heap.remove(heap.size() - 1); // and remove it from the list;
        downheap(0); // then fix new root
        return answer;
    }

    private void recursivelyCompare(K num, int pos, List<K> list) {
        if (compare(heap.get(pos), new PQEntry<>(num, null)) < 0) {
            list.add(heap.get(pos).getKey());
            if (hasLeft(pos)) {
                recursivelyCompare(num, left(pos), list);
            }
            if (hasRight(pos)) {
                recursivelyCompare(num, right(pos), list);
            }
        } else if (compare(heap.get(pos), new PQEntry<>(num, null)) == 0) {
            list.add(heap.get(pos).getKey());
        }
    }

    public List<K> getAllKeysLessThanOrEqualTo(K key) {
        List<K> list = new ArrayList<>();
        recursivelyCompare(key, 0, list);
        return list;
    }
}
