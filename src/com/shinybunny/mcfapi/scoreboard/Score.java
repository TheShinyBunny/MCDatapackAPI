package com.shinybunny.mcfapi.scoreboard;

public class Score {

    private Objective objective;
    private ObjectiveEntry owner;

    public Score(Objective objective, ObjectiveEntry owner) {
        this.objective = objective;
        this.owner = owner;
    }

    public void get() {
        objective.queryScore(owner);
    }

    public void set(int value) {
        objective.setScore(owner,value);
    }

    public void add(int amount) {
        objective.addScore(owner,amount);
    }

    public void remove(int amount) {
        objective.removeScore(owner,amount);
    }

    public void reset() {
        objective.resetScore(owner);
    }

    public void enable() {
        objective.enableTrigger(owner);
    }

    public void operate(Operator operator, ObjectiveEntry src, Objective srcObj) {
        objective.operateScore(owner,operator,src,srcObj);
    }
}
