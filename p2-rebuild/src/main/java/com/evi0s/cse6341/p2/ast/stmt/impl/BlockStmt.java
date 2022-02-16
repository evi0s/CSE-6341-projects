package com.evi0s.cse6341.p2.ast.stmt.impl;

import com.evi0s.cse6341.p2.ast.impl.UnitList;
import com.evi0s.cse6341.p2.ast.stmt.Stmt;
import com.evi0s.cse6341.p2.misc.IdentMap;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public class BlockStmt extends Stmt {
    public final UnitList block;
    public BlockStmt(UnitList b, Location loc) {
        super(loc);
        block = b;
    }
    public void print(PrintStream ps, String indent) {
        ps.print(indent + "{\n");
        block.print(ps, indent + "  ");
        ps.print(indent + "}");
    }
    public void print(PrintStream ps) {
        print(ps,"");
    }

    @Override
    public void check(IdentMap table) {
    }
}
