package com.evi0s.cse6341.p5.ast.stmt.impl;

import com.evi0s.cse6341.p5.ast.expr.CondExpr;
import com.evi0s.cse6341.p5.ast.stmt.Stmt;
import com.evi0s.cse6341.p5.datastructures.ScopeStack;
import com.evi0s.cse6341.p5.errors.AbstractEvaluationError;
import com.evi0s.cse6341.p5.types.AbstractIdentType;
import com.evi0s.cse6341.p5.types.BlockType;
import com.evi0s.cse6341.p5.misc.Location;
import com.evi0s.cse6341.p5.utils.AbstractEvaluator;
import com.evi0s.cse6341.p5.utils.BlockNormalizer;

import java.io.PrintStream;

public class WhileStmt extends Stmt {
    private final String TAG = "WhileStmt";
    public final CondExpr expr;
    public final Stmt body;
    public WhileStmt(CondExpr e, Stmt s, Location loc) {
        super(loc);
        expr = e;
        body = s;
    }
    public void print(PrintStream ps, String indent) {
        ps.print(indent + "while (");
        expr.print(ps);
        ps.print(")\n");
        body.print(ps, indent + "  ");
    }
    public void print(PrintStream ps) {
        print(ps,"");
    }

    @Override
    public void check() {
        expr.check();

        body.blockType = BlockType.TYPE_WHILE;
        body.check();
    }

    @Override
    public void evaluate() {
        expr.evaluate();

        while (expr.evaluatedValue) {
            body.evaluate();
            expr.evaluate();
        }
    }

    @Override
    public void abstractEvaluate() {
        expr.abstractEvaluate();

        if (AbstractIdentType.TYPE_FALSE.equals(expr.evaluatedAbstractType)) {
            throw new AbstractEvaluationError(this.TAG, AbstractEvaluationError.ErrorType.DEAD_CODE,
                    "dead code detected: condition is always false (while condition)", this.loc);
        }


        ScopeStack shadowStack = ScopeStack.cloneFromExistStack(ScopeStack.getInstance());
        ScopeStack originalStack = ScopeStack.getInstance();

        while (true) {
            ScopeStack newStack = BlockNormalizer.normalize(body).abstractEvaluateWithShadowStack(shadowStack);

            // need to drop the last block manually
            newStack.pop();

            ScopeStack mergedStack = AbstractEvaluator.mergeScopeStack(originalStack, newStack);

            // assign the shadow stack to the merged stack
            shadowStack = mergedStack;
            originalStack = mergedStack;

            if (AbstractEvaluator.isSameStackScope(newStack, mergedStack)) {
                break;
            }
        }

        ScopeStack.setInstance(shadowStack);
    }
}
