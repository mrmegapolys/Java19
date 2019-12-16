package ru.sbt.classwork10;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class ReflectionUtils {
    public static List<String> collectFieldName(Object o) {
        return getPropertyNames(o, Class::getDeclaredFields, 5);
    }

    public static List<String> collectMethodName(Object o) {
        return getPropertyNames(o, Class::getDeclaredMethods, 4);
    }

    private static List<String> getPropertyNames(Object o,
                                                 Function<Class<?>, Member[]> propertyGetter,
                                                 int minLength) {
        return getProperties(o.getClass(), propertyGetter).stream()
                .map(Member::getName)
                .filter(name -> name.length() > minLength)
                .collect(toList());
    }

    private static List<Member> getProperties(Class<?> clazz, Function<Class<?>, Member[]> propertyGetter) {
        List<Member> properties = new ArrayList<>();
        while (clazz != null) {
            properties.addAll(asList(propertyGetter.apply(clazz)));
            clazz = clazz.getSuperclass();
        }
        return properties;
    }
}
