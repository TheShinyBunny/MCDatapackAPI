package com.shinybunny.mcfapi.server;

public enum GameMode {
    SURVIVAL, CREATIVE, SPECTATOR, ADVENTURE;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
