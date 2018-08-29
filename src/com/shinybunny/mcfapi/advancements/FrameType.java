package com.shinybunny.mcfapi.advancements;

public enum FrameType {
    TASK, GOAL, CHALLENGE;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
