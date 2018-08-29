package com.shinybunny.mcfapi.scoreboard;

public class DummyEntry implements ObjectiveEntry {

    private final String name;

    public DummyEntry(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
