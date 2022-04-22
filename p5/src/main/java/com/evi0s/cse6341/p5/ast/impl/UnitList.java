package com.evi0s.cse6341.p5.ast.impl;

import com.evi0s.cse6341.p5.ast.ASTNode;
import com.evi0s.cse6341.p5.ast.Unit;
import com.evi0s.cse6341.p5.misc.Location;

import java.io.PrintStream;


public class UnitList extends ASTNode {
    public final Unit unit;
    public final UnitList unitList;

    public UnitList(Unit u, UnitList ul, Location loc) {
        super(loc);
        unit = u;
        unitList = ul;
    }

    public UnitList(Unit u, Location loc) {
        super(loc);
        unit = u;
        unitList = null;
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }

    public void print(PrintStream ps, String indent) {
        unit.print(ps, indent);
        ps.println();
        if (unitList != null)
            unitList.print(ps, indent);
    }

    @Override
    public void check() {
        this.unit.check();
        if (this.unitList != null) {
            this.unitList.check();
        }
    }

    @Override
    public void evaluate() {
        this.unit.evaluate();
        if (this.unitList != null) {
            this.unitList.evaluate();
        }
    }

    @Override
    public void abstractEvaluate() {
        this.unit.abstractEvaluate();
        if (this.unitList != null) {
            this.unitList.abstractEvaluate();
        }
    }
}
