package com.evi0s.cse6341.p3.ast.impl;

import com.evi0s.cse6341.p3.ast.ASTNode;
import com.evi0s.cse6341.p3.misc.Location;

import java.io.PrintStream;

public class Program extends ASTNode {
    public final UnitList unitList;
    public Program(UnitList ul, Location loc) {
        super(loc);
        unitList = ul;
    }
    public void print(PrintStream ps) {
        unitList.print(ps);
    }

    @Override
    public void check() {
        this.unitList.check();
    }

    @Override
    public void evaluate() {
        this.unitList.evaluate();
    }
}
