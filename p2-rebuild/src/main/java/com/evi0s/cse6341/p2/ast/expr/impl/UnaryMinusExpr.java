package com.evi0s.cse6341.p2.ast.expr.impl;

import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.misc.Location;

import java.io.PrintStream;

public class UnaryMinusExpr extends Expr {
    public final Expr expr;

    public UnaryMinusExpr(Expr e, Location loc) {
        super(loc);
        expr = e;
    }

    public void print(PrintStream ps) {
        ps.print("-(");
        expr.print(ps);
        ps.print(")");
    }

    @Override
    public void check() {
        expr.check();
        identType = expr.identType;
    }
}
