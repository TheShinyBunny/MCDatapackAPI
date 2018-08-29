package com.shinybunny.mcfapi.execute;

import com.shinybunny.mcfapi.Position;
import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Execute {

    private List<ExecutePart> parts;

    public Execute() {
        this.parts = new ArrayList<>();
    }

    public Execute as(EntityList entities) {
        return then(new ExecuteAs(entities));
    }

    Execute then(ExecutePart part) {
        parts.add(part);
        return this;
    }

    public Execute at(EntityList entity) {
        return then(new ExecuteAt(entity));
    }

    public Execute atSelf() {
        return then(new ExecuteAt(EntityList.self));
    }

    public Execute positioned(Position pos) {
        return then(new ExecutePositioned(pos));
    }

    public Execute positionedAs(SingleEntity entity) {
        return then(new ExecutePositioned(entity));
    }

    public Execute align(boolean x, boolean y, boolean z) {
        return then(new ExecuteAlign(x,y,z));
    }

    public Execute facing(Position pos) {
        return then(new ExecuteFacing(pos));
    }

    public Execute facing(SingleEntity entity, Anchor anchor) {
        return then(new ExecuteFacing(entity,anchor));
    }

    public Execute rotated(Rotation rot) {
        return then(new ExecuteRotated(rot));
    }

    public Execute rotatedAs(SingleEntity entity) {
        return then(new ExecuteRotated(entity));
    }

    public Execute in(Dimension dim) {
        return then(new ExecuteIn(dim));
    }

    public Execute anchored(Anchor anchor) {
        return then(new ExecuteAnchored(anchor));
    }

    public ExecuteConditioned.Builder iF() {
        return new ExecuteConditioned.Builder(this,true);
    }

    public ExecuteConditioned.Builder unless() {
        return new ExecuteConditioned.Builder(this,false);
    }

    public ExecuteStore.Builder storeResult() {
        return new ExecuteStore.Builder(this,true);
    }

    public ExecuteStore.Builder storeSuccess() {
        return new ExecuteStore.Builder(this,false);
    }

    public void run(Runnable function) {
        DatapackManager.getFunctionWriter().setCurrentExecute(this);
        function.run();
        DatapackManager.getFunctionWriter().setCurrentExecute(null);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("execute");
        for (ExecutePart part : parts) {
            b.append(" ");
            b.append(part.getName());
            b.append(" ");
            b.append(part.buildSyntax());
        }
        return b.toString();
    }
}
