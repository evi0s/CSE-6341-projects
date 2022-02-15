package com.evi0s.cse6341.p2.ast.expr.impl;

import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public class ReadIntExpr extends Expr {
    public ReadIntExpr(Location loc) {
        super(loc);
    }

    public void print(PrintStream ps) {
        ps.print("readint");
    }

    @Override
    public void check(Map<String, Type> identTable) {

    }
}
