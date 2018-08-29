package com.shinybunny.mcfapi.blocks;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.DatapackManager;

public class Clone {
    private final Position begin;
    private final Position end;
    private final Position destination;
    private Mode mode;

    public Clone(Position begin, Position end, Position destination) {
        this.begin = begin;
        this.end = end;
        this.destination = destination;
        this.mode = Mode.NORMAL;
    }

    public Clone move() {
        mode = Mode.MOVE;
        return this;
    }

    public Clone force() {
        mode = Mode.FORCE;
        return this;
    }

    public void replace() {
        DatapackManager.writeCommand(build("replace"));
    }

    private String build(String maskMode) {
        String s = "clone " + begin + " " + end + " " + destination + " " + maskMode;
        if (mode != null) {
            s += " " + mode.toString().toLowerCase();
        }
        return s;
    }

    public void masked() {
        DatapackManager.writeCommand(build("masked"));
    }

    public void filtered(Block block) {
        DatapackManager.writeCommand(build("filtered") + " " + block);
    }

    public enum Mode {
        NORMAL, MOVE, FORCE
    }
}
