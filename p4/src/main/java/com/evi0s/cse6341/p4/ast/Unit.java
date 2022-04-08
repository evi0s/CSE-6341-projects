package com.evi0s.cse6341.p4.ast;

import com.evi0s.cse6341.p4.misc.Location;

import java.io.PrintStream;

public abstract class Unit extends ASTNode {
    public Unit(Location loc) {
        super(loc);
    }

    public abstract void print(PrintStream ps);
    public abstract void print(PrintStream ps, String ident);
    public abstract void check();
    public abstract void evaluate();
    public abstract void abstractEvaluate();
}
