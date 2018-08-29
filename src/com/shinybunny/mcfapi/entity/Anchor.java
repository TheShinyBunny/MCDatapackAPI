package com.shinybunny.mcfapi.entity;

public enum Anchor {
    FEET, EYES;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
