package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.NBT;
import com.shinybunny.mcfapi.Range;
import com.shinybunny.mcfapi.server.GameMode;

import java.util.HashMap;
import java.util.Map;

public class EntitySelector<T extends EntityList> {

    private final TargetSelector selector;
    private EntityType type;
    private boolean negateType;
    private String name;
    private Map<String,Boolean> tags;
    private int limit;
    private Range level;
    private GameMode gm;
    private Class<T> entity;
    private NBT nbt;
    private Range distance;

    public EntitySelector(Class<T> entity, TargetSelector selector) {
        this.selector = selector;
        this.entity = entity;
        this.limit = -1;
        tags = new HashMap<>();
    }

    public EntitySelector<T> type(EntityType type) {
        this.type = type;
        return this;
    }

    public EntitySelector<T> notType(EntityType not) {
        this.type = not;
        this.negateType = true;
        return this;
    }

    public EntitySelector<T> limit(int limit) {
        this.limit = limit;
        return this;
    }

    public EntitySelector<T> tag(String tag) {
        tags.put(tag,true);
        return this;
    }

    public EntitySelector<T> name(String name) {
        this.name = name;
        return this;
    }

    public EntitySelector<T> notTag(String tag) {
        tags.put(tag,false);
        return this;
    }

    public EntitySelector<T> level(Range level) {
        this.level = level;
        return this;
    }

    public T find() {
        if (entity == Entity.class) {
            return (T) findEntity();
        } else if (entity == PlayerList.class) {
            return (T) findPlayers();
        } else if (entity == Player.class) {
            return (T) findPlayer();
        }
        return (T) findEntities();
    }

    private Player findPlayer() {
        if (type == EntityType.PLAYER && selector == TargetSelector.ALL_ENTITIES) {
            if (limit == 1) {
                return new Player(this);
            }
            throw new IllegalStateException("This entity selector does not select limit=1!");
        } else if ((selector.isOnePlayer() && (type == null || type == EntityType.PLAYER)) || (selector == TargetSelector.ALL_PLAYERS && limit == 1) || selector == TargetSelector.SELF) {
            return new Player(this);
        }
        throw new IllegalStateException("This entity selector does not select only one player!");
    }

    private PlayerList findPlayers() {
        if (limit == 1) {
            throw new IllegalStateException("This entity selector selects one player/entity!");
        }
        if (type == EntityType.PLAYER && selector == TargetSelector.ALL_ENTITIES) {
            return new PlayerList(this);
        } else if (selector == TargetSelector.ALL_PLAYERS) {
            return new PlayerList(this);
        }
        throw new IllegalStateException("This entity selector does not select only players!");
    }

    private Entity findEntity() {
        if (selector != TargetSelector.ALL_PLAYERS && selector != TargetSelector.CLOSEST_PLAYER) {
            if (limit == 1 || selector == TargetSelector.SELF) {
                return new Entity(this);
            }
            throw new IllegalStateException("This entity selector does not select only one entity!");
        }
        throw new IllegalStateException("This entity selector does not target entities!");
    }

    private EntityList findEntities() {
        if (limit == 1) {
            throw new IllegalStateException("This entity selector selects only one player/entity!");
        }
        if (selector != TargetSelector.ALL_PLAYERS && selector != TargetSelector.CLOSEST_PLAYER) {
            return new EntityList(this);
        }
        throw new IllegalStateException("This entity selector does not select entities!");
    }

    public NBT toNBT() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(selector.getSign());
        addProperty(b,"name",name);
        if (type != null) {
            addProperty(b, "type", (negateType ? "!" : "") + type.getId());
        }
        addProperty(b,"limit",limit);
        addProperty(b,"level",level);
        addProperty(b,"gamemode",gm);
        addProperty(b,"nbt",nbt);
        addProperty(b,"distance",distance);

        for (Map.Entry<String,Boolean> e : tags.entrySet()) {
            addProperty(b,"tag",e.getValue() ? e.getKey() : "!" + e.getKey());
        }
        if (b.length() > 2) {
            b.append("]");
        }
        return b.toString();
    }

    private void addProperty(StringBuilder b, String key, Object value) {
        if (value != null) {
            if (b.length() > 2) {
                b.append(",");
            }
            if (value instanceof String && ((String) value).contains(" ")) {
                value = "\"" + value + "\"";
            }
            if (value instanceof Integer) {
                if ((int)value == -1) {
                    return;
                }
            }
            if (b.length() == 2) {
                b.append("[");
            }
            b.append(key).append("=").append(value);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EntitySelector)) {
            return false;
        }
        EntitySelector other = (EntitySelector) obj;
        if (other.selector != selector) {
            return false;
        }
        if (other.type != type) {
            return false;
        }
        if (other.negateType != negateType) {
            return false;
        }
        if (!other.name.equals(name)) {
            return false;
        }
        if (!tags.equals(other.tags)) {
            return false;
        }
        if (limit != other.limit) {
            return false;
        }
        if (!level.equals(other.level)) {
            return false;
        }
        return entity == other.entity;
    }

    public EntitySelector<T> gamemode(GameMode mode) {
        this.gm = mode;
        return this;
    }

    public EntitySelector<T> nbt(NBT nbt) {
        this.nbt = nbt;
        return this;
    }

    public EntitySelector<T> distance(Range distance) {
        this.distance = distance;
        return this;
    }
}
