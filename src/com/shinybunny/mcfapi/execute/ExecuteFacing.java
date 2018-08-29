package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.entity.Anchor;
import com.shinybunny.mcfapi.entity.SingleEntity;

public class ExecuteFacing extends ExecutePart {
    private Position pos;
    private SingleEntity entity;
    private Anchor anchor;

    public ExecuteFacing(Position pos) {
        this.pos = pos;
    }

    public ExecuteFacing(SingleEntity entity, Anchor anchor) {
        this.entity = entity;
        this.anchor = anchor;
    }

    @Override
    public String getName() {
        return "facing";
    }

    @Override
    public String buildSyntax() {
        return entity == null ? pos.toString() : "entity " + entity + " " + anchor.toString().toLowerCase();
    }
}
