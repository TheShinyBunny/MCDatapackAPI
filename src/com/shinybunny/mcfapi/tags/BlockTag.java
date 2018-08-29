package com.shinybunny.mcfapi.tags;

import com.shinybunny.mcfapi.Namespace;
import com.shinybunny.mcfapi.blocks.Block;

public class BlockTag extends Tag<Block> {
    public BlockTag(Namespace ns, String name) {
        super(ns, name);
        ns.getBlockTags().add(this);
        createFile(ns.getBlockTagsFolder());
    }

    @Override
    public BlockTag add(Block value) {
        return (BlockTag) super.add(value);
    }
}
