package com.shinybunny.mcfapi.server;

public class GameRule<T> {

    public static final GameRule<Boolean> ANNOUNCE_ADVANCEMENT = new GameRule<>("announceAdvancement");
    public static final GameRule<Boolean> COMMAND_BLOCK_OUTPUT = new GameRule<>("commandBlockOutput");
    private final String id;

    public GameRule(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
