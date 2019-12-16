package ru.sbt.homework06;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Serializer {
    private static final Set<Class<?>> wrapperTypes = getWrapperTypes();
    private final Format format;

    public Serializer(Format format) {
        this.format = format;
    }

    public String serialize(Object o) {
        return serializeObject(o);
    }

    private String serializeObject(Object o) {
        if (o == null) return format.writeNull();
        else if (isWrapperType(o)) return serializeWrapper(o);
        else if (isArray(o)) return serializeCollection((asList((Object[]) o)));
        else if (isCollection(o) || isArray(o)) return serializeCollection((Collection<?>) o);
        else if (isMap(o)) return serializeMap((Map<?, ?>) o);
        else return serializeFields(o);
    }

    private static boolean isWrapperType(Object o) {
        return wrapperTypes.contains(o.getClass());
    }

    private boolean isArray(Object o) {
        return o.getClass().isArray();
    }

    private boolean isCollection(Object o) {
        return o instanceof Collection;
    }

    private boolean isMap(Object o) {
        return o instanceof Map;
    }

    private String serializeWrapper(Object o) {
        if (o instanceof Number || o instanceof Boolean) return format.writeNumberOrBool(o);
        else return format.writeString(o);
    }

    private String serializeCollection(Collection<?> collection) {
        List<String> result = collection.stream().map(this::serializeObject).collect(toList());
        return format.writeCollection(result);
    }

    private String serializeMap(Map<?, ?> map) {
        Map<String, String> result = new HashMap<>();
        map.forEach((key, value) -> result.put(key.toString(), serializeObject(value)));
        return format.writeMap(result);
    }

    private String serializeFields(Object o) {
        return serializeMap(getDeclaredFields(o));
    }

    private Map<String, Object> getDeclaredFields(Object o) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = o.getClass();

        try {
            while (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(o));
                }
                clazz = clazz.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> wrapperTypes = new HashSet<>();
        wrapperTypes.add(Boolean.class);
        wrapperTypes.add(Character.class);
        wrapperTypes.add(Byte.class);
        wrapperTypes.add(Short.class);
        wrapperTypes.add(Integer.class);
        wrapperTypes.add(Long.class);
        wrapperTypes.add(Float.class);
        wrapperTypes.add(Double.class);
        wrapperTypes.add(Void.class);
        wrapperTypes.add(String.class);
        return wrapperTypes;
    }
}
