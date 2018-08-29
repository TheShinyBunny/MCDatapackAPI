package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.bossbar.BossBar;
import com.shinybunny.mcfapi.entity.*;
import com.shinybunny.mcfapi.scoreboard.Criteria;
import com.shinybunny.mcfapi.scoreboard.Objective;
import com.shinybunny.mcfapi.server.ResourceLocation;

public interface CommandExecutor {

    default BossBar createBossBar(ResourceLocation id, TextComponent name) {
        return new BossBar(id,name);
    }

    default Objective createObjective(String name, Criteria criteria) {
        return new Objective(name,criteria);
    }

    default Objective createObjective(String name, Criteria criteria, TextComponent displayName) {
        return new Objective(name,criteria,displayName);
    }

    default EntitySelector<EntityList> selectEntities() {
        return new EntitySelector<>(EntityList.class,TargetSelector.ALL_ENTITIES);
    }

    default EntitySelector<Entity> selectEntity() {
        return new EntitySelector<>(Entity.class,TargetSelector.ALL_ENTITIES).limit(1);
    }

    default EntitySelector<PlayerList> selectPlayers() {
        return new EntitySelector<>(PlayerList.class,TargetSelector.ALL_PLAYERS);
    }

    default Player closestPlayer() {
        return new EntitySelector<>(Player.class,TargetSelector.CLOSEST_PLAYER).find();
    }

    default EntitySelector<Player> randomPlayer() {
        return new EntitySelector<>(Player.class,TargetSelector.RANDOM_PLAYER);
    }

    default EntitySelector<Player> self() {
        return new EntitySelector<>(Player.class,TargetSelector.SELF);
    }
}
