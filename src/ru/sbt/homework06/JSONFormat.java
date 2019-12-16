package ru.sbt.homework06;

import java.util.List;
import java.util.Map;

public class JSONFormat implements Format {
    final int indent;
    private int currentIndent = 0;
    private final StringBuilder builder = new StringBuilder();

    public JSONFormat(int indent) {
        this.indent = indent;
    }

    String getIndent(int length) {
        return new String(new char[length]).replace("\0", " ");
    }

    @Override
    public String getResult() {
        return builder.toString();
    }

    @Override
    public String writeMap(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        currentIndent++;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = "\"" + entry.getKey() + "\": ";
            String value = addIndent(entry.getValue(), getIndent(indent * currentIndent + key.length()));
            builder.append(getIndent(currentIndent*indent))
                    .append(key)
                    .append(value).append(",\n");
        }
        currentIndent--;
        builder.append("}");
        return builder.toString();
    }

    @Override
    public String writeArray(List<String> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        currentIndent++;
        for (String s : list) {
            builder.append(addIndent(s, getIndent(indent * currentIndent))).append(",\n");
        }
        currentIndent--;
        builder.append("]");
        return builder.toString();
    }

    private String addIndent(String raw, String indent) {
        String[] splitted = raw.split("\n");
        if (splitted.length == 1) return raw;
        StringBuilder builder = new StringBuilder(splitted[0]);
        for (int i = 1; i < splitted.length - 1; i++) {
            builder.append("\n").append(indent).append(splitted[i]);
        }
        builder.append("\n").append(indent).append(splitted[splitted.length - 1]);
        return builder.toString();
    }


}
