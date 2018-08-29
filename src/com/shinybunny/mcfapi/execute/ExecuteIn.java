package com.shinybunny.mcfapi.execute;

public class ExecuteIn extends ExecutePart {
    private final Dimension dim;

    public ExecuteIn(Dimension dim) {
        this.dim = dim;
    }

    @Override
    public String getName() {
        return "in";
    }

    @Override
    public String buildSyntax() {
        return dim.toString().toLowerCase();
    }
}
