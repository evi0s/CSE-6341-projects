package com.evi0s.cse6341.p2.ast.expr.impl;

import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public class BinaryExpr extends Expr {
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

    public void print(PrintStream ps) {
        ps.print("(");
        expr1.print(ps);
        switch (op) {
            case PLUS:
                ps.print("+");
                break;
            case MINUS:
                ps.print("-");
                break;
            case TIMES:
                ps.print("*");
                break;
            case DIV:
                ps.print("/");
                break;
        }
        expr2.print(ps);
        ps.print(")");
    }

    @Override
    public void check(Map<String, Type> identTable) {

    }
}
