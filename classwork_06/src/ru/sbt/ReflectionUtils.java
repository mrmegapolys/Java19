package ru.sbt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionUtils {
    public static List<String> getMethodName (Object o) {
        Class<?> clazz = o.getClass();
        List<String> names = new ArrayList<>();
        for (Method method: clazz.getMethods()) {
            names.add(method.getName());
        }
        return names;
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
