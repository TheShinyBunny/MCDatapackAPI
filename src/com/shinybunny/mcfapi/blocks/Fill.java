package com.shinybunny.mcfapi.blocks;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.DatapackManager;

public class Fill {

    private final Block block;
    private final Position begin;
    private final Position end;

    public Fill(Position begin, Position end, Block with) {
        this.begin = begin;
        this.end = end;
        this.block = with;
    }

    public void replace(Block other) {
        DatapackManager.writeCommand("fill " + begin + " " + end + " " + block + " replace " + other);
    }

    public void replace() {
        DatapackManager.writeCommand("fill " + begin + " " + end + " " + block + " replace");
    }

    public void destroy() {
        DatapackManager.writeCommand("fill " + begin + " " + end + " " + block + " destroy");
    }

    public void keep() {
        DatapackManager.writeCommand("fill " + begin + " " + end + " " + block + " keep");
    }

    public void hollow() {
        DatapackManager.writeCommand("fill " + begin + " " + end + " " + block + " hollow");
    }

    public void outline() {
        DatapackManager.writeCommand("fill " + begin + " " + end + " " + block + " outline");
    }

}
