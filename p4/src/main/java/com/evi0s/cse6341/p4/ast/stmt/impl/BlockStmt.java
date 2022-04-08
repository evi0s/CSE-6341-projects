package com.evi0s.cse6341.p4.ast.stmt.impl;

import com.evi0s.cse6341.p4.ast.impl.UnitList;
import com.evi0s.cse6341.p4.ast.stmt.Stmt;
import com.evi0s.cse6341.p4.datastructures.IdentMap;
import com.evi0s.cse6341.p4.datastructures.ScopeBlock;
import com.evi0s.cse6341.p4.datastructures.ScopeStack;
import com.evi0s.cse6341.p4.datastructures.ScopeTag;
import com.evi0s.cse6341.p4.misc.Location;

import java.io.PrintStream;

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

    private void enterBlock() {
        // push
        IdentMap newTable = new IdentMap();
        ScopeBlock block = new ScopeBlock(new ScopeTag(this.blockType), newTable);
        ScopeStack.getInstance().push(block);
    }

    private void exitBlock() {
        // pop
        ScopeStack.getInstance().pop();
    }

    @Override
    public void check() {
        this.enterBlock();

        // check
        this.block.check();

        this.exitBlock();
    }

    @Override
    public void evaluate() {
        this.enterBlock();

        // evaluate
        this.block.evaluate();

        this.exitBlock();
    }

    @Override
    public void abstractEvaluate() {
        this.enterBlock();

        // evaluate
        this.block.abstractEvaluate();

        this.exitBlock();
    }

    public ScopeStack abstractEvaluateWithShadowStack(ScopeStack shadowStack) {
        // set instance to shadow stack
        ScopeStack.setInstance(shadowStack);

        // enter block
        this.enterBlock();

        // abstract evaluate
        this.block.abstractEvaluate();

        // saved evaluated instance
        return ScopeStack.getInstance();

        // need to exit block manually
    }

}
