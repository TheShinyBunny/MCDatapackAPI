package com.shinybunny.mcfapi.server;

import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.Namespace;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Datapack {

    private final File mainFolder;
    private final File data;
    private String name;
    private File packMCMeta;
    private Namespace defaultNamespace;
    private World world;

    public Datapack(String name) {
        this.name = name;
        this.world = new WorldImpl();
        DatapackManager.setDatapack(this);
        mainFolder = new File(getPath() + "/" + name);
        if (!mainFolder.exists()) {
            mainFolder.mkdirs();
        }
        data = new File(mainFolder,"data");
        if (!data.exists()) {
            data.mkdirs();
        }
        packMCMeta = new File(mainFolder,"pack.mcmeta");
        if (!packMCMeta.exists()) {
            try {
                packMCMeta.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter w = new BufferedWriter(new FileWriter(packMCMeta))) {
            w.write("{\n" +
                    "\t\"pack\":{\n" +
                    "\t\t\"pack_format\":1,\n" +
                    "\t\t\"description\":\"" + getDescription() + "\"\n" +
                    "\t}\n" +
                    "}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getMainFolder() {
        return mainFolder;
    }

    public File getDataFolder() {
        return data;
    }

    public String getName() {
        return name;
    }

    public abstract String getDescription();

    public abstract String getPath();

    public Namespace createNamespace(String name) {
        return new Namespace(this,name);
    }

    public Namespace getDefaultNamespace() {
        if (defaultNamespace == null) {
            defaultNamespace = new Namespace(this,"minecraft");
        }
        return defaultNamespace;
    }

    public World getWorld() {
        return world;
    }
}
