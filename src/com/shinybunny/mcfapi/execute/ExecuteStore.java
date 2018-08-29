package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.bossbar.BossBar;
import com.shinybunny.mcfapi.entity.SingleEntity;
import com.shinybunny.mcfapi.scoreboard.Objective;
import com.shinybunny.mcfapi.scoreboard.ObjectiveEntries;

public class ExecuteStore extends ExecutePart {

    private boolean result;
    private ObjectiveEntries name;
    private Objective objective;
    private Position pos;
    private SingleEntity entity;
    private String path;
    private Class<? extends Number> type;
    private double scale;
    private BossBar bossBar;
    private boolean value;

    public ExecuteStore(boolean result, ObjectiveEntries name, Objective objective) {
        this.result = result;
        this.name = name;
        this.objective = objective;
    }

    public ExecuteStore(boolean result, Position pos, String path, Class<? extends Number> type, double scale) {
        this.result = result;
        this.pos = pos;
        this.path = path;
        this.type = type;
        this.scale = scale;
    }

    public ExecuteStore(boolean result, SingleEntity entity, String path, Class<? extends Number> type, double scale) {
        this.result = result;
        this.entity = entity;
        this.path = path;
        this.type = type;
        this.scale = scale;
    }

    public ExecuteStore(boolean result, BossBar bossBar, boolean value) {
        this.result = result;
        this.bossBar = bossBar;
        this.value = value;
    }

    @Override
    public String getName() {
        return "store";
    }

    @Override
    public String buildSyntax() {
        String s = result ? "result " : "success ";
        if (name != null) {
            return s + "score " + name + " " + objective;
        }
        if (pos != null) {
            return s + "block " + pos + " " + path + " " + nameType(type) + " " + scale;
        }
        if (entity != null) {
            return s + "entity " + entity + " " + path + " " + nameType(type) + " " + scale;
        }
        return s + "bossbar " + bossBar + " " + (value ? "value" : "max");
    }

    private String nameType(Class<? extends Number> type) {
        return type == Integer.TYPE ? "int" : type.getSimpleName().toLowerCase();
    }

    public static class Builder {

        private boolean result;
        private Execute execute;

        public Builder(Execute execute, boolean result) {
            this.execute = execute;
            this.result = result;
        }

        public Execute score(ObjectiveEntries name, Objective objective) {
            return execute.then(new ExecuteStore(result,name,objective));
        }

        public Execute block(Position pos, String path, Class<? extends Number> type, double scale) {
            return execute.then(new ExecuteStore(result,pos,path,type,scale));
        }

        public Execute entity(SingleEntity entity, String path, Class<? extends Number> type, double scale) {
            return execute.then(new ExecuteStore(result,entity,path,type,scale));
        }

        public Execute bossbarValue(BossBar bossBar) {
            return execute.then(new ExecuteStore(result,bossBar,true));
        }

        public Execute bossbarMax(BossBar bossBar) {
            return execute.then(new ExecuteStore(result,bossBar,false));
        }
    }
}
