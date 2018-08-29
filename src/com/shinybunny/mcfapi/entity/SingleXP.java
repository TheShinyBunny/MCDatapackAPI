package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.DatapackManager;

public class SingleXP extends XP {
    public SingleXP(Player owner) {
        super(owner);
    }

    public void getLevels() {
        DatapackManager.writeCommand("xp query " + owners + " levels");
    }

    public void getPoints() {
        DatapackManager.writeCommand("xp query " + owners + " points");
    }
}
