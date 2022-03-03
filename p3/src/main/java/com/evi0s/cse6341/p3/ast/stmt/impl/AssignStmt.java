package com.evi0s.cse6341.p3.ast.stmt.impl;


import com.evi0s.cse6341.p3.ast.expr.Expr;
import com.evi0s.cse6341.p3.ast.stmt.Stmt;
import com.evi0s.cse6341.p3.errors.TypeMismatchError;
import com.evi0s.cse6341.p3.errors.UndefinedIdentError;
import com.evi0s.cse6341.p3.misc.IdentInfo;
import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.misc.ScopeStack;

import java.io.PrintStream;

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

        IdentInfo info = ScopeStack.getInstance().findIdentByName(this.ident);
        if (info == null) {
            throw new UndefinedIdentError(this.TAG, this.ident, this.loc);
        } else {
            if (!info.identType.equals(expr.identType)) {
                throw new TypeMismatchError(this.TAG, this.ident, info.scopeTag, info.identType, expr.identType, this.loc);
            }
        }
    }

    @Override
    public void evaluate() {

    }
}
