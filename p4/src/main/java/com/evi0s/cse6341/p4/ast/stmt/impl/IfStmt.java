package com.evi0s.cse6341.p4.ast.stmt.impl;


import com.evi0s.cse6341.p4.ast.expr.CondExpr;
import com.evi0s.cse6341.p4.ast.stmt.Stmt;
import com.evi0s.cse6341.p4.datastructures.ScopeStack;
import com.evi0s.cse6341.p4.types.BlockType;
import com.evi0s.cse6341.p4.misc.Location;
import com.evi0s.cse6341.p4.utils.AbstractEvaluator;
import com.evi0s.cse6341.p4.utils.BlockNormalizer;

import java.io.PrintStream;

public class IfStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt thenstmt, elsestmt;

    public IfStmt(CondExpr e, Stmt s, Location loc) {
        super(loc);
        expr = e;
        thenstmt = s;
        elsestmt = null;
    }

    public IfStmt(CondExpr e, Stmt s1, Stmt s2, Location loc) {
        super(loc);
        expr = e;
        thenstmt = s1;
        elsestmt = s2;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + "if (");
        expr.print(ps);
        ps.print(")\n");
        thenstmt.print(ps, indent + "  ");
        if (elsestmt != null) {
            ps.print("\n" + indent + "else\n");
            elsestmt.print(ps, indent + "  ");
        }
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }

    @Override
    public void check() {
        expr.check();

        // check then stmt
        thenstmt.blockType = BlockType.TYPE_IF_THEN;
        thenstmt.check();

        // if else stmt exists, check it
        if (elsestmt != null) {
            elsestmt.blockType = BlockType.TYPE_IF_ELSE;
            elsestmt.check();
        }
    }

    @Override
    public void evaluate() {
        expr.evaluate();

        if (expr.evaluatedValue) {
            thenstmt.evaluate();
        } else if (elsestmt != null) {
            elsestmt.evaluate();
        }
    }

    @Override
    public void abstractEvaluate() {
        // no need to do abstract evaluation for if expr
        // expr.abstractEvaluate();

        if (elsestmt != null) {

            if (elsestmt instanceof IfStmt) {
                elsestmt.abstractEvaluate();
                return;
            }

            // need to merge
            ScopeStack shadowStackForThenStmt = ScopeStack.cloneFromExistStack(ScopeStack.getInstance());
            ScopeStack shadowStackForElseStmt = ScopeStack.cloneFromExistStack(ScopeStack.getInstance());

            // abstract evaluate
            ScopeStack evaluatedThenShadowStack = BlockNormalizer.normalize(thenstmt).abstractEvaluateWithShadowStack(shadowStackForThenStmt);
            ScopeStack evaluatedElseShadowStack = BlockNormalizer.normalize(elsestmt).abstractEvaluateWithShadowStack(shadowStackForElseStmt);

            // merge
            ScopeStack mergedStack = AbstractEvaluator.mergeScopeStack(evaluatedThenShadowStack, evaluatedElseShadowStack);

            // set instance
            ScopeStack.setInstance(mergedStack);

            return;
        }

        // else stmt is null, no need to merge stack
        ScopeStack originalStack = ScopeStack.getInstance();
        ScopeStack shadowStack = ScopeStack.cloneFromExistStack(originalStack);

        ScopeStack evaluatedShadowStack = BlockNormalizer.normalize(thenstmt).abstractEvaluateWithShadowStack(shadowStack);

        // drop the last block
        evaluatedShadowStack.pop();

        // merge
        ScopeStack mergedStack = AbstractEvaluator.mergeScopeStack(originalStack, evaluatedShadowStack);
        ScopeStack.setInstance(mergedStack);
    }
}
