package com.evi0s.cse6341.p3.ast.expr;


import com.evi0s.cse6341.p3.ast.ASTNode;
import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.misc.IdentType;

import java.io.PrintStream;

public abstract class Expr extends ASTNode {
    public IdentType identType;
    public Expr(Location loc) {
        super(loc);
        this.identType = null;
    }

    public abstract void print(PrintStream ps);
    public abstract void check();
    public abstract void evaluate();
}
