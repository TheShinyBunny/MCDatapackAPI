package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.scoreboard.Criteria;
import com.shinybunny.mcfapi.scoreboard.DummyEntry;
import com.shinybunny.mcfapi.scoreboard.Objective;
import com.shinybunny.mcfapi.scoreboard.Operator;
import com.shinybunny.mcfapi.server.GameMode;
import com.shinybunny.mcfapi.server.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class MyFunctions extends FunctionContainer {

    public MyFunctions(Namespace ns) {
        super(ns);
    }

    @MCFunction("init")
    public void init() {

        new Objective("cmdRaycast",Criteria.DUMMY,TextComponent.of(Segment.text("CommandBlock Raycast")));

        new Objective("randomizer",Criteria.DUMMY);
        new Objective("maxRandom",Criteria.DUMMY);

    }

    @MCFunction("loop")
    public void loop() {

        execute().as(selectPlayers().find()).atSelf().positioned(Position.relative(0,1.67,0)).run(function("command_block/check"));

        execute().as(selectPlayers().find()).atSelf().run(function("worldgen/scan"));

    }

    @MCFunction("random")
    public void random() {

        Objective maxRandom = Objective.of("maxRandom");
        Objective randomizer = Objective.of("randomizer");
        DummyEntry random1 = new DummyEntry("random1");
        DummyEntry random2 = new DummyEntry("random2");
        DummyEntry max = new DummyEntry("max");
        DummyEntry result = new DummyEntry("result");

        randomizer.setScore(result,0);

        random1.multiplyScore(maxRandom,random2,maxRandom);
        random1.moduloScore(maxRandom,random2,maxRandom);
        result.setScore(randomizer,random2,maxRandom);
        result.moduloScore(randomizer,random1,maxRandom);
        result.moduloScore(randomizer,max,maxRandom);

    }

    @MCFunction("random_100")
    public void random100() {

        new DummyEntry("max").setScore(Objective.of("maxRandom"),100);
        runFunction("random");

    }

    @MCFFolder("command_block")
    public static class CommandBlocks extends FunctionContainer {

        public CommandBlocks(Namespace ns) {
            super(ns);
        }

        @MCFunction("check")
        public void check() {

            NBT selectedMain = new NBT()
                    .set("SelectedItem",new NBT()
                            .set("id","minecraft:command_block")
                    );
            List<NBT> list = new ArrayList<>();
            list.add(new NBT().set("id","minecraft:command_block")
                    .set("Slot",(byte)-106));

            NBT selectedOff = new NBT()
                    .set("Inventory", list);

            execute().iF().entity(
                    self().gamemode(GameMode.SURVIVAL)
                            .nbt(selectedMain)
                            .find()).run(()->{
                                getTags().remove("notEditingCMD");
                                getTags().add("editingCMD");
                            });
            execute().iF().entity(
                    self().gamemode(GameMode.SURVIVAL)
                            .nbt(selectedOff)
                            .find()).run(()->{
                                getTags().remove("notEditingCMD");
                                getTags().add("editingCMD");
                            });

            execute().iF().entity(self().tag("lookingAtCMD").find()).run(()->{
                setGameMode(GameMode.CREATIVE);
            });

            execute().iF().entity(self().tag("editingCMD").notTag("lookingAtCMD").gamemode(GameMode.CREATIVE).find()).unless().entity(self().nbt(selectedMain).find()).unless().entity(self().nbt(selectedOff).find()).run(()->{
                getTags().add("notEditingCMD");
                getTags().remove("editingCMD");
            });

            execute().iF().entity(self().tag("editingCMD").find()).run(()->setGameMode(GameMode.CREATIVE));
            execute().iF().entity(self().tag("notEditingCMD").find()).run(()->{
                setGameMode(GameMode.SURVIVAL);
                getTags().remove("notEditingCMD");
            });

            getTags().remove("wasLookingCMD");
            execute().iF().entity(self().tag("lookingAtCMD").find()).run(()->getTags().add("wasLookingCMD"));
            getTags().remove("lookingAtCMD");
            execute().unless().entity(self().tag("editingCMD").find()).run(function("command_block/raycast"));
            execute().iF().entity(self().tag("wasLookingCMD").notTag("lookingAtCMD").find()).run(()->setGameMode(GameMode.SURVIVAL));
        }

        @MCFunction("raycast")
        public void rayCast() {

            Objective rayCast = Objective.of("cmdRaycast");
            addScore(rayCast,1);

            execute().iF().block(Position.here(),Block.tagged(ResourceLocation.of("main:command_blocks"))).run(()->{
                setScore(rayCast,0);
                getTags().add("lookingAtCMD");
            });

            execute().positioned(Position.rotated(0,0,0.1)).iF().scoreMatches(this,rayCast,Range.between(1,50)).run(function("command_block/raycast"));

            setScore(rayCast,0);

        }

    }

    @MCFFolder("worldgen")
    public static class WorldGen extends FunctionContainer {

        public WorldGen(Namespace ns) {
            super(ns);
        }

        @MCFunction("scan")
        public void scan() {
            execute().positioned(new Position(Coordinate.relative(0),Coordinate.absolute(0),Coordinate.relative(0))).iF().block(Position.here(),Block.of("bedrock")).run(function("worldgen/generate"));

        }

        @MCFunction("generate")
        public void generate() {

            selectPlayers().find().actionbar(
                    TextComponent.of(
                            Segment.text("!").color(ChatColor.RED).bold(),
                            Segment.text(" New chunks are being generated, might cause lags ").color(ChatColor.RESET),
                            Segment.text("!").color(ChatColor.RED).bold()
                    )
            );

            fill(Position.relative(16,0,16),Position.relative(-16,0,-16),Block.of("obsidian")).replace();

            kill();

        }

        @MCFunction("spread")
        public void spread() {

            execute().positioned(Position.relative(-32,0,0))
                    .iF().block(Position.here(),Block.of("bedrock"))
                    .iF().entity(selectPlayers().distance(Range.max(150)).find())
                    .unless().entity(selectPlayers().distance(Range.max(70)).find())
                    .run(function("worldgen/generate"));
            execute().positioned(Position.relative(32,0,0))
                    .iF().block(Position.here(),Block.of("bedrock"))
                    .iF().entity(selectPlayers().distance(Range.max(150)).find())
                    .unless().entity(selectPlayers().distance(Range.max(70)).find())
                    .run(function("worldgen/generate"));
            execute().positioned(Position.relative(0,0,32))
                    .iF().block(Position.here(),Block.of("bedrock"))
                    .iF().entity(selectPlayers().distance(Range.max(180)).find())
                    .unless().entity(selectPlayers().distance(Range.max(70)).find())
                    .run(function("worldgen/generate"));
            execute().positioned(Position.relative(0,0,-32))
                    .iF().block(Position.here(),Block.of("bedrock"))
                    .iF().entity(selectPlayers().distance(Range.max(150)).find())
                    .unless().entity(selectPlayers().distance(Range.max(70)).find())
                    .run(function("worldgen/generate"));


        }

    }

}