package com.evi0s.cse6341.p3.ast.expr.impl;

import com.evi0s.cse6341.p3.ast.expr.Expr;
import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.misc.IdentType;

import java.io.PrintStream;

public class ReadFloatExpr extends Expr {
    public ReadFloatExpr(Location loc) {
        super(loc);
    }

    public void print(PrintStream ps) {
        ps.print("readfloat");
    }

    @Override
    public void check() {
       this.identType = IdentType.TYPE_FLOAT;
    }

    @Override
    public void evaluate() {

    }
}
