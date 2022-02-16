package com.evi0s.cse6341.p2.ast;


import com.evi0s.cse6341.p2.misc.IdentMap;
import com.evi0s.cse6341.p2.misc.Location;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class ASTNode implements INode{
    protected String TAG;
    public final Location loc;

    public ASTNode(Location loc) {
        this.loc = loc;
    }

    public abstract void print(PrintStream ps);
    public abstract void check(IdentMap table);

    public String toString() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        print(new PrintStream(b));
        return b.toString(java.nio.charset.StandardCharsets.UTF_8);
    }
}
