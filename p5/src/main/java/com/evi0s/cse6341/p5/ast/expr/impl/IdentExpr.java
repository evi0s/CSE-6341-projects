package com.evi0s.cse6341.p5.ast.expr.impl;


import com.evi0s.cse6341.p5.ast.expr.Expr;
import com.evi0s.cse6341.p5.errors.AbstractEvaluationError;
import com.evi0s.cse6341.p5.errors.InterpreterRuntimeError;
import com.evi0s.cse6341.p5.errors.UndefinedIdentError;
import com.evi0s.cse6341.p5.misc.Location;
import com.evi0s.cse6341.p5.datastructures.ScopeStack;
import com.evi0s.cse6341.p5.types.AbstractIdentType;

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

    @Override
    public void abstractEvaluate() throws AbstractEvaluationError {
        AbstractIdentType abstractValue = ScopeStack.getInstance().getIdentAbstractTypeByName(this.ident);
        if (AbstractIdentType.TYPE_UNDEFINED.equals(abstractValue)) {
            throw new AbstractEvaluationError(this.TAG, AbstractEvaluationError.ErrorType.UNINITIALIZED_VAR,
                    "abstraction evaluation on uninitialized variable: `" + this.ident + "'.", this.loc);
        }

        this.evaluatedAbstractType = abstractValue;
    }
}
