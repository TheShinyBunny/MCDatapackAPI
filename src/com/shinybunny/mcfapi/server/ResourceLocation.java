package com.shinybunny.mcfapi.server;

import com.shinybunny.mcfapi.Namespace;

public class ResourceLocation {

    private String namespace;
    private String path;

    public ResourceLocation(String path) {
        this("minecraft",path);
    }

    public ResourceLocation(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public ResourceLocation(Namespace ns, String path) {
        this(ns.getName(),path);
    }

    public static ResourceLocation of(String id) {
        if (id.startsWith("#")) {
            id = id.substring(1);
        }
        if (id.contains(":")) {
            int i = id.indexOf(":");
            if (i == id.lastIndexOf(":") && i < id.length()-1) {
                return new ResourceLocation(id.substring(0,i),id.substring(i+1));
            }
        }
        return new ResourceLocation(id);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return namespace.toLowerCase() + ":" + path.toLowerCase();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ResourceLocation && ((ResourceLocation) obj).namespace.equals(namespace) && ((ResourceLocation) obj).path.equalsIgnoreCase(path);
    }
}
