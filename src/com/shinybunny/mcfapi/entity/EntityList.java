package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.*;
import com.shinybunny.mcfapi.advancements.Advancement;
import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.blocks.Clone;
import com.shinybunny.mcfapi.blocks.Fill;
import com.shinybunny.mcfapi.blocks.SetBlock;
import com.shinybunny.mcfapi.execute.Execute;
import com.shinybunny.mcfapi.NBT;
import com.shinybunny.mcfapi.scoreboard.Objective;
import com.shinybunny.mcfapi.scoreboard.ObjectiveEntries;
import com.shinybunny.mcfapi.server.*;

public class EntityList implements InventoryHolder, ObjectiveEntries, World {
    private EntitySelector selector;
    private TagList tags;
    private World world;
    public static final EntityList self = new EntitySelector<>(EntityList.class,TargetSelector.SELF).find();

    public EntityList(EntitySelector selector) {
        this.selector = selector;
        tags = new TagList(this);
        this.world = DatapackManager.getDatapack().getWorld();
    }

    public EntityList() {
        tags = new TagList(this);
        this.world = DatapackManager.getDatapack().getWorld();
    }

    public World getWorld() {
        return world;
    }

    public Execute execute() {
        if (this instanceof FunctionContainer) {
            return new Execute();
        }
        return new Execute().as(this);
    }

    public final void giveEffect(Effect effect) {
        DatapackManager.writeCommand("effect give " + this + " " + effect.toString().toLowerCase());
    }

    public final void giveEffect(Effect effect, int seconds) {
        DatapackManager.writeCommand("effect give " + this + " " + effect.toString().toLowerCase() + " " + seconds);
    }

    public final void giveEffect(Effect effect, int seconds, int level) {
        DatapackManager.writeCommand("effect give " + this + " " + effect.toString().toLowerCase() + " " + seconds + " " + level);
    }

    public final void giveEffect(Effect effect, int seconds, int level, boolean hideParticles) {
        DatapackManager.writeCommand("effect give " + this + " " + effect.toString().toLowerCase() + " " + seconds + " " + level + " " + hideParticles);
    }

    public final void clearAllEffects() {
        DatapackManager.writeCommand("effect clear " + this);
    }

    public final void clearEffect(Effect effect) {
        DatapackManager.writeCommand("effect clear " + this + " " + effect.toString().toLowerCase());
    }

    public final void kill() {
        DatapackManager.writeCommand("kill " + this);
    }

    public final void replaceItem(Slot slot, Item item) {
        DatapackManager.writeCommand("replaceitem entity " + this + " " + slot + " " + item);
    }

    public final void replaceItem(Slot slot, Item item, int count) {
        DatapackManager.writeCommand("replaceitem entity " + this + " " + slot + " " + item + " " + count);
    }

    public final void say(String message) {
        if (this instanceof FunctionContainer) {
            DatapackManager.writeCommand("say " + message);
        } else {
            DatapackManager.writeCommand("say " + this + " " + message);
        }
    }

    @Override
    public final String toString() {
        if (this instanceof FunctionContainer) {
            return "@s";
        }
        return selector == null ? "server" : selector.toString();
    }

    public final TagList getTags() {
        return tags;
    }

    public final void leaveTeam() {
        DatapackManager.writeCommand("team leave " + this);
    }

    public final void spread(double x, double z, float distance, float maxRange, boolean respectTeams) {
        DatapackManager.writeCommand("spreadplayers " + x + " " + z + " " + distance + " " + maxRange + " " + respectTeams + " " + this);
    }

    public final void teleport(Position destination) {
        if (this instanceof FunctionContainer) {
            DatapackManager.writeCommand("tp " + destination);
        } else {
            DatapackManager.writeCommand("tp " + this + " " + destination);
        }
    }

    public final void teleport(SingleEntity entity) {
        DatapackManager.writeCommand("tp " + this + " " + entity);
    }

    public final void teleportFacing(Position pos, Position facing) {
        DatapackManager.writeCommand("tp " + this + " " + pos + " facing " + facing);
    }

    public final void teleportFacing(Position pos, SingleEntity facing) {
        DatapackManager.writeCommand("tp " + this + " " + pos + " facing entity " + facing);
    }

    public final void teleportFacing(Position pos, SingleEntity facing, Anchor anchor) {
        DatapackManager.writeCommand("tp " + this + " " + pos + " facing entity " + facing + " " + anchor);
    }

    public final void teleport(Position pos, Rotation rotation) {
        DatapackManager.writeCommand("tp " + this + " " + pos + " " + rotation);
    }

    public final void trigger(Objective objective) {
        DatapackManager.writeCommand("trigger " + objective);
    }

    public final void triggerSet(Objective objective, int set) {
        DatapackManager.writeCommand("trigger " + objective + " set " + set);
    }

    public final void triggerAdd(Objective objective, int add) {
        DatapackManager.writeCommand("trigger " + objective + " add " + add);
    }

    @Override
    public final boolean equals(Object obj) {
        return obj instanceof EntityList && selector.equals(((EntityList) obj).selector);
    }

    @Override
    public void getSeed() {
        world.getSeed();
    }

    @Override
    public Clone clone(Position begin, Position end, Position destination) {
        return world.clone(begin, end, destination);
    }

    @Override
    public Fill fill(Position begin, Position end, Block with) {
        return world.fill(begin, end, with);
    }

    @Override
    public void setDefaultGameMode(GameMode gm) {
        world.setDefaultGameMode(gm);
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        world.setDifficulty(difficulty);
    }

    @Override
    public <T> void setGameRule(GameRule<T> gameRule, T value) {
        world.setGameRule(gameRule, value);
    }

    @Override
    public <T> void getGameRule(GameRule<T> gameRule) {
        world.getGameRule(gameRule);
    }

    @Override
    public void spawnParticle(Particle p) {
        world.spawnParticle(p);
    }

    @Override
    public SetBlock setBlock(Position pos, Block block) {
        return world.setBlock(pos, block);
    }

    @Override
    public void setWorldSpwan(Position pos) {
        world.setWorldSpwan(pos);
    }

    @Override
    public void summon(EntityType type) {
        world.summon(type);
    }

    @Override
    public void summon(EntityType type, Position pos) {
        world.summon(type, pos);
    }

    @Override
    public void summon(EntityType type, Position pos, NBT nbt) {
        world.summon(type, pos, nbt);
    }

    @Override
    public Time getTime() {
        return world.getTime();
    }

    @Override
    public void setWeather(Weather weather) {
        world.setWeather(weather);
    }

    @Override
    public void setWeather(Weather weather, int duration) {
        world.setWeather(weather, duration);
    }

    @Override
    public WorldBorder getWorldBorder() {
        return world.getWorldBorder();
    }

    @Override
    public Advancement getAdvancement(ResourceLocation id) {
        return world.getAdvancement(id);
    }

    @Override
    public Objective getObjective(String name) {
        return world.getObjective(name);
    }
}
