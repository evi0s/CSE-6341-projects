package com.evi0s.cse6341.p3.ast.stmt.impl;


import com.evi0s.cse6341.p3.ast.expr.CondExpr;
import com.evi0s.cse6341.p3.ast.stmt.Stmt;
import com.evi0s.cse6341.p3.types.BlockType;
import com.evi0s.cse6341.p3.misc.Location;

import java.io.PrintStream;

public class IfStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt thenstmt, elsestmt;

    public IfStmt(CondExpr e, Stmt s, Location loc) {
        super(loc);
        expr = e;
        thenstmt = s;
        elsestmt = null;
    }

    public IfStmt(CondExpr e, Stmt s1, Stmt s2, Location loc) {
        super(loc);
        expr = e;
        thenstmt = s1;
        elsestmt = s2;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + "if (");
        expr.print(ps);
        ps.print(")\n");
        thenstmt.print(ps, indent + "  ");
        if (elsestmt != null) {
            ps.print("\n" + indent + "else\n");
            elsestmt.print(ps, indent + "  ");
        }
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }

    @Override
    public void check() {
        expr.check();

        // check then stmt
        thenstmt.blockType = BlockType.TYPE_IF_THEN;
        thenstmt.check();

        // if else stmt exists, check it
        if (elsestmt != null) {
            elsestmt.blockType = BlockType.TYPE_IF_ELSE;
            elsestmt.check();
        }
    }

    @Override
    public void evaluate() {
        expr.evaluate();

        if (expr.evaluatedValue) {
            thenstmt.evaluate();
        } else if (elsestmt != null) {
            elsestmt.evaluate();
        }
    }
}
