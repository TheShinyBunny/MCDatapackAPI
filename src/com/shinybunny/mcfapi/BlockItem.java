package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.server.ResourceLocation;

public interface BlockItem {

    ResourceLocation getId();

    NBT getTag();

}
