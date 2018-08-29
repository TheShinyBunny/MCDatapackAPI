package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.DatapackManager;

public class TagList {

    private EntityList owners;

    public TagList(EntityList owners) {
        this.owners = owners;
    }

    public void add(String tag) {
        DatapackManager.writeCommand("tag " + owners + " add " + tag);
    }

    public void remove(String tag) {
        DatapackManager.writeCommand("tag " + owners + " remove " + tag);
    }

    public void list() {
        DatapackManager.writeCommand("tag " + owners + " list");
    }
}
