package com.evi0s.cse6341.p2.ast.expr;


import com.evi0s.cse6341.p2.ast.ASTNode;
import com.evi0s.cse6341.p2.misc.IdentMap;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public abstract class Expr extends ASTNode {
    public Type type;
    public Expr(Location loc) {
        super(loc);
        this.type = null;
    }

    public abstract void print(PrintStream ps);
    public abstract void check(IdentMap identTable);
}
