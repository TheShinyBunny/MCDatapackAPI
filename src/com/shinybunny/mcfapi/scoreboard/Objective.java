package com.shinybunny.mcfapi.scoreboard;

import com.shinybunny.mcfapi.MCObject;
import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.TextComponent;

public class Objective extends MCObject {

    private String name;
    private Criteria criteria;
    private TextComponent displayName;

    public Objective(String name, Criteria criteria) {
        this(name,criteria,null);
    }

    public Objective(String name, Criteria criteria, TextComponent displayName) {
        this.name = name;
        this.criteria = criteria;
        this.displayName = displayName;
        this.create();
    }

    private Objective(String name) {
        this.name = name;
    }

    public static Objective of(String name) {
        return new Objective(name);
    }

    public String getName() {
        return name;
    }

    public void setDisplayName(TextComponent displayName) {
        updateProperty("displayname", displayName);
        this.displayName = displayName;
    }

    public void setRenderType(RenderType type) {
        updateProperty("rendertype",type);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    protected String getGetterSyntax(String property) {
        return "";
    }

    @Override
    public String getCommand() {
        return "scoreboard objectives";
    }

    @Override
    public String getUpdateSyntax(String property, Object value) {
        return "modify " + name + " " + property + " " + value;
    }

    @Override
    public String getCreationSyntax() {
        return "add " + name + " " + criteria + (displayName == null ? "" : " " + displayName);
    }

    @Override
    public String getDeletionSyntax() {
        return "remove " + name;
    }

    public void setScore(ObjectiveEntries entries, int value) {
        DatapackManager.writeCommand("scoreboard players set " + entries + " " + this + " " + value);
    }

    public Score getScore(ObjectiveEntry entry) {
        return new Score(this,entry);
    }

    public void addScore(ObjectiveEntries entries, int value) {
        DatapackManager.writeCommand("scoreboard players add " + entries + " " + this + " " + value);
    }

    public void removeScore(ObjectiveEntries entries, int value) {
        DatapackManager.writeCommand("scoreboard players remove " + entries + " " + this + " " + value);
    }

    public void resetScore(ObjectiveEntries entries) {
        DatapackManager.writeCommand("scoreboard players reset " + entries + " " + this);
    }

    public void enableTrigger(ObjectiveEntries entries) {
        DatapackManager.writeCommand("scoreboard players enable " + entries + " " + this);
    }

    public void operateScore(ObjectiveEntries entries, Operator operator, ObjectiveEntry src, Objective srcObjective) {
        DatapackManager.writeCommand("scoreboard players operation " + entries + " " + this + " " + operator.getSign() + " " + src + " " + srcObjective);
    }

    public void queryScore(ObjectiveEntry entry) {
        DatapackManager.writeCommand("scoreboard players get " + entry + " " + this);
    }


    public enum RenderType {
        HEARTS, INTEGER;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
