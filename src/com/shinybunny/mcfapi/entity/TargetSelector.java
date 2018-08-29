package com.shinybunny.mcfapi.entity;

public enum TargetSelector {
    ALL_ENTITIES("@e"), ALL_PLAYERS("@a"), RANDOM_PLAYER("@r"), CLOSEST_PLAYER("@p"), SELF("@s");

    private String sign;

    TargetSelector(String sign) {
        this.sign = sign;
    }

    public boolean isOnePlayer() {
        return this == CLOSEST_PLAYER || this == RANDOM_PLAYER;
    }

    public String getSign() {
        return sign;
    }
}
