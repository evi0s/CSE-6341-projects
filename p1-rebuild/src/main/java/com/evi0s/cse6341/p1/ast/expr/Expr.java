package com.evi0s.cse6341.p1.ast.expr;


import com.evi0s.cse6341.p1.ast.ASTNode;
import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public abstract class Expr extends ASTNode {
    public Type type;
    public Expr(Location loc) {
        super(loc);
        this.type = null;
    }

    public abstract void print(PrintStream ps);
    public abstract void check(Map<String, Type> identTable);
}
