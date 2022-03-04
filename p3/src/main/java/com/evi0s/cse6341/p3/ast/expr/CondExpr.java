package com.evi0s.cse6341.p3.ast.expr;

import com.evi0s.cse6341.p3.ast.ASTNode;
import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.types.IdentType;

public abstract class CondExpr extends ASTNode {
    public IdentType identType;
    public Boolean evaluatedValue;
    public CondExpr(Location loc) {
        super(loc);
        this.evaluatedValue = null;
    }
}
