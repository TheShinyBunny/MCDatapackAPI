package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.server.ResourceLocation;
import com.shinybunny.mcfapi.tags.Taggable;

public class Item implements BlockItem, Taggable {

    private ResourceLocation id;
    private NBT tag;

    private Item(String id, NBT tag) {
        this.id = ResourceLocation.of(id);
        this.tag = tag;
    }

    public static Item of(String id) {
        return new Item(id,null);
    }

    public static Item of(String id, NBT tag) {
        return new Item(id,tag);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public NBT getTag() {
        return tag;
    }

    public NBT toNBT() {
        return null;
    }

    @Override
    public String toString() {
        return id + (tag == null ? "" : tag.toString());
    }
}
