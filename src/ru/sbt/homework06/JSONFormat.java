package ru.sbt.homework06;

import java.util.List;
import java.util.Map;

public class JSONFormat implements Format {
    final int indentValue;
    private int currentIndent = 0;

    public JSONFormat(int indent) {
        this.indentValue = indent;
    }

    String getIndent() {
        return new String(new char[indentValue * currentIndent]).replace("\0", " ");
    }

    @Override
    public String writeMap(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        currentIndent++;
        map.forEach((key, value) -> builder.append(getIndent())
                .append("\"").append(key).append("\": ")
                .append(addIndent(value, getIndent())).append(",\n"));
        currentIndent--;
        builder.deleteCharAt(builder.length() - 2).append("}");
        return builder.toString();
    }

    @Override
    public String writeCollection(List<String> list) {
        StringBuilder builder = new StringBuilder();
        boolean inlineable = inlineable(list);
        builder.append("[");
        if (!inlineable) builder.append("\n");
        currentIndent++;
        if (inlineable(list)) {
            builder.append(String.join(", ", list));
        } else {
            list.forEach(s -> builder.append(getIndent())
                    .append(addIndent(s, getIndent())).append(inlineable(list) ? ", " : ",\n"));
        }
        currentIndent--;
        if (!inlineable) builder.deleteCharAt(builder.length() - 2);
        builder.append("]");
        return builder.toString();
    }

    private boolean inlineable(List<String> list) {
        return list.stream().noneMatch(s -> s.contains("\n"));
    }

    private String addIndent(String raw, String indent) {
        return String.join("\n" + indent, raw.split("\n"));
    }
}
