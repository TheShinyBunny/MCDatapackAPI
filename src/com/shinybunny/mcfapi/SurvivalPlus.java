package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.server.*;

public class SurvivalPlus extends Datapack {

    public SurvivalPlus() {
        super("survival_plus");

        Namespace main = this.createNamespace("main");
        main.createFunctions(new MyFunctions(main));

        main.createLoadTag("init");
        main.createTickTag("loop");
        main.createBlockTag("command_blocks",
                Block.of("minecraft:command_block"),
                Block.of("minecraft:repeating_command_block"),
                Block.of("minecraft:chain_command_block")
        );
    }

    public static void main(String[] args) {
        new SurvivalPlus();
    }

    @Override
    public String getDescription() {
        return "The offical datapack of Survival+ V2!";
    }

    @Override
    public String getPath() {
        return "C:/Users/yaniv/AppData/Roaming/.minecraft/saves/1_13 tests/datapacks";
    }
}
