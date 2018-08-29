package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.entity.Entity;
import com.shinybunny.mcfapi.entity.SingleEntity;

public class ExecutePositioned extends ExecutePart {
    private Position pos;
    private SingleEntity as;

    public ExecutePositioned(Position pos) {
        this.pos = pos;
    }

    public ExecutePositioned(SingleEntity as) {
        this.as = as;
    }

    @Override
    public String getName() {
        return "positioned";
    }

    @Override
    public String buildSyntax() {
        return as == null ? pos.toString() : "as " + as;
    }
}
