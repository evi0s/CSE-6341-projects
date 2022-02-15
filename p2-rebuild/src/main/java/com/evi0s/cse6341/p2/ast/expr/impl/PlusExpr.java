package com.evi0s.cse6341.p2.ast.expr.impl;


import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.errors.TypeMismatchError;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public class PlusExpr extends Expr {

    private final String TAG = "PlusExpr";

    public final Expr expr1, expr2;

    public PlusExpr(Expr e1, Expr e2, Location loc) {
        super(loc);
        expr1 = e1;
        expr2 = e2;
    }

    public void print(PrintStream ps) {
        ps.print("(");
        expr1.print(ps);
        ps.print(" + ");
        expr2.print(ps);
        ps.print(")");
    }

    @Override
    public void check(Map<String, Type> identTable) throws TypeMismatchError {
        expr1.check(identTable);
        expr2.check(identTable);

        if (!expr1.type.equals(expr2.type)) {
            throw new TypeMismatchError(this.TAG, expr1.type, expr2.type, this.loc);
        }

        type = expr1.type;
    }
}
