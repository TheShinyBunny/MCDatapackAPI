package com.shinybunny.mcfapi.blocks;

import com.shinybunny.mcfapi.BlockItem;
import com.shinybunny.mcfapi.NBT;
import com.shinybunny.mcfapi.server.ResourceLocation;
import com.shinybunny.mcfapi.tags.Taggable;

import java.util.Map;

public class Block implements BlockItem, Taggable {

    private String id;
    private Map<String,Object> state;
    private NBT tag;

    private Block(String id, Map<String, Object> state, NBT tag) {
        this.id = id;
        this.state = state;
        this.tag = tag;
    }

    public static Block of(String id) {
        return new Block(id,null,null);
    }

    public static Block of(ResourceLocation id) {
        return new Block(id.toString(),null,null);
    }

    public static Block of(ResourceLocation id, Map<String,Object> state) {
        return new Block(id.toString(),state,null);
    }

    public static Block of(ResourceLocation id, Map<String,Object> state, NBT tag) {
        return new Block(id.toString(),state,tag);
    }

    public static Block tagged(ResourceLocation tag) {
        return new Block("#" + tag,null,null);
    }


    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(id);
        if (state != null && !state.isEmpty()) {
            b.append("[");
            for (Map.Entry<String,Object> e : state.entrySet()) {
                if (b.charAt(b.length()-1) != '[') {
                    b.append(",");
                }
                b.append(e.getKey() + "=" + e.getValue());
            }
            b.append("]");
        }
        if (tag != null) {
            b.append(tag);
        }
        return b.toString();
    }

    @Override
    public ResourceLocation getId() {
        return ResourceLocation.of(id);
    }

    @Override
    public NBT getTag() {
        return tag;
    }

}
