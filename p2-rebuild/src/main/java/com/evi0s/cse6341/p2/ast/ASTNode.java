package com.evi0s.cse6341.p2.ast;


import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public abstract class ASTNode implements INode{
    protected String TAG;
    public final Location loc;

    public ASTNode(Location loc) {
        this.loc = loc;
    }

    public abstract void print(PrintStream ps);
    public abstract void check(Map<String, Type> table);
}
