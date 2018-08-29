package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.entity.Player;

public class FunctionContainer extends Player {

    private final Namespace namespace;

    public FunctionContainer(Namespace ns) {
        this.namespace = ns;
    }

    protected Runnable function(String name) {
        return ()->DatapackManager.writeCommand("function " + namespace.getName() + ":" + name);
    }

    protected void runFunction(String name) {
        DatapackManager.writeCommand("function " + namespace.getName() + ":" + name);
    }

    public Namespace getNamespace() {
        return namespace;
    }
}
