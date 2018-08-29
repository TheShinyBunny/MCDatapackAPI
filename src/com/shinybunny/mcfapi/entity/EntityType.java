package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.server.ResourceLocation;

import java.util.function.BiFunction;

public class EntityType<B extends Entity.Builder> {
    public static final EntityType<Entity.Creeper> CREEPER = new EntityType<>("creeper",Entity.Creeper::new);
    public static final EntityType<Entity.Builder> PLAYER = new EntityType<>("player",null);
    public static final EntityType<Entity.Builder> ARMOR_STAND = new EntityType<>("armor_stand",null);

    private final ResourceLocation id;
    private final BiFunction<Position, String, B> constructor;

    private EntityType(String id, BiFunction<Position,String,B> constructor) {
        this.id = new ResourceLocation("minecraft",id);
        this.constructor = constructor;
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public B create(Position pos, String tag) {
        if (constructor == null) {
            return null;
        }
        return constructor.apply(pos,tag);
    }
}
