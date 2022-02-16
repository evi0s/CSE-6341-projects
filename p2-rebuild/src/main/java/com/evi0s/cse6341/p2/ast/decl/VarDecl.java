package com.evi0s.cse6341.p2.ast.decl;


import com.evi0s.cse6341.p2.ast.ASTNode;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.IdentType;

import java.io.PrintStream;

public abstract class VarDecl extends ASTNode {
    public final String ident;
    public IdentType identType;

    public VarDecl(String i, Location loc) {
        super(loc);
        ident = i;
    }

    public abstract void print(PrintStream ps);

    public abstract void check();

    public abstract IdentType getType();
}
