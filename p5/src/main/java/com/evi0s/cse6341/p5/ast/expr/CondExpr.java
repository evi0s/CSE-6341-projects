package com.evi0s.cse6341.p5.ast.expr;

import com.evi0s.cse6341.p5.ast.ASTNode;
import com.evi0s.cse6341.p5.misc.Location;
import com.evi0s.cse6341.p5.types.AbstractIdentType;
import com.evi0s.cse6341.p5.types.IdentType;

public abstract class CondExpr extends ASTNode {
    public IdentType identType;
    public Boolean evaluatedValue;
    public AbstractIdentType evaluatedAbstractType;
    public CondExpr(Location loc) {
        super(loc);
        this.evaluatedValue = null;
    }

    public abstract void abstractEvaluate();

    public AbstractIdentType getEvaluatedAbstractType() {
        return this.evaluatedAbstractType;
    }
}
