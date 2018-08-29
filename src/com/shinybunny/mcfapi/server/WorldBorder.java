package com.shinybunny.mcfapi.server;

import com.shinybunny.mcfapi.DatapackManager;

public class WorldBorder {
    private final World world;
    private Damage damage;
    private Warning warning;

    public WorldBorder(WorldImpl world) {
        this.world = world;
        this.damage = new Damage();
        this.warning = new Warning();
    }

    public void add(int blocks) {
        DatapackManager.writeCommand("worldborder add " + blocks);
    }

    public void add(int blocks, int seconds) {
        DatapackManager.writeCommand("worldborder add " + blocks + " " + seconds);
    }

    public void setCenter(double x, double z) {
        DatapackManager.writeCommand("worldborder center " + x + " " + z);
    }

    public void set(int diameter) {
        DatapackManager.writeCommand("worldborder set " + diameter);
    }

    public void get() {
        DatapackManager.writeCommand("worldborder get");
    }

    public void set(int diameter, int seconds) {
        DatapackManager.writeCommand("worldborder set " + diameter + " " + seconds);
    }

    public Damage getDamage() {
        return damage;
    }

    public Warning getWarning() {
        return warning;
    }

    public class Damage {

        public void setBuffer(int blocks) {
            DatapackManager.writeCommand("worldborder damage buffer " + blocks);
        }

        public void setAmount(double damage) {
            DatapackManager.writeCommand("worldborder damage amount " + damage);
        }

    }

    private class Warning {

        public void setDistance(int blocks) {
            DatapackManager.writeCommand("worldborder warning distance " + blocks);
        }

        public void setTime(int seconds) {
            DatapackManager.writeCommand("worldborder warning time " + seconds);
        }

    }
}
