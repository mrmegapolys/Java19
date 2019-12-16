package ru.sbt.homework06;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        checkSerializer();
    }

    private static void checkSerializer() {
        TestClass testClass = new TestClass(getArray(), getList(), "string", 10, getMap());

        Serializer serializer = new Serializer(new JSONFormat(4));
//        Map<String, Map<String, String>> map = new HashMap<>();
//        map.put("A", new HashMap<>());
//        map.get("A").put("A", "B");
//        map.get("A").put("C", "D");
//        Person person = new Person("Alex", 20, map);
        String serialized = serializer.serialize(testClass);
        System.out.println(serialized);

    }

    private static Map<Person, Person> getMap() {
        Map<Person, Person> map = new HashMap<>();
        map.put(
                new Person("key1", 1),
                new Person("value1", 2)
        );
        map.put(
                new Person("key2", 3),
                new Person("value2", 4)
        );
        return map;
    }

    private static List<Person> getList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("list1", 1));
        list.add(new Person("list2", 2));
        return list;
    }

    private static Person[] getArray() {
        Person[] array = new Person[2];
        array[0] = new Person("array1", 1);
        array[1] = new Person("array2", 2);
        return array;
    }
}
