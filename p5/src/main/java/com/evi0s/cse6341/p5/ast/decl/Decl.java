package com.evi0s.cse6341.p5.ast.decl;


import java.io.PrintStream;

import com.evi0s.cse6341.p5.ast.expr.Expr;
import com.evi0s.cse6341.p5.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p5.errors.TypeMismatchError;
import com.evi0s.cse6341.p5.ast.Unit;
import com.evi0s.cse6341.p5.datastructures.IdentMap;
import com.evi0s.cse6341.p5.misc.Location;
import com.evi0s.cse6341.p5.datastructures.ScopeStack;
import com.evi0s.cse6341.p5.datastructures.ScopeTag;
import com.evi0s.cse6341.p5.types.AbstractIdentType;


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
        ScopeTag scopeTag = ScopeStack.getInstance().getCurrentScopeTag();

        // check if the variable is already declared
        if (currentScopeIdentMap.containsKey(this.varDecl.ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.varDecl.ident, scopeTag, this.loc);
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

    @Override
    public void evaluate() {
        // add the identifier to the table
        this.varDecl.evaluate();

        // evaluate initial value for the identifier
        if (this.expr != null) {
            this.expr.evaluate();
            ScopeStack.getInstance().putNewIdentValueByName(this.varDecl.ident, this.expr.evaluatedValue);
            return;
        }

        ScopeStack.getInstance().putNewIdentValueByName(this.varDecl.ident, null);
    }

    @Override
    public void abstractEvaluate() {
        this.varDecl.abstractEvaluate();

        if (this.expr != null) {
            this.expr.abstractEvaluate();
            ScopeStack.getInstance().putNewIdentAbstractTypeByName(this.varDecl.ident, this.expr.evaluatedAbstractType);
            return;
        }

        ScopeStack.getInstance().putNewIdentAbstractTypeByName(this.varDecl.ident, AbstractIdentType.TYPE_UNDEFINED);
    }
}
