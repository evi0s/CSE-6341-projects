package com.evi0s.cse6341.p3.ast.expr.impl;


import com.evi0s.cse6341.p3.ast.expr.Expr;
import com.evi0s.cse6341.p3.errors.InterpreterRuntimeError;
import com.evi0s.cse6341.p3.errors.UndefinedIdentError;
import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.datastructures.ScopeStack;

import java.io.PrintStream;

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
    public void check() throws UndefinedIdentError {
        if (ScopeStack.getInstance().findIdentByName(this.ident) == null) {
            throw new UndefinedIdentError(this.TAG, this.ident, this.loc);
        }
        this.identType = ScopeStack.getInstance().findIdentByName(this.ident).identType;
    }

    @Override
    public void evaluate() throws InterpreterRuntimeError {
        Number value = ScopeStack.getInstance().getIdentValueByName(this.ident);
        if (value == null) {
            throw new InterpreterRuntimeError(this.TAG, InterpreterRuntimeError.ErrorType.UNINITIALIZED_VAR,
                    "evaluation on uninitialized variable: `" + this.ident + "'.", this.loc);
        }

        this.evaluatedValue = value;
    }
}
