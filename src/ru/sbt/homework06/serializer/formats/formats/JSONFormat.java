package ru.sbt.homework06.serializer.formats.formats;

import ru.sbt.homework06.serializer.formats.AbstractFormat;

import java.util.List;
import java.util.Map;

public class JSONFormat extends AbstractFormat {
    public JSONFormat(int indent) {
        super(indent);
    }

    @Override
    protected String onMapStart() {
        return "{\n";
    }

    @Override
    protected void processMapContents(Map<String, String> map, StringBuilder builder) {
        map.forEach((key, value) -> builder.append(getIndent())
                .append("\"").append(key).append("\": ")
                .append(addIndent(value, getIndent()))
                .append(",\n"));
        builder.deleteCharAt(builder.length() - 2);
    }

    @Override
    protected String onMapFinish() {
        return "}";
    }

    @Override
    protected String onCollectionStart() {
        return "[\n";
    }

    @Override
    protected void processCollectionContents(List<String> list, StringBuilder builder) {
        list.forEach(s -> builder.append(getIndent())
                .append(addIndent(s, getIndent())).append(",\n"));
        builder.deleteCharAt(builder.length() - 2);
    }

    @Override
    protected String onCollectionFinish() {
        return "]";
    }

    @Override
    public String writeNull() {
        return "null";
    }

    @Override
    public String writeNumberOrBool(Object o) {
        return o.toString();
    }

    @Override
    public String writeAsString(Object o) {
        return "\"" + o.toString() + "\"";
    }

    private String addIndent(String raw, String indent) {
        return String.join("\n" + indent, raw.split("\n"));
    }
}
