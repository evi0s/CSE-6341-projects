package com.evi0s.cse6341.p2.ast.impl;

import com.evi0s.cse6341.p2.ast.ASTNode;
import com.evi0s.cse6341.p2.ast.Unit;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;


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
        unit.print(ps);
        ps.println();
        if (unitList != null)
            unitList.print(ps);
    }

    public void print(PrintStream ps, String indent) {
        unit.print(ps, indent);
        ps.println();
        if (unitList != null)
            unitList.print(ps, indent);
    }

    @Override
    public void check(Map<String, Type> identTable) {
        this.unit.check(identTable);
        if (this.unitList != null) {
            this.unitList.check(identTable);
        }
    }
}