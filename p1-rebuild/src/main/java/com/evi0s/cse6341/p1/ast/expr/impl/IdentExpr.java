package com.evi0s.cse6341.p1.ast.expr.impl;


import com.evi0s.cse6341.p1.ast.expr.Expr;
import com.evi0s.cse6341.p1.errors.UndefinedIdentError;
import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public class IdentExpr extends Expr {

    private final String TAG = "IdentExpr";

    public final String ident;

    public IdentExpr(String i, Location loc) {
        super(loc);
        ident = i;
    }

    public void print(PrintStream ps) {
        ps.print(ident);
    }

    @Override
    public void check(Map<String, Type> identTable) throws UndefinedIdentError {
        if (identTable.get(this.ident) == null) {
            throw new UndefinedIdentError(this.TAG, this.ident, this.loc);
        }
        this.type = identTable.get(this.ident);
    }
}
