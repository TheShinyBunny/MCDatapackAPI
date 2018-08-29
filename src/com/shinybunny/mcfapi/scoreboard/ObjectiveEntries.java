package com.shinybunny.mcfapi.scoreboard;

import com.shinybunny.mcfapi.DatapackManager;

public interface ObjectiveEntries {

    default void resetScores() {
        DatapackManager.writeCommand("scoreboard objectives reset " + this);
    }

    default void setScore(Objective objective, int value) {
        objective.setScore(this,value);
    }

    default void addScore(Objective objective, int value) {
        objective.addScore(this,value);
    }

    default void removeScore(Objective objective, int value) {
        objective.removeScore(this,value);
    }

    default void resetScore(Objective objective) {
        objective.resetScore(this);
    }

    default void enableTrigger(Objective objective) {
        objective.enableTrigger(this);
    }

    default void addScore(Objective objective, ObjectiveEntry src, Objective srcObj) {
        operateScore(objective,Operator.ADD,src,srcObj);
    }

    default void subtractScore(Objective objective, ObjectiveEntry src, Objective srcObj) {
        operateScore(objective,Operator.SUBTRACT,src,srcObj);
    }

    default void multiplyScore(Objective objective, ObjectiveEntry src, Objective srcObj) {
        operateScore(objective,Operator.MULTIPLY,src,srcObj);
    }

    default void divideScore(Objective objective, ObjectiveEntry src, Objective srcObj) {
        operateScore(objective,Operator.DIVIDE,src,srcObj);
    }

    default void moduloScore(Objective objective, ObjectiveEntry src, Objective srcObj) {
        operateScore(objective,Operator.MODULO,src,srcObj);
    }

    default void setScore(Objective objective, ObjectiveEntry src, Objective srcObj) {
        operateScore(objective,Operator.ASSIGN,src,srcObj);
    }

    default void setScoreIfLess(Objective objective, ObjectiveEntry src, Objective srcObj) {
        operateScore(objective,Operator.MINIMUM,src,srcObj);
    }

    default void setScoreIfGreater(Objective objective, ObjectiveEntry src, Objective srcObj) {
        operateScore(objective,Operator.MAXIMUM,src,srcObj);
    }

    default void operateScore(Objective objective, Operator operator, ObjectiveEntry src, Objective srcObjective) {
        objective.operateScore(this,operator,src,srcObjective);
    }

}
