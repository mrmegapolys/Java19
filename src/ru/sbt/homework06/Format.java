package ru.sbt.homework06;

import java.util.List;
import java.util.Map;

public interface Format {
    String getResult();

    String writeMap(Map<String, String> map);

    String writeArray(List<String> list);
}
