package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.Range;
import com.shinybunny.mcfapi.blocks.Block;
import com.shinybunny.mcfapi.entity.EntityList;
import com.shinybunny.mcfapi.scoreboard.Objective;
import com.shinybunny.mcfapi.scoreboard.ObjectiveEntry;

public class ExecuteIf extends ExecuteConditioned {
    public ExecuteIf(Position pos, Block block) {
        super(pos, block);
    }

    public ExecuteIf(Position pos, Position end, Position destination, boolean masked) {
        super(pos, end, destination, masked);
    }

    public ExecuteIf(EntityList entities) {
        super(entities);
    }

    public ExecuteIf(ObjectiveEntry target, Objective targetObjective, ScoreOperator operator, ObjectiveEntry source, Objective sourceObjective) {
        super(target, targetObjective, operator, source, sourceObjective);
    }

    public ExecuteIf(ObjectiveEntry target, Objective targetObjective, Range matches) {
        super(target, targetObjective, matches);
    }

    @Override
    public String getName() {
        return "if";
    }
}
