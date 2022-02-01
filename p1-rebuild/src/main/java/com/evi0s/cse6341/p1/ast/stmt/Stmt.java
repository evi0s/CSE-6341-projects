package com.evi0s.cse6341.p1.ast.stmt;

import com.evi0s.cse6341.p1.ast.Unit;
import com.evi0s.cse6341.p1.misc.Location;

import java.io.PrintStream;

public abstract class Stmt extends Unit {
    public Stmt(Location loc) {
        super(loc);
    }

    public abstract void print(PrintStream ps);
}
