package com.evi0s.cse6341.p4.ast.stmt;

import com.evi0s.cse6341.p4.ast.Unit;
import com.evi0s.cse6341.p4.types.BlockType;
import com.evi0s.cse6341.p4.misc.Location;

import java.io.PrintStream;

public abstract class Stmt extends Unit {
    public BlockType blockType = BlockType.TYPE_PLAIN;
    public Stmt(Location loc) {
        super(loc);
    }

    public abstract void print(PrintStream ps);
}
