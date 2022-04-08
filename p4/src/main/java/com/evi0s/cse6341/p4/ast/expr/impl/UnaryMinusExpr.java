package com.evi0s.cse6341.p4.ast.expr.impl;

import com.evi0s.cse6341.p4.ast.expr.Expr;
import com.evi0s.cse6341.p4.misc.Location;
import com.evi0s.cse6341.p4.types.AbstractIdentType;

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

    @Override
    public void abstractEvaluate() {
        expr.abstractEvaluate();

        switch (expr.evaluatedAbstractType) {
            case TYPE_ANY_FLOAT, TYPE_ANY_INT ->
                    this.evaluatedAbstractType = expr.evaluatedAbstractType;
            case TYPE_NEG ->
                    this.evaluatedAbstractType = AbstractIdentType.TYPE_POS;
            case TYPE_POS ->
                    this.evaluatedAbstractType = AbstractIdentType.TYPE_NEG;
            case TYPE_ZERO ->
                    this.evaluatedAbstractType = AbstractIdentType.TYPE_ZERO;
        }
    }
}
