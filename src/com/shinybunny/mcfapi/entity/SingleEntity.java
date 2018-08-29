package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.scoreboard.ObjectiveEntry;

public interface SingleEntity extends ObjectiveEntry {

    default void getData(String path) {
        DatapackManager.writeCommand("data get entity " + this + " " + path);
    }

    default void getData(String path, double scale) {
        DatapackManager.writeCommand("data get entity " + this + " " + path + " " + scale);
    }

}
