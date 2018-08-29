package com.shinybunny.mcfapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NBT {

    private Map<String,Object> map;

    public NBT() {
        this.map = new HashMap<>();
    }

    public  <T> NBT set(String key, T value) {
        this.map.put(key,value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("{");
        for (Map.Entry<String,Object> e : map.entrySet()) {
            if (e.getValue() != null) {
                b.append(e.getKey() + ":" + stringify(e.getValue()));
                b.append(",");
            }
        }
        if (!map.isEmpty()) {
            b.deleteCharAt(b.length()-1);
        }
        return b.append("}").toString();
    }

    private Object stringify(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        if (value instanceof Boolean) {
            return stringify((boolean)value ? (byte)1 : (byte)0);
        }
        if (value instanceof Byte) {
            return value + "b";
        }
        if (value instanceof Short) {
            return value + "s";
        }
        if (value instanceof Long) {
            return value + "L";
        }
        if (value instanceof List) {
            StringBuilder b = new StringBuilder("[");
            for (Object o : (List)value) {
                b.append(stringify(o));
                b.append(",");
            }
            b.deleteCharAt(b.length()-1);
            return b.append("]").toString();
        }
        return value.toString();
    }
}
