package ru.sbt.homework05.countmap;

import java.util.HashMap;
import java.util.Map;

public class MyCountMap<K> implements CountMap<K> {
    private final HashMap<K, Integer> storage;

    public MyCountMap() {
        this.storage = new HashMap<>();
    }

    @Override
    public void add(K key) {
        Integer counter = storage.get(key);
        if (counter == null) {
            counter = 0;
        }
        counter += 1;
        storage.put(key, counter);
    }

    @Override
    public int getCount(K key) {
        Integer counter = storage.get(key);
        return (counter == null) ? 0 : counter;
    }

    @Override
    public int remove(K key) {
        Integer counter = storage.remove(key);
        return (counter == null) ? 0 : counter;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void addAll(CountMap<? extends K> source) {
        source.toMap().forEach((key, value) -> {
            for (int counter = 0; counter < value; ++counter) add(key);
        });
    }

    @Override
    public Map<K, Integer> toMap() {
        Map<K, Integer> result = new HashMap<>();
        toMap(result);
        return result;
    }

    @Override
    public void toMap(Map<? super K, ? super Integer> destination) {
        storage.forEach(destination::put);
    }

}
