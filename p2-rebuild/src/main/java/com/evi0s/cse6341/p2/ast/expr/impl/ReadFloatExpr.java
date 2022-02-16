package com.evi0s.cse6341.p2.ast.expr.impl;

import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

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
       this.type = Type.TYPE_FLOAT;
    }
}
