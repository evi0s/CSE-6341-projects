package com.evi0s.cse6341.p1.ast.stmt.impl;


import com.evi0s.cse6341.p1.ast.expr.Expr;
import com.evi0s.cse6341.p1.ast.stmt.Stmt;
import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public class PrintStmt extends Stmt {
    public final Expr expr;

    public PrintStmt(Expr e, Location loc) {
        super(loc);
        expr = e;
    }

    public void print(PrintStream ps) {
        ps.print("print ");
        expr.print(ps);
        ps.print(";");
    }

    @Override
    public void check(Map<String, Type> identTable) {
        this.expr.check(identTable);
    }
}
