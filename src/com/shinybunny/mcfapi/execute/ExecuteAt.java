package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.entity.EntityList;

public class ExecuteAt extends ExecutePart {

    private final EntityList entities;

    public ExecuteAt(EntityList entities) {
        this.entities = entities;
    }


    @Override
    public String getName() {
        return "at";
    }

    @Override
    public String buildSyntax() {
        return entities.toString();
    }
}
