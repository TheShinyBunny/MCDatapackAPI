package com.shinybunny.mcfapi.execute;

public class ExecuteAlign extends ExecutePart {

    private final boolean x;
    private final boolean y;
    private final boolean z;

    public ExecuteAlign(boolean x, boolean y, boolean z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String getName() {
        return "align";
    }

    @Override
    public String buildSyntax() {
        return (x ? "x" : "") + (y ? "y" : "") + (z ? "z" : "");
    }
}
