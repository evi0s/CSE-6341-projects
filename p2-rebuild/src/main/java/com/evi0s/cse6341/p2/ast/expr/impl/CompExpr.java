package com.evi0s.cse6341.p2.ast.expr.impl;

import com.evi0s.cse6341.p2.ast.expr.CondExpr;
import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.errors.TypeMismatchError;
import com.evi0s.cse6341.p2.misc.Location;

import java.io.PrintStream;

public class CompExpr extends CondExpr {

    private final String TAG = "CompExpr";

    public static final int GE = 1;
    public static final int GT = 2;
    public static final int LE = 3;
    public static final int LT = 4;
    public static final int EQ = 5;
    public static final int NE = 6;
    public final Expr expr1, expr2;
    public final int op;

    public CompExpr(Expr e1, int oper, Expr e2, Location loc) {
        super(loc);
        expr1 = e1;
        expr2 = e2;
        op = oper;
    }

    public String getOperationString (int operation) {
        return switch (operation) {
            case GE -> ">=";
            case GT -> ">";
            case LE -> "<=";
            case LT -> "<";
            case EQ -> "==";
            case NE -> "!=";
            default -> "";
        };
    }

    public void print(PrintStream ps) {
        ps.print("(");
        expr1.print(ps);
        ps.print(getOperationString(op));
        expr2.print(ps);
        ps.print(")");
    }

    @Override
    public void check() throws TypeMismatchError {
        expr1.check();
        expr2.check();

        if (expr1.type != expr2.type) {
            throw new TypeMismatchError(this.TAG, expr1.type, expr2.type, loc);
        }

        this.type = expr1.type;
    }
}
