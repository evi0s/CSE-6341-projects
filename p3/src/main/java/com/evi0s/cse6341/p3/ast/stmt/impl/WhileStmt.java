package com.evi0s.cse6341.p3.ast.stmt.impl;

import com.evi0s.cse6341.p3.ast.expr.CondExpr;
import com.evi0s.cse6341.p3.ast.stmt.Stmt;
import com.evi0s.cse6341.p3.types.BlockType;
import com.evi0s.cse6341.p3.misc.Location;

import java.io.PrintStream;

public class WhileStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt body;
    public WhileStmt(CondExpr e, Stmt s, Location loc) {
        super(loc);
        expr = e;
        body = s;
    }
    public void print(PrintStream ps, String indent) {
        ps.print(indent + "while (");
        expr.print(ps);
        ps.print(")\n");
        body.print(ps, indent + "  ");
    }
    public void print(PrintStream ps) {
        print(ps,"");
    }

    @Override
    public void check() {
        expr.check();

        body.blockType = BlockType.TYPE_WHILE;
        body.check();
    }

    @Override
    public void evaluate() {
        expr.evaluate();

        while (expr.evaluatedValue) {
            body.evaluate();
            expr.evaluate();
        }
    }
}
