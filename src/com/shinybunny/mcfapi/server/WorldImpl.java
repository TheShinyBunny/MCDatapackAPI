package com.shinybunny.mcfapi.server;

import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.advancements.Advancement;
import com.shinybunny.mcfapi.bossbar.BossBar;
import com.shinybunny.mcfapi.NBT;
import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.blocks.Clone;
import com.shinybunny.mcfapi.blocks.Fill;
import com.shinybunny.mcfapi.blocks.SetBlock;
import com.shinybunny.mcfapi.entity.EntityType;
import com.shinybunny.mcfapi.scoreboard.Objective;
import com.shinybunny.mcfapi.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class WorldImpl implements World {

    private final Time time;
    private WorldBorder worldBorder;
    private List<Team> teams;
    private List<Objective> objectives;
    private List<BossBar> bossBars;
    private List<Advancement> advancements;

    public WorldImpl() {
        this.time = new Time();
        this.worldBorder = new WorldBorder(this);
        teams = new ArrayList<>();
        objectives = new ArrayList<>();
        bossBars = new ArrayList<>();
        advancements = new ArrayList<>();
    }

    @Override
    public void getSeed() {
        DatapackManager.writeCommand("seed");
    }

    @Override
    public Clone clone(Position begin, Position end, Position destination) {
        return new Clone(begin,end,destination);
    }

    @Override
    public Fill fill(Position begin, Position end, Block with) {
        return new Fill(begin,end,with);
    }

    @Override
    public void setDefaultGameMode(GameMode gm) {
        DatapackManager.writeCommand("defaultgamemode " + gm.toString().toLowerCase());
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        DatapackManager.writeCommand("difficulty " + difficulty.toString().toLowerCase());
    }

    @Override
    public <T> void setGameRule(GameRule<T> gameRule, T value) {
        DatapackManager.writeCommand("gamerule " + gameRule.getId() + " " + value);
    }

    @Override
    public <T> void getGameRule(GameRule<T> gameRule) {
        DatapackManager.writeCommand("gamerule " + gameRule.getId());
    }

    @Override
    public void spawnParticle(Particle p) {
        DatapackManager.writeCommand(p.toString());
    }

    @Override
    public SetBlock setBlock(Position pos, Block block) {
        return new SetBlock(pos,block);
    }

    @Override
    public void setWorldSpwan(Position pos) {
        DatapackManager.writeCommand("setworldspawn " + pos);
    }

    @Override
    public void summon(EntityType type) {
        DatapackManager.writeCommand("summon " + type.getId());
    }

    @Override
    public void summon(EntityType type, Position pos) {
        DatapackManager.writeCommand("summon " + type.getId() + " " + pos);
    }

    @Override
    public void summon(EntityType type, Position pos, NBT nbt) {
        DatapackManager.writeCommand("summon " + type.getId() + " " + pos + " " + nbt);
    }

    @Override
    public Time getTime() {
        return time;
    }

    @Override
    public void setWeather(Weather weather) {
        DatapackManager.writeCommand("weather " + weather.toString().toLowerCase());
    }

    @Override
    public void setWeather(Weather weather, int duration) {
        if (duration > 1000000 || duration < 0) {
            throw new IllegalArgumentException("Weather duration must be between 0 and 1,000,000");
        }
        DatapackManager.writeCommand("weather " + weather.toString().toLowerCase() + " " + duration);
    }

    @Override
    public WorldBorder getWorldBorder() {
        return worldBorder;
    }

    @Override
    public Advancement getAdvancement(ResourceLocation id) {
        for (Advancement a : advancements) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return new Advancement(id);
    }

    @Override
    public Objective getObjective(String name) {
        return Objective.of(name);
    }

    @Override
    public String toString() {
        return "server";
    }
}
