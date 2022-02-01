package com.evi0s.cse6341.p1.ast.expr.impl;


import com.evi0s.cse6341.p1.ast.expr.Expr;
import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

import java.io.PrintStream;
import java.util.Map;

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
    public void check(Map<String, Type> table) {
        this.type = Type.TYPE_INT;
    }
}
