package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.DataContainer;
import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.NBT;

public class Entity extends EntityList implements DataContainer,SingleEntity {

    public Entity(EntitySelector selector) {
        super(selector);
    }

    @Override
    public void mergeData(NBT nbt) {
        DatapackManager.writeCommand("data merge entity " + this + " " + nbt);
    }

    @Override
    public void removeData(String path) {
        DatapackManager.writeCommand("data remove entity " + this + " " + path);
    }

    @Override
    public void getData(String path) {
        DatapackManager.writeCommand("data get entity " + this + " " + path);
    }

    @Override
    public void getData(String path, double scale) {
        DatapackManager.writeCommand("data get entity " + this + " " + path + " " + scale);
    }

    public static abstract class Builder {

        private final EntityType type;
        private EntitySelector<Entity> selector;
        private Position pos;
        protected NBT nbt;

        public Builder(EntityType type, Position pos, String tag) {
            this.selector = new EntitySelector<>(Entity.class,TargetSelector.ALL_ENTITIES).limit(1).type(type).tag(tag);
            this.pos = pos;
            this.nbt = new NBT();
            this.type = type;
        }

        public Entity summon() {
            DatapackManager.getDatapack().getWorld().summon(type,pos,nbt);
            return selector.find();
        }

    }

    public static class Creeper extends Builder {
        public Creeper(Position pos, String tag) {
            super(EntityType.CREEPER,pos,tag);
        }

        public Creeper setPowered() {
            nbt.set("powered",true);
            return this;
        }
    }
}
