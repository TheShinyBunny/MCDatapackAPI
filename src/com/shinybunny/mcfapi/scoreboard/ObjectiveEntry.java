package com.shinybunny.mcfapi.scoreboard;

public interface ObjectiveEntry extends ObjectiveEntries {

    default void queryScore(Objective objective) {
        objective.queryScore(this);
    }

    default Score getScore(Objective of) {
        return of.getScore(this);
    }

    default void swapScores(Objective objective, ObjectiveEntry with, Objective srcObj) {
        operateScore(objective,Operator.SWAP,with,srcObj);
    }
}
