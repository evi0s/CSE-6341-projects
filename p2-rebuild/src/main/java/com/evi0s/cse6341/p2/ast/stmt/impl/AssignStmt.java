package com.evi0s.cse6341.p2.ast.stmt.impl;


import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.ast.stmt.Stmt;
import com.evi0s.cse6341.p2.errors.TypeMismatchError;
import com.evi0s.cse6341.p2.errors.UndefinedIdentError;
import com.evi0s.cse6341.p2.misc.*;

import java.io.PrintStream;
import java.util.Map;

public class AssignStmt extends Stmt {

    public final String TAG = "AssignStmt";

    public final String ident;
    public final Expr expr;

    public AssignStmt(String i, Expr e, Location loc) {
        super(loc);
        ident = i;
        expr = e;
    }

    public void print(PrintStream ps) {
        ps.print(ident + " = ");
        expr.print(ps);
        ps.print(";");
    }

    @Override
    public void print(PrintStream ps, String indent) {
        ps.print(indent + ident + " = ");
        expr.print(ps);
        ps.print(";");
    }

    @Override
    public void check() throws UndefinedIdentError, TypeMismatchError {
        this.expr.check();

        Pair<String, Type> info = ScopeStack.getInstance().findIdentByName(this.ident);
        if (info == null) {
            throw new UndefinedIdentError(this.TAG, this.ident, this.loc);
        } else {
            if (!info.second.equals(expr.type)) {
                throw new TypeMismatchError(this.TAG, this.ident, info.second, expr.type, this.loc);
            }
        }
    }
}
