package com.evi0s.cse6341.p2.ast.expr;

import com.evi0s.cse6341.p2.ast.ASTNode;
import com.evi0s.cse6341.p2.misc.Location;

public abstract class CondExpr extends ASTNode {
    public CondExpr(Location loc) {
        super(loc);
    }
}
