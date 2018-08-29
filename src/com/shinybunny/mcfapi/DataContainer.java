package com.shinybunny.mcfapi;

public interface DataContainer {

    void getData(String path);

    void getData(String path, double scale);

    void mergeData(NBT nbt);

    void removeData(String path);

}
