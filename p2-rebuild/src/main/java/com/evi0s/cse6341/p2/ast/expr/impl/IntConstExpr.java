package com.evi0s.cse6341.p2.ast.expr.impl;


import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.IdentType;

import java.io.PrintStream;

public class IntConstExpr extends Expr {
    public final Long ival;

    public IntConstExpr(Long i, Location loc) {
        super(loc);
        ival = i;
    }

    public void print(PrintStream ps) {
        ps.print(ival);
    }

    @Override
    public void check() {
        this.identType = IdentType.TYPE_INT;
    }
}
