package com.shinybunny.mcfapi.tags;

import com.shinybunny.mcfapi.Item;
import com.shinybunny.mcfapi.Namespace;

public class ItemTag extends Tag<Item> {
    public ItemTag(Namespace ns, String name) {
        super(ns, name);
    }

    @Override
    public ItemTag add(Item value) {
        return (ItemTag) super.add(value);
    }
}
