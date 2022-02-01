package com.evi0s.cse6341.p1.ast.decl;


import com.evi0s.cse6341.p1.ast.ASTNode;
import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public abstract class VarDecl extends ASTNode {
    public final String ident;
    public Type type;

    public VarDecl(String i, Location loc) {
        super(loc);
        ident = i;
    }

    public abstract void print(PrintStream ps);

    public abstract void check(Map<String, Type> declTable);

    public abstract Type getType();
}
