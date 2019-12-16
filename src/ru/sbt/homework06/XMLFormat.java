package ru.sbt.homework06;

import java.util.List;
import java.util.Map;

public class XMLFormat implements Format {
    final int indentValue;
    private int currentIndent = 0;

    public XMLFormat(int indentValue) {
        this.indentValue = indentValue;
    }

    private String getIndent() {
        return new String(new char[indentValue * currentIndent]).replace("\0", " ");
    }

    @Override
    public String writeMap(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        currentIndent++;
        builder.append("\n");
        map.forEach((key, value) ->
                builder.append(getOpeningTag(key))
                        .append(addIndent(value, getIndent()))
                        .append(getClosingTag(key)).append("\n"));
        currentIndent--;
        return builder.toString();
    }

    @Override
    public String writeCollection(List<String> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        currentIndent++;
        list.forEach(s -> builder.append(getElementOpeningTag())
                .append(addIndent(s, getIndent()))
                .append(getElementClosingTag())
                .append("\n"));
        currentIndent--;
        return builder.toString();
    }

    @Override
    public String writeNull() {
        return "";
    }

    @Override
    public String writeNumberOrBool(Object o) {
        return writeString(o);
    }

    @Override
    public String writeString(Object o) {
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

    private String getElementOpeningTag() {
        return getOpeningTag("element");
    }

    private String getElementClosingTag() {
        return getClosingTag("element");
    }
}
