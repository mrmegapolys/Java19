package ru.sbt;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class ReflectionUtils10 {
    public static List<String> collectFieldName(Object o) {
        return getPropertyNames(o, Class::getDeclaredFields, 5);
    }

    public static <T> List<String> collectMethodName(Objects o, Function<Class<?>, T[]> propertyGetter) {
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
            properties.addAll(Arrays.asList(propertyGetter.apply(clazz)));
            clazz = clazz.getSuperclass();
        }
        return properties;
    }
}
