package com.shinybunny.mcfapi.entity;

public enum SoundSource {
    MASTER, MUSIC, RECORD, WEATHER, BLOCK, HOSTILE, NEUTRAL, PLAYER, AMBIENT, VOICE;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
