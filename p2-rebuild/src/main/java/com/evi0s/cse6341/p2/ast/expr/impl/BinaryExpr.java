package com.evi0s.cse6341.p2.ast.expr.impl;

import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.errors.TypeMismatchError;
import com.evi0s.cse6341.p2.misc.Location;

import java.io.PrintStream;

public class BinaryExpr extends Expr {

    private final String TAG = "BinaryExpr";

    public static final int PLUS = 1;
    public static final int MINUS = 2;
    public static final int TIMES = 3;
    public static final int DIV = 4;
    public final Expr expr1, expr2;
    public final int op;

    public BinaryExpr(Expr e1, int oper, Expr e2, Location loc) {
        super(loc);
        expr1 = e1;
        expr2 = e2;
        op = oper;
    }

    public String getOperationString (int operation) {
        return switch (operation) {
            case PLUS -> "+";
            case MINUS -> "-";
            case TIMES -> "*";
            case DIV -> "/";
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
            throw new TypeMismatchError(this.TAG, expr1.type, expr2.type, this.loc);
        }

        this.type = expr1.type;
    }
}
