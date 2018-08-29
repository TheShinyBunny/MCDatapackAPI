package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.entity.InventoryHolder;
import com.shinybunny.mcfapi.entity.Slot;

public class Position implements InventoryHolder, DataContainer {

    private Coordinate x;
    private Coordinate y;
    private Coordinate z;

    public Position(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position() {
    }

    public static Position relative(double x, double y, double z) {
        return new Position(Coordinate.relative(x),Coordinate.relative(y),Coordinate.relative(z));
    }

    public static Position absolute(double x, double y, double z) {
        return new Position(Coordinate.absolute(x),Coordinate.absolute(y),Coordinate.absolute(z));
    }

    public static Position here() {
        return relative(0,0,0);
    }

    public static Position rotated(double x, double y, double z) {
        return new Position(Coordinate.rotated(x),Coordinate.rotated(y),Coordinate.rotated(z));
    }

    public Position x(Coordinate x) {
        this.x = x;
        return this;
    }

    public Position y(Coordinate y) {
        this.y = y;
        return this;
    }

    public Position z(Coordinate z) {
        this.z = z;
        return this;
    }


    @Override
    public void getData(String path) {
        DatapackManager.writeCommand("data get block " + this + " " + path);
    }

    @Override
    public void getData(String path, double scale) {
        DatapackManager.writeCommand("data get block " + this + " " + path + " " + scale);
    }

    @Override
    public void mergeData(NBT nbt) {
        DatapackManager.writeCommand("data merge block " + this + " " + nbt);
    }

    @Override
    public void removeData(String path) {
        DatapackManager.writeCommand("data remove block " + this + " " + path);
    }

    @Override
    public void replaceItem(Slot slot, Item item) {
        DatapackManager.writeCommand("replaceitem block " + this + " " + slot + " " + item);
    }

    @Override
    public void replaceItem(Slot slot, Item item, int count) {
        DatapackManager.writeCommand("replaceitem block " + this + " " + slot + " " + item + " " + count);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
}
