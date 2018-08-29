package com.shinybunny.mcfapi.server;

import com.shinybunny.mcfapi.DatapackManager;

public class Time {

    public static final int DAY = 1000;
    public static final int NIGHT = 13000;
    public static final int NOON = 6000;
    public static final int MIDNIGHT = 18000;

    public void set(int time) {
        DatapackManager.writeCommand("time set " + time);
    }

    public void add(int time) {
        DatapackManager.writeCommand("time add " + time);
    }

    public void getDay() {
        DatapackManager.writeCommand("time query day");
    }

    public void getDayTime() {
        DatapackManager.writeCommand("time query daytime");
    }

    public void getGameTime() {
        DatapackManager.writeCommand("time query gametime");
    }
}
