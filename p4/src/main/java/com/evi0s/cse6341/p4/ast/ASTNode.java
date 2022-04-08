package com.evi0s.cse6341.p4.ast;


import com.evi0s.cse6341.p4.misc.Location;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class ASTNode implements INode{
    private final String TAG = "ASTNode";
    public final Location loc;

    public ASTNode(Location loc) {
        this.loc = loc;
    }

    public abstract void print(PrintStream ps);
    public abstract void check();
    public abstract void evaluate();
    public abstract void abstractEvaluate();

    public String toString() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        print(new PrintStream(b));
        return b.toString(java.nio.charset.StandardCharsets.UTF_8);
    }
}
