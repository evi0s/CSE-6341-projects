package com.evi0s.cse6341.p3.ast.expr.impl;

import com.evi0s.cse6341.p3.ast.expr.Expr;
import com.evi0s.cse6341.p3.misc.Location;

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

    @Override
    public void evaluate() {
        this.expr.evaluate();

        if (this.expr.evaluatedValue instanceof Long) {
            this.evaluatedValue = -((Long) this.expr.evaluatedValue);
        } else if (this.expr.evaluatedValue instanceof Double) {
            this.evaluatedValue = -((Double) this.expr.evaluatedValue);
        }
    }
}
