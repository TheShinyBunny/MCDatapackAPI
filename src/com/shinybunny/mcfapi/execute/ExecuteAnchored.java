package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.entity.Anchor;

public class ExecuteAnchored extends ExecutePart {
    private final Anchor anchor;

    public ExecuteAnchored(Anchor anchor) {
        this.anchor = anchor;
    }

    @Override
    public String getName() {
        return "anchored";
    }

    @Override
    public String buildSyntax() {
        return anchor.toString().toLowerCase();
    }
}
