package com.shinybunny.mcfapi.scoreboard;

import com.shinybunny.mcfapi.BlockItem;
import com.shinybunny.mcfapi.ChatColor;
import com.shinybunny.mcfapi.Item;
import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.entity.EntityType;

import java.util.function.Function;

public class Criteria {

    private String name;
    private boolean isModifiable;

    public Criteria(String name) {
        this(name,true);
    }

    public Criteria(String name, boolean isModifiable) {
        this.name = name;
        this.isModifiable = isModifiable;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final Criteria DUMMY = new Criteria("dummy");
    public static final Criteria TRIGGER = new Criteria("trigger");
    public static final Criteria AIR = new Criteria("air",false);
    public static final Criteria ARMOR = new Criteria("armor",false);
    public static final Criteria FOOD = new Criteria("food",false);
    public static final Criteria DEATHS = new Criteria("deathCount");
    public static final Criteria HEALTH = new Criteria("health",false);
    public static final Criteria LEVEL = new Criteria("level",false);
    public static final Criteria XP = new Criteria("xp",false);
    public static final Criteria PLAYER_KILLS = new Criteria("playerKillCount");
    public static final Criteria TOTAL_KILLS = new Criteria("totalKillCount");

    public static final Advanced<ChatColor> KILLED_BY_TEAM = new Advanced<>("killedByTeam", c->c.toString().toLowerCase(),'.');
    public static final Advanced<ChatColor> KILL_TEAM = new Advanced<>("teamkill", c->c.toString().toLowerCase(),'.');
    public static final Advanced<Item> BROKEN_ITEM = new Advanced<>("minecraft.broken",i-> i.getId().toString(),':');
    public static final Advanced<Item> CRAFTED_ITEM = new Advanced<>("minecraft.crafted",i->i.getId().toString(),':');
    public static final Advanced<Item> DROPPED_ITEM = new Advanced<>("minecraft.dropped",i->i.getId().toString(),':');
    public static final Advanced<EntityType> KILLED_ENTITY = new Advanced<>("minecraft.killed", i->i.getId().toString(),':');
    public static final Advanced<EntityType> KILLED_BY_ENTITY = new Advanced<>("minecraft.killed_by", i->i.getId().toString(),':');
    public static final Advanced<Block> MINED_BLOCK = new Advanced<>("minecraft.killed", i->i.getId().toString(),':');
    public static final Advanced<Item> PICKED_ITEM = new Advanced<>("minecraft.picked_up", i->i.getId().toString(),':');
    public static final Advanced<BlockItem> USED = new Advanced<>("minecraft.used", i->i.getId().toString(),':');

    public static final Criteria ANIMALS_BRED = new Criteria("minecraft.custom:minecraft.animals_bred");
    public static final Criteria DISTANCE_BY_ELYTRA = new Criteria("minecraft.custom:minecraft.aviate_one_cm");

    public static class Advanced<T> {

        private final char separator;
        private final Function<T, String> stringify;
        private String name;

        public Advanced(String name, Function<T,String> stringify, char nameSeparator) {
            this.name = name;
            this.stringify = stringify;
            this.separator = nameSeparator;
        }

        public String getName() {
            return name;
        }

        public Criteria of(T obj) {
            return new Criteria(name + separator + stringify.apply(obj));
        }

    }
}
