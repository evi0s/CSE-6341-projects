package com.evi0s.cse6341.p2.ast.stmt.impl;


import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.ast.stmt.Stmt;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

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
    public void print(PrintStream ps, String ident) {

    }

    @Override
    public void check(Map<String, Type> identTable) {
        this.expr.check(identTable);
    }
}
