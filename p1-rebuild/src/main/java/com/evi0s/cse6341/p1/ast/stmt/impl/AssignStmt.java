package com.evi0s.cse6341.p1.ast.stmt.impl;


import com.evi0s.cse6341.p1.ast.expr.Expr;
import com.evi0s.cse6341.p1.ast.stmt.Stmt;
import com.evi0s.cse6341.p1.errors.TypeMismatchError;
import com.evi0s.cse6341.p1.errors.UndefinedIdentError;
import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

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
    public void check(Map<String, Type> identTable) throws UndefinedIdentError, TypeMismatchError {
        this.expr.check(identTable);

        Type type = identTable.get(this.ident);
        if (type == null) {
            throw new UndefinedIdentError(this.TAG, this.ident, this.loc);
        } else {
            if (!type.equals(expr.type)) {
                throw new TypeMismatchError(this.TAG, this.ident, type, expr.type, this.loc);
            }
        }
    }
}
