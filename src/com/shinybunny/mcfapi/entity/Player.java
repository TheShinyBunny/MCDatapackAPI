package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.DatapackManager;

public class Player extends PlayerList implements SingleEntity {

    public Player(EntitySelector selector) {
        super(selector);
    }

    public Player() {
    }

    @Override
    protected final XP createXP() {
        return new SingleXP(this);
    }

    @Override
    public final SingleXP getXP() {
        return (SingleXP) super.getXP();
    }

    public final void tell(Player p, String message) {
        DatapackManager.writeCommand("tell " + p + " " + message);
    }
}
