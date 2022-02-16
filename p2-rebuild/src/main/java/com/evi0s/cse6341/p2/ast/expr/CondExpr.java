package com.evi0s.cse6341.p2.ast.expr;

import com.evi0s.cse6341.p2.ast.ASTNode;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.IdentType;

public abstract class CondExpr extends ASTNode {
    public IdentType identType;
    public CondExpr(Location loc) {
        super(loc);
    }
}
