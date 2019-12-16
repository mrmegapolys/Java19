package ru.sbt.homework06.serializer.formats.formats;

import ru.sbt.homework06.serializer.formats.AbstractFormat;

import java.util.List;
import java.util.Map;

public class XMLFormat extends AbstractFormat {
    public XMLFormat(int indent) {
        super(indent);
    }

    @Override
    protected String onMapStart() {
        return "\n";
    }

    @Override
    protected void processMapContents(Map<String, String> map, StringBuilder builder) {
        map.forEach((key, value) ->
                builder.append(getOpeningTag(key))
                        .append(addIndent(value, getIndent()))
                        .append(getClosingTag(key)).append("\n"));
    }

    @Override
    protected String onMapFinish() {
        return "";
    }

    @Override
    protected String onCollectionStart() {
        return "\n";
    }

    @Override
    protected void processCollectionContents(List<String> list, StringBuilder builder) {
        list.forEach(s -> builder.append(getOpeningTag("element"))
                .append(addIndent(s, getIndent()))
                .append(getClosingTag("element"))
                .append("\n"));
    }

    @Override
    protected String onCollectionFinish() {
        return "";
    }

    @Override
    public String writeNull() {
        return "";
    }

    @Override
    public String writeNumberOrBool(Object o) {
        return writeAsString(o);
    }

    @Override
    public String writeAsString(Object o) {
        return o.toString();
    }

    private String addIndent(String raw, String indent) {
        if (!raw.contains("<")) return raw;
        StringBuilder builder = new StringBuilder();
        for (String s : raw.split("\n")) {
            builder.append(getIndent()).append(s).append("\n");
        }
        return builder.toString();
    }

    private String getOpeningTag(String s) {
        return "<" + s + ">";
    }

    private String getClosingTag(String s) {
        return "</" + s + ">";
    }
}
