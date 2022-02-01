package com.evi0s.cse6341.p1.ast.impl;

import com.evi0s.cse6341.p1.ast.ASTNode;
import com.evi0s.cse6341.p1.ast.Unit;
import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

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

    @Override
    public void check(Map<String, Type> identTable) {
        this.unit.check(identTable);
        if (this.unitList != null) {
            this.unitList.check(identTable);
        }
    }
}
