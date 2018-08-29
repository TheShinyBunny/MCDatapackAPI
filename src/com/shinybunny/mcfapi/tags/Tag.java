package com.shinybunny.mcfapi.tags;

import com.shinybunny.mcfapi.Namespace;
import com.shinybunny.mcfapi.server.ResourceLocation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tag<T extends Taggable> implements Taggable {

    protected final Namespace namespace;
    private ResourceLocation id;
    private List<T> values;
    protected File file;

    public Tag(Namespace ns, String name) {
        this.namespace = ns;
        this.id = new ResourceLocation(ns,name);
        values = new ArrayList<>();
        namespace.createTagsFolder();
    }

    public Tag<T> add(T value) {
        values.add(value);
        StringBuilder b = new StringBuilder("{\n");
        b.append("\t\"values\":[\n");
        for (T v : values) {
            b.append("\t\t\"" + v + "\"");
            b.append(",\n");
        }
        b.deleteCharAt(b.length()-2);
        b.append("\t]\n}");
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))){
            w.write(b.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public List<T> getValues() {
        return values;
    }

    protected void createFile(File folder) {
        file = new File(folder,id.getPath() + ".json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public String toString() {
        return "#" + id;
    }
}
