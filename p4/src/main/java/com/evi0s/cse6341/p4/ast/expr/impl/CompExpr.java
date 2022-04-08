package com.evi0s.cse6341.p4.ast.expr.impl;

import com.evi0s.cse6341.p4.ast.expr.CondExpr;
import com.evi0s.cse6341.p4.ast.expr.Expr;
import com.evi0s.cse6341.p4.errors.TypeMismatchError;
import com.evi0s.cse6341.p4.types.IdentType;
import com.evi0s.cse6341.p4.misc.Location;

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

        if (expr1.identType != expr2.identType) {
            throw new TypeMismatchError(this.TAG, expr1.identType, expr2.identType, loc);
        }

        this.identType = IdentType.TYPE_BOOLEAN;
    }

    @Override
    public void evaluate() {
        expr1.evaluate();
        expr2.evaluate();

        boolean isDouble = expr1.evaluatedValue instanceof Double && expr2.evaluatedValue instanceof Double;
        boolean isLong   = expr1.evaluatedValue instanceof Long   && expr2.evaluatedValue instanceof Long;

        switch (op) {
            case GE -> {
                if (isLong) {
                    this.evaluatedValue = (Long) expr1.evaluatedValue >= (Long) expr2.evaluatedValue;
                } else if (isDouble) {
                    this.evaluatedValue = (Double) expr1.evaluatedValue >= (Double) expr2.evaluatedValue;
                }
            }
            case GT -> {
                if (isLong) {
                    this.evaluatedValue = (Long) expr1.evaluatedValue > (Long) expr2.evaluatedValue;
                } else if (isDouble) {
                    this.evaluatedValue = (Double) expr1.evaluatedValue > (Double) expr2.evaluatedValue;
                }
            }
            case LE -> {
                if (isLong) {
                    this.evaluatedValue = (Long) expr1.evaluatedValue <= (Long) expr2.evaluatedValue;
                } else if (isDouble) {
                    this.evaluatedValue = (Double) expr1.evaluatedValue <= (Double) expr2.evaluatedValue;
                }
            }
            case LT -> {
                if (isLong) {
                    this.evaluatedValue = (Long) expr1.evaluatedValue < (Long) expr2.evaluatedValue;
                } else if (isDouble) {
                    this.evaluatedValue = (Double) expr1.evaluatedValue < (Double) expr2.evaluatedValue;
                }
            }
            case EQ -> this.evaluatedValue = expr1.evaluatedValue.equals(expr2.evaluatedValue);
            case NE -> this.evaluatedValue = !expr1.evaluatedValue.equals(expr2.evaluatedValue);
        }
    }

    @Override
    public void abstractEvaluate() {
        expr1.abstractEvaluate();
        expr2.abstractEvaluate();

        // do nothing
    }
}
