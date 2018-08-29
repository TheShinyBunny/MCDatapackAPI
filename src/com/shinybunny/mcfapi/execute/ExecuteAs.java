package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.entity.EntityList;

public class ExecuteAs extends ExecutePart {

    private final EntityList entities;

    public ExecuteAs(EntityList entities) {
        this.entities = entities;
    }

    @Override
    public String getName() {
        return "as";
    }

    @Override
    public String buildSyntax() {
        return entities.toString();
    }
}
