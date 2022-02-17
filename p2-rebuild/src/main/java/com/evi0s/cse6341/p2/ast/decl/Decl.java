package com.evi0s.cse6341.p2.ast.decl;


import java.io.PrintStream;

import com.evi0s.cse6341.p2.ast.expr.Expr;
import com.evi0s.cse6341.p2.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p2.errors.TypeMismatchError;
import com.evi0s.cse6341.p2.ast.Unit;
import com.evi0s.cse6341.p2.misc.IdentMap;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.ScopeStack;


public class Decl extends Unit {

    private final String TAG = "Decl";

    public final VarDecl varDecl;
    public final Expr expr;

    public Decl(VarDecl d, Location loc) {
        super(loc);
        varDecl = d;
        expr = null;
    }

    public Decl(VarDecl d, Expr e, Location loc) {
        super(loc);
        varDecl = d;
        expr = e;
    }

    public void print(PrintStream ps) {
        varDecl.print(ps);
        if (expr != null) {
            ps.print(" = ");
            expr.print(ps);
        }
        ps.print(";");
    }

    @Override
    public void print(PrintStream ps, String indent) {
        ps.print(indent);
        varDecl.print(ps);
        if (expr != null) {
            ps.print(" = ");
            expr.print(ps);
        }
        ps.print(";");
    }

    @Override
    public void check() throws DuplicateVarDeclarationError, TypeMismatchError {
        IdentMap currentScopeIdentMap = ScopeStack.getInstance().getCurrentScopeIdentMap();

        // check if the variable is already declared
        if (currentScopeIdentMap.containsKey(this.varDecl.ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.varDecl.ident, currentScopeIdentMap.scopeTag, this.loc);
        }

        if (this.expr != null) {
            // check if the assigned expression has undefined variables
            this.expr.check();

            // type check
            if (!this.varDecl.getType().equals(this.expr.identType)) {
                throw new TypeMismatchError(this.TAG, this.varDecl.ident, this.varDecl.getType(), this.expr.identType, this.loc);
            }
        }

        // add the identifier to the table
        this.varDecl.check();
    }
}
