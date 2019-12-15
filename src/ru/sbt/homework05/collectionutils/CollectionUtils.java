package ru.sbt.homework05.collectionutils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? super T> source, T object) {
        int counter = 0;
        for (Object elem : source) {
            if (elem.equals(object)) return counter;
            counter++;
        }
        return -1;
    }

    public static <T> List<T> limit(List<T> source, int size) {
        List<T> result = CollectionUtils.newArrayList();
        int counter = 0;
        for (T elem : source) {
            if (counter >= size) break;
            result.add(elem);
            counter++;
        }
        return result;
    }

    public static <T> void add(List<? super T> source, T elem) {
        source.add(elem);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        c2.forEach(removeFrom::remove);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        return c2.stream().anyMatch(c1::contains);
    }

    public static <T extends Comparable<? super T>> List<T> range(List<? extends T> list, T min, T max) {
        return range(list, elem -> elem.compareTo(min) > 0 && elem.compareTo(max) < 0);
    }

    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        return range(list, elem -> comparator.compare(elem, min) > 0 && comparator.compare(elem, max) < 0);
    }

    private static <T> List<T> range(List<? extends T> list, Predicate<T> predicate) {
        List<T> result = CollectionUtils.newArrayList();
        list.stream()
                .filter(predicate)
                .forEach(result::add);
        return result;
    }
}