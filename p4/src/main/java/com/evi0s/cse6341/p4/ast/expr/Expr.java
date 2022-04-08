package com.evi0s.cse6341.p4.ast.expr;


import com.evi0s.cse6341.p4.ast.ASTNode;
import com.evi0s.cse6341.p4.misc.Location;
import com.evi0s.cse6341.p4.types.AbstractIdentType;
import com.evi0s.cse6341.p4.types.IdentType;

import java.io.PrintStream;

public abstract class Expr extends ASTNode {
    public IdentType identType;
    public Number evaluatedValue;
    public AbstractIdentType evaluatedAbstractType;
    public Expr(Location loc) {
        super(loc);
        this.identType = null;
        this.evaluatedValue = null;
        this.evaluatedAbstractType = AbstractIdentType.TYPE_UNDEFINED;
    }

    public abstract void print(PrintStream ps);
    public abstract void check();
    public abstract void evaluate();
    public abstract void abstractEvaluate();

    public Number getEvaluatedValue() {
        return this.evaluatedValue;
    }

    public AbstractIdentType getEvaluatedAbstractType() {
        return this.evaluatedAbstractType;
    }
}
