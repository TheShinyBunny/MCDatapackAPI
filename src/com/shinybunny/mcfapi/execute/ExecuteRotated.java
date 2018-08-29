package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.entity.Rotation;
import com.shinybunny.mcfapi.entity.SingleEntity;

public class ExecuteRotated extends ExecutePart {

    private Rotation rotation;
    private SingleEntity as;

    public ExecuteRotated(Rotation rotation) {
        this.rotation = rotation;
    }

    public ExecuteRotated(SingleEntity as) {
        this.as = as;
    }

    @Override
    public String getName() {
        return "rotated";
    }

    @Override
    public String buildSyntax() {
        return as == null ? rotation.toString() : as.toString();
    }
}
