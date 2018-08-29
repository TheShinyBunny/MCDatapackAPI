package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.server.Datapack;
import com.shinybunny.mcfapi.server.ResourceLocation;
import com.shinybunny.mcfapi.tags.BlockTag;
import com.shinybunny.mcfapi.tags.FunctionTag;
import com.shinybunny.mcfapi.tags.ItemTag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Namespace {

    private final File folder;
    private Datapack dp;
    private String name;
    private FunctionContainer functions;
    private List<BlockTag> blockTags;
    private File tagsFolder;
    private File blockTagsFolder;
    private List<ItemTag> itemTags;
    private File itemTagsFolder;
    private List<FunctionTag> functionTags;
    private File functionTagsFolder;
    private FunctionWriter writer;
    private File functionsFolder;

    public Namespace(Datapack dp, String name) {
        this.dp = dp;
        this.name = name;
        this.blockTags = new ArrayList<>();
        this.itemTags = new ArrayList<>();
        this.functionTags = new ArrayList<>();
        this.writer = new FunctionWriter();
        folder = new File(dp.getDataFolder(),name);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public String getName() {
        return name;
    }

    public void createFunctions(FunctionContainer container) {
        DatapackManager.setFunctionWriter(writer);
        functions = container;
        writer.createFunctions(container);
    }

    public void createLoadTag(String function) {
        dp.getDefaultNamespace().createFunctionTag("load",this,function);
    }

    public void createTickTag(String function) {
        dp.getDefaultNamespace().createFunctionTag("tick",this,function);
    }

    public void createFunctionTag(String name, Namespace funcNamespace, String... functions) {
        FunctionTag t = new FunctionTag(this,name);
        for (String f : functions) {
            t.add(new MCFunctionHolder(funcNamespace.functions,f));
        }
    }

    public void createBlockTag(String name, Block... blocks) {
        BlockTag t = new BlockTag(this,name);
        for (Block b : blocks) {
            t.add(b);
        }
    }

    public List<FunctionTag> getFunctionTags() {
        return functionTags;
    }

    public List<BlockTag> getBlockTags() {
        return blockTags;
    }

    public List<ItemTag> getItemTags() {
        return itemTags;
    }

    public File getFolder() {
        return folder;
    }

    public File getTagsFolder() {
        return tagsFolder;
    }

    public void createTagsFolder() {
        if (tagsFolder != null) {
            return;
        }
        tagsFolder = new File(folder,"tags");
        if (!tagsFolder.exists()) {
            tagsFolder.mkdirs();
        }
    }

    public File getFunctionTagsFolder() {
        if (functionTagsFolder == null) {
            functionTagsFolder = new File(tagsFolder,"functions");
        }
        if (!functionTagsFolder.exists()) {
            functionTagsFolder.mkdirs();
        }
        return functionTagsFolder;
    }

    public File getFunctionsFolder() {
        if (functionsFolder == null) {
            functionsFolder = new File(folder,"functions");
        }
        if (!functionsFolder.exists()) {
            functionsFolder.mkdirs();
        }
        return functionsFolder;
    }

    public File getBlockTagsFolder() {
        if (blockTagsFolder == null) {
            blockTagsFolder = new File(tagsFolder,"blocks");
        }
        if (!blockTagsFolder.exists()) {
            blockTagsFolder.mkdirs();
        }
        return blockTagsFolder;
    }
}
