package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.DatapackManager;

public class XP {
    protected final PlayerList owners;

    public XP(PlayerList owners) {
        this.owners = owners;
    }

    public void addLevels(int levels) {
        DatapackManager.writeCommand("xp add " + owners + " " + levels + " levels");
    }

    public void addPoints(int points) {
        DatapackManager.writeCommand("xp add " + owners + " " + points + " points");
    }

    public void setLevels(int levels) {
        DatapackManager.writeCommand("xp set " + owners + " " + levels + " levels");
    }

    public void setPoints(int points) {
        DatapackManager.writeCommand("xp set " + owners + " " + points + " levels");
    }
}
