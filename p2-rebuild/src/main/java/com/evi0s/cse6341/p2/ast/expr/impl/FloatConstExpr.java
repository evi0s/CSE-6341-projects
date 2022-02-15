package com.evi0s.cse6341.p2.ast.expr.impl;

import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public class FloatConstExpr extends Expr {
    public final Double fval;

    public FloatConstExpr(Double f, Location loc) {
        super(loc);
        fval = f;
    }

    public void print(PrintStream ps) {
        ps.print(fval);
    }

    @Override
    public void check(Map<String, Type> table) {
        this.type = Type.TYPE_FLOAT;
    }
}
