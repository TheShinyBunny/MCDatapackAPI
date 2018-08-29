package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.server.ResourceLocation;
import com.shinybunny.mcfapi.tags.Taggable;

public class MCFunctionHolder implements Taggable {

    private FunctionContainer container;
    private ResourceLocation id;

    public MCFunctionHolder(FunctionContainer container, String name) {
        this.container = container;
        this.id = new ResourceLocation(container.getNamespace().getName(),name);
    }

    public FunctionContainer getContainer() {
        return container;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
