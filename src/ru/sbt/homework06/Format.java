package ru.sbt.homework06;

import java.util.List;
import java.util.Map;

public interface Format {
    String writeMap(Map<String, String> map);

    String writeCollection(List<String> list);

    String writeNull();

    String writeNumberOrBool(Object o);

    String writeAsString(Object o);
}
