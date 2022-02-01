package com.evi0s.cse6341.p1.ast;

import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public abstract class Unit extends ASTNode {
    public Unit(Location loc) {
        super(loc);
    }

    public abstract void print(PrintStream ps);
    public abstract void check(Map<String, Type> table);
}
