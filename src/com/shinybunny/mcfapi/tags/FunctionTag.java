package com.shinybunny.mcfapi.tags;

import com.shinybunny.mcfapi.MCFunctionHolder;
import com.shinybunny.mcfapi.Namespace;

public class FunctionTag extends Tag<MCFunctionHolder> {
    public FunctionTag(Namespace ns, String name) {
        super(ns, name);
        ns.getFunctionTags().add(this);
        createFile(ns.getFunctionTagsFolder());
    }

    @Override
    public FunctionTag add(MCFunctionHolder value) {
        return (FunctionTag) super.add(value);
    }
}
