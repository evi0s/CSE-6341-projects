package com.evi0s.cse6341.p2.ast.expr.impl;

import com.evi0s.cse6341.p2.ast.expr.CondExpr;
import com.evi0s.cse6341.p2.errors.TypeMismatchError;
import com.evi0s.cse6341.p2.misc.Location;

import java.io.PrintStream;

public class LogicalExpr extends CondExpr {

    private final String TAG = "LogicalExpr";

    public static final int AND = 1;
    public static final int OR = 2;
    public static final int NOT = 3;
    public final CondExpr expr1, expr2;
    public final int op;

    public LogicalExpr(CondExpr e1, int oper, CondExpr e2, Location loc) {
        super(loc);
        expr1 = e1;
        expr2 = e2;
        op = oper;
    }

    public LogicalExpr(CondExpr e1, int oper, Location loc) {
        this(e1, oper, null, loc); // used for NOT
    }

    public void print(PrintStream ps) {
        if (op == NOT) {
            ps.print("!(");
            expr1.print(ps);
            ps.print(")");
            return;
        }
        ps.print("(");
        expr1.print(ps);
        switch (op) {
            case AND:
                ps.print("&&");
                break;
            case OR:
                ps.print("||");
                break;
        }
        expr2.print(ps);
        ps.print(")");
    }

    @Override
    public void check() {
        expr1.check();
        expr2.check();

        if (expr1.identType != expr2.identType) {
            throw new TypeMismatchError(this.TAG, expr1.identType, expr2.identType, this.loc);
        }

        this.identType = expr1.identType;
    }
}
