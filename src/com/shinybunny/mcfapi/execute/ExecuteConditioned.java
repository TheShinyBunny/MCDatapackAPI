package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.Range;
import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.entity.EntityList;
import com.shinybunny.mcfapi.scoreboard.Objective;
import com.shinybunny.mcfapi.scoreboard.ObjectiveEntry;

public abstract class ExecuteConditioned extends ExecutePart {
    private Block block;
    private Position pos;
    private Position end;
    private Position destination;
    private boolean masked;
    private EntityList entities;
    private ObjectiveEntry target;
    private Objective targetObjective;
    private ScoreOperator operator;
    private ObjectiveEntry source;
    private Objective sourceObjective;
    private Range matches;

    public ExecuteConditioned(Position pos, Block block) {
        this.pos = pos;
        this.block = block;
    }

    public ExecuteConditioned(Position pos, Position end, Position destination, boolean masked) {
        this.pos = pos;
        this.end = end;
        this.destination = destination;
        this.masked = masked;
    }

    public ExecuteConditioned(EntityList entities) {
        this.entities = entities;
    }

    public ExecuteConditioned(ObjectiveEntry target, Objective targetObjective, ScoreOperator operator, ObjectiveEntry source, Objective sourceObjective) {
        this.target = target;
        this.targetObjective = targetObjective;
        this.operator = operator;
        this.source = source;
        this.sourceObjective = sourceObjective;
    }

    public ExecuteConditioned(ObjectiveEntry target, Objective targetObjective, Range matches) {
        this.target = target;
        this.targetObjective = targetObjective;
        this.matches = matches;
    }

    @Override
    public String buildSyntax() {
        if (block != null) {
            return "block " + pos + " " + block;
        }
        if (end != null) {
            return "blocks " + pos + " " + end + " " + destination + " " + (masked ? "masked" : "all");
        }
        if (entities != null) {
            return "entity " + entities;
        }
        if (operator != null) {
            return "score " + target + " " + targetObjective + " " + operator.getSign() + " " + source + " " + sourceObjective;
        }
        return "score " + target + " " + targetObjective + " matches " + matches;
    }

    public static class Builder {

        private final boolean cond;
        private final Execute execute;

        public Builder(Execute execute, boolean cond) {
            this.execute = execute;
            this.cond = cond;
        }

        public Execute block(Position pos, Block block) {
            return execute.then(cond ? new ExecuteIf(pos,block) : new ExecuteUnless(pos,block));
        }

        public Execute blocks(Position begin, Position end, Position dest, boolean masked) {
            return execute.then(cond ? new ExecuteIf(begin,end,dest,masked) : new ExecuteUnless(begin,end,dest,masked));
        }

        public Execute entity(EntityList entities) {
            return execute.then(cond ? new ExecuteIf(entities) : new ExecuteUnless(entities));
        }

        public Execute score(ObjectiveEntry target, Objective targetObj, ScoreOperator operator, ObjectiveEntry src, Objective srcObj) {
            return execute.then(cond ? new ExecuteIf(target,targetObj,operator,src,srcObj) : new ExecuteUnless(target,targetObj,operator,src,srcObj));
        }

        public Execute scoreMatches(ObjectiveEntry target, Objective targetObj, Range range) {
            return execute.then(cond ? new ExecuteIf(target,targetObj,range) : new ExecuteUnless(target,targetObj,range));
        }
    }
}
