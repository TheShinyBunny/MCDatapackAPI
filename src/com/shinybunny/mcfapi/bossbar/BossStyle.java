package com.shinybunny.mcfapi.bossbar;

public enum BossStyle {
    PROGRESS, NOTCHED_6, NOTCHED_10, NOTCHED_12, NOTCHED_20;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
