package ru.sbt.classwork06;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class ReflectionUtils {
    public static List<String> getMethodName (Object o) {
        return Arrays.stream(o.getClass().getMethods())
                .map(Method::getName)
                .collect(toList());
    }

    public static Map<String, Object> getAllFieldValues(Object o) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = o.getClass();
        while (clazz != null) {
            for (Field field: clazz.getDeclaredFields()) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(o));
            }
            clazz = clazz.getSuperclass();
        }
        return map;
    }

}
