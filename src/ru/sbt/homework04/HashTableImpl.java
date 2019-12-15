package ru.sbt.homework04;

import java.util.Arrays;
import java.util.Objects;

public class HashTableImpl<K, V> implements HashTable<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 32;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] array;
    private int size;
    private int capacity;
    private float loadFactor;


    public HashTableImpl(int initialCapacity, float loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.array = createArray(capacity);
        this.size = 0;
    }

    public HashTableImpl(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTableImpl() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }


    public V put(K key, V value) {
        if ((float) (size + 1) / capacity > loadFactor) {
            resize();
        }
        return putEntry(key, value);
    }

    public V get(K key) {
        Entry<K, V> entry = array[findEntry(key)];
        return entry == null ? null : entry.getValue();
    }

    public V remove(K key) {
        int idx = findEntry(key);
        if (array[idx] == null) return null;
        V value = array[idx].getValue();
        removeEntry(idx);
        size--;
        return value;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        return Arrays.stream(array).anyMatch(entry -> (entry != null) && (entry.getValue() == value));
    }

    public int size() {
        return size;
    }


    @SuppressWarnings("unchecked")
    private Entry<K, V>[] createArray(int capacity) {
        return new Entry[capacity];
    }

    private int findEntry(K key) { //TODO: fix infinite loop if array is full
        int idx = getStartIndex(key);
        while (true) {
            Entry<K, V> entry = array[idx];
            if (entry == null || Objects.equals(entry.getKey(), key)) return idx;
            idx = getNextIdx(idx);
        }
    }

    private V putEntry(K key, V value) {
        int idx = findEntry(key);
        if (array[idx] == null) {
            array[idx] = new Entry<>(key, null);
            size++;
        }

        V oldValue = array[idx].getValue();
        array[idx].setValue(value);
        return oldValue;
    }

    private void removeEntry(int lastFreeIndex) {
        array[lastFreeIndex] = null;
        int currIdx = lastFreeIndex;
        while (true) {
            currIdx = getNextIdx(currIdx);
            if (array[currIdx] == null) return;
            int startIdx = getStartIndex(array[currIdx].getKey());
            if ((lastFreeIndex < currIdx) ?
                    (lastFreeIndex >= startIdx) || (startIdx > currIdx) :
                    (lastFreeIndex >= startIdx) && (startIdx > currIdx)
            ) continue;
            array[lastFreeIndex] = array[currIdx];
            array[currIdx] = null;
            lastFreeIndex = currIdx;
        }
    }

    private int getStartIndex(K key) {
        return Math.abs(key.hashCode() % this.capacity);
    }

    private int getNextIdx(int idx) {
        return (idx + 1) % capacity;
    }

    private void resize() {
        Entry<K, V>[] oldArray = array;
        capacity *= 2;
        array = createArray(capacity);
        Arrays.stream(oldArray)
                .filter(Objects::nonNull)
                .forEach(entry -> putEntry(entry.getKey(), entry.getValue()));
    }

}
