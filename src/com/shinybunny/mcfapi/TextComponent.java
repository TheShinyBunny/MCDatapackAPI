package com.shinybunny.mcfapi;

import java.util.Arrays;
import java.util.List;

public class TextComponent {

    private String plain;
    private List<Segment> segments;

    public TextComponent(Segment... segments) {
        this.segments = Arrays.asList(segments);
    }

    public TextComponent(String text) {
        this.plain = text;
    }

    public static TextComponent of(Segment... segments) {
        return new TextComponent(segments);
    }

    public static TextComponent plain(String text) {
        return new TextComponent(text);
    }

    @Override
    public String toString() {
        if (plain == null) {
            StringBuilder b = new StringBuilder(segments.size() > 1 ? "[" : "");
            for (Segment s : segments) {
                b.append(s.toString());
                b.append(",");
            }
            b.deleteCharAt(b.length() - 1);
            b.append(segments.size() > 1 ? "]" : "");
            return b.toString();
        }
        return "\"" + plain + "\"";
    }
}
