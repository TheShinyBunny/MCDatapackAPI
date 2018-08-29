package com.shinybunny.mcfapi.server;

import com.shinybunny.mcfapi.CommandExecutor;
import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.advancements.Advancement;
import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.blocks.Clone;
import com.shinybunny.mcfapi.blocks.Fill;
import com.shinybunny.mcfapi.blocks.SetBlock;
import com.shinybunny.mcfapi.entity.EntityType;
import com.shinybunny.mcfapi.NBT;
import com.shinybunny.mcfapi.scoreboard.Objective;

public interface World extends CommandExecutor {

    void getSeed();

    Clone clone(Position begin, Position end, Position destination);

    Fill fill(Position begin, Position end, Block with);

    void setDefaultGameMode(GameMode gm);

    void setDifficulty(Difficulty difficulty);

    <T> void setGameRule(GameRule<T> gameRule, T value);

    <T> void getGameRule(GameRule<T> gameRule);

    void spawnParticle(Particle p);

    SetBlock setBlock(Position pos, Block block);

    void setWorldSpwan(Position pos);

    void summon(EntityType type);

    void summon(EntityType type, Position pos);

    void summon(EntityType type, Position pos, NBT nbt);

    Time getTime();

    void setWeather(Weather weather);

    void setWeather(Weather weather, int duration);

    WorldBorder getWorldBorder();

    Advancement getAdvancement(ResourceLocation id);

    Objective getObjective(String name);
}
