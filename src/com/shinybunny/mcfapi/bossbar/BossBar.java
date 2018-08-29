package com.shinybunny.mcfapi.bossbar;

import com.shinybunny.mcfapi.MCObject;
import com.shinybunny.mcfapi.entity.PlayerList;
import com.shinybunny.mcfapi.server.ResourceLocation;
import com.shinybunny.mcfapi.TextComponent;

public class BossBar extends MCObject {

    private TextComponent name;
    private ResourceLocation id;

    public BossBar(ResourceLocation id, TextComponent name) {
        this.id = id;
        this.name = name;
        create();
    }

    public void isVisible() {
        getProperty("visible");
    }

    public void setViewers(PlayerList viewers) {
        updateProperty("players",viewers);
    }

    public void setValue(int value) {
        updateProperty("value",value);
    }

    public void setName(TextComponent name) {
        updateProperty("name",name);
        this.name = name;
    }

    public void setMax(int max) {
        updateProperty("max",max);
    }

    public void setStyle(BossStyle style) {
        updateProperty("style",style.toString());
    }

    public void getMax() {
        getProperty("max");
    }

    public void getValue() {
        getProperty("value");
    }

    public void getViewerCount() {
        getProperty("players");
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    protected String getGetterSyntax(String property) {
        return "get " + id + " " + property;
    }

    @Override
    public String getCommand() {
        return "bossbar";
    }

    @Override
    public String getUpdateSyntax(String property, Object value) {
        return "set " + id + " " + property + " " + value;
    }

    @Override
    public String getCreationSyntax() {
        return "add " + id + " " + name;
    }

    @Override
    public String getDeletionSyntax() {
        return "remove " + id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
