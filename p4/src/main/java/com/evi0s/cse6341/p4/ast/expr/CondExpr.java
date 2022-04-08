package com.evi0s.cse6341.p4.ast.expr;

import com.evi0s.cse6341.p4.ast.ASTNode;
import com.evi0s.cse6341.p4.misc.Location;
import com.evi0s.cse6341.p4.types.IdentType;

public abstract class CondExpr extends ASTNode {
    public IdentType identType;
    public Boolean evaluatedValue;
    public CondExpr(Location loc) {
        super(loc);
        this.evaluatedValue = null;
    }
}
