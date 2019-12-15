package ru.sbt.homework05.countmap;

import java.util.Map;

public interface CountMap<K> {
    void add(K key);

    int getCount(K key);

    int remove(K key);

    int size();

    void addAll(CountMap<? extends K> source);

    Map<K, Integer> toMap();

    void toMap(Map<? super K, ? super Integer> destination);
}
