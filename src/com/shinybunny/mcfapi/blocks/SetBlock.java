package com.shinybunny.mcfapi.blocks;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.DatapackManager;

public class SetBlock {

    private Position pos;
    private Block block;

    public SetBlock(Position pos, Block block) {
        this.pos = pos;
        this.block = block;
    }

    public void replace() {
        DatapackManager.writeCommand("setblok " + pos + " " + block + " replace");
    }

    public void destroy() {
        DatapackManager.writeCommand("setblok " + pos + " " + block + " destroy");
    }

    public void keep() {
        DatapackManager.writeCommand("setblok " + pos + " " + block + " keep");
    }
}
