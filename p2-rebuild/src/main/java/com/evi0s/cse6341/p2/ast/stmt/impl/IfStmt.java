package com.evi0s.cse6341.p2.ast.stmt.impl;


import com.evi0s.cse6341.p2.ast.expr.CondExpr;
import com.evi0s.cse6341.p2.ast.stmt.Stmt;
import com.evi0s.cse6341.p2.misc.IdentMap;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

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

    @Override
    public void check(IdentMap table) {

    }

    public void print(PrintStream ps) {
        print(ps, "");
    }
}
