package com.shinybunny.mcfapi.scoreboard;

public class TeamOption<T> {

    private Team team;
    private String name;
    private T value;

    public TeamOption(Team t, String name, T value) {
        this.team = t;
        this.name = name;
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
        team.updateOption(this);
    }

    public T get() {
        return value;
    }

    public String getName() {
        return name;
    }
}
