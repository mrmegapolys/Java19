import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;

public class Serializer {

    private static final Set<Class<?>> wrapperTypes = getWrapperTypes();

    public static String serialize(Object o, String encoding) throws UnsupportedEncodingException {

        Map<String, Object> fieldValues = getFieldValues(o);

        switch (encoding) {
            case ("json"):
                return toJson(fieldValues);
            default:
                throw new UnsupportedEncodingException();
        }
    }

    private static String toJson(Map<String, Object> fieldValues) {
        JSONObject json = new JSONObject(fieldValues);
        return json.toString();
    }

    private static Map<String, Object> getFieldValues(Object object) {
        HashMap<String, Object> fieldValues = new HashMap<>();
        List<Field> fields = getFields(object);

        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException ignored) {
            } //this is never going to happen because of setAccessible

            Object value;
            if (fieldValue == null || isWrapperType(fieldValue) || isArray(fieldValue) || isCollectionOrMap(fieldValue))  {
                value = fieldValue;
            } else {
                value = getFieldValues(fieldValue);
            }
            fieldValues.put(field.getName(), value);
        }
        return fieldValues;
    }

    private static List<Field> getFields(Object o) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = o.getClass();
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private static boolean isWrapperType(Object o) {
        return wrapperTypes.contains(o.getClass());
    }

    private static boolean isCollectionOrMap(Object o) {
        return o instanceof Map || o instanceof Collection;
    }

    private static boolean isArray(Object o) {
        return o.getClass().isArray();
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
