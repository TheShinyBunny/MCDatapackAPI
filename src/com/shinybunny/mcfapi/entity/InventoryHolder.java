package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.Item;

public interface InventoryHolder {

    void replaceItem(Slot slot, Item item);

    void replaceItem(Slot slot, Item item, int count);

}
