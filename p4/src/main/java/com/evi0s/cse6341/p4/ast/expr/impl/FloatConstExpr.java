package com.evi0s.cse6341.p4.ast.expr.impl;

import com.evi0s.cse6341.p4.ast.expr.Expr;
import com.evi0s.cse6341.p4.misc.Location;
import com.evi0s.cse6341.p4.types.AbstractIdentType;
import com.evi0s.cse6341.p4.types.IdentType;

import java.io.PrintStream;

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
    public void check() {
        this.identType = IdentType.TYPE_FLOAT;
    }

    @Override
    public void evaluate() {
        this.evaluatedValue = fval;
    }

    @Override
    public void abstractEvaluate() {
        if (fval > 0) {
            this.evaluatedAbstractType = AbstractIdentType.TYPE_POS;
        } else if (fval == 0) {
            this.evaluatedAbstractType = AbstractIdentType.TYPE_ZERO;
        } else {
            this.evaluatedAbstractType = AbstractIdentType.TYPE_NEG;
        }
    }
}
