package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.server.ResourceLocation;

public class Recipes {

    private final PlayerList owners;

    public Recipes(PlayerList owners) {
        this.owners = owners;
    }

    public void give(ResourceLocation recipe) {
        DatapackManager.writeCommand("recipe give " + owners + " " + recipe);
    }

    public void take(ResourceLocation recipe) {
        DatapackManager.writeCommand("recipe take " + owners + " " + recipe);
    }

    public void giveAll() {
        DatapackManager.writeCommand("recipe give " + owners + " *");
    }

    public void takeAll() {
        DatapackManager.writeCommand("recipe take " + owners + " *");
    }
}
