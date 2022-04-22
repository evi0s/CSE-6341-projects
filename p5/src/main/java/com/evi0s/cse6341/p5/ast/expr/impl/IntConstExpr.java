package com.evi0s.cse6341.p5.ast.expr.impl;


import com.evi0s.cse6341.p5.ast.expr.Expr;
import com.evi0s.cse6341.p5.misc.Location;
import com.evi0s.cse6341.p5.types.AbstractIdentType;
import com.evi0s.cse6341.p5.types.IdentType;

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

    @Override
    public void evaluate() {
        this.evaluatedValue = ival;
    }

    @Override
    public void abstractEvaluate() {
        if (ival > 0) {
            this.evaluatedAbstractType = AbstractIdentType.TYPE_POS;
        } else if (ival == 0) {
            this.evaluatedAbstractType = AbstractIdentType.TYPE_ZERO;
        } else {
            this.evaluatedAbstractType = AbstractIdentType.TYPE_NEG;
        }
    }
}
