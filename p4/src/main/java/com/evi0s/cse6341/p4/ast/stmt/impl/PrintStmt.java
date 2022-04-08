package com.evi0s.cse6341.p4.ast.stmt.impl;


import com.evi0s.cse6341.p4.ast.expr.Expr;
import com.evi0s.cse6341.p4.ast.stmt.Stmt;
import com.evi0s.cse6341.p4.misc.Location;
import com.evi0s.cse6341.p4.utils.OutputPrinter;

import java.io.PrintStream;

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
    public void print(PrintStream ps, String indent) {
        ps.print(indent + "print ");
        expr.print(ps);
        ps.print(";");
    }

    @Override
    public void check() {
        this.expr.check();
    }

    @Override
    public void evaluate() {
        this.expr.evaluate();
        OutputPrinter.print(String.valueOf(this.expr.getEvaluatedValue()));
    }

    @Override
    public void abstractEvaluate() {
        this.expr.abstractEvaluate();
        OutputPrinter.print(this.expr.getEvaluatedAbstractType().toString());
    }
}
