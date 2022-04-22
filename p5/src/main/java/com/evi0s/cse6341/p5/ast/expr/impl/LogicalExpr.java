package com.evi0s.cse6341.p5.ast.expr.impl;

import com.evi0s.cse6341.p5.ast.expr.CondExpr;
import com.evi0s.cse6341.p5.errors.TypeMismatchError;
import com.evi0s.cse6341.p5.misc.Location;
import com.evi0s.cse6341.p5.types.AbstractIdentType;
import com.evi0s.cse6341.p5.utils.AbstractEvaluator;

import java.io.PrintStream;

public class LogicalExpr extends CondExpr {

    private final String TAG = "LogicalExpr";

    public static final int AND = 1;
    public static final int OR = 2;
    public static final int NOT = 3;
    public final CondExpr expr1, expr2;
    public final int op;

    public LogicalExpr(CondExpr e1, int oper, CondExpr e2, Location loc) {
        super(loc);
        expr1 = e1;
        expr2 = e2;
        op = oper;
    }

    public LogicalExpr(CondExpr e1, int oper, Location loc) {
        this(e1, oper, null, loc); // used for NOT
    }

    public void print(PrintStream ps) {
        if (op == NOT) {
            ps.print("!(");
            expr1.print(ps);
            ps.print(")");
            return;
        }
        ps.print("(");
        expr1.print(ps);
        switch (op) {
            case AND -> ps.print("&&");
            case OR -> ps.print("||");
        }
        expr2.print(ps);
        ps.print(")");
    }

    @Override
    public void check() {
        expr1.check();
        if (this.op == NOT) {
            this.identType = expr1.identType;
            return;
        }

        expr2.check();

        if (expr1.identType != expr2.identType) {
            throw new TypeMismatchError(this.TAG, expr1.identType, expr2.identType, this.loc);
        }

        this.identType = expr1.identType;
    }

    @Override
    public void evaluate() {
        expr1.evaluate();
        if (this.op == NOT) {
            this.evaluatedValue = !expr1.evaluatedValue;
            return;
        }

        switch (op) {
            case AND -> {
                if (!expr1.evaluatedValue) {
                    this.evaluatedValue = false;
                    return;
                }
                expr2.evaluate();
                this.evaluatedValue = expr1.evaluatedValue && expr2.evaluatedValue;
            }
            case OR -> {
                if (expr1.evaluatedValue) {
                    this.evaluatedValue = true;
                    return;
                }
                expr2.evaluate();
                this.evaluatedValue = expr1.evaluatedValue || expr2.evaluatedValue;
            }
        }
    }

    @Override
    public void abstractEvaluate () {
        expr1.abstractEvaluate();

        if (this.op == NOT) {
            this.evaluatedAbstractType = AbstractEvaluator.getLogicNotOpType(expr1.evaluatedAbstractType);
            return;
        }

        switch (op) {
            case AND -> {
                if (AbstractIdentType.TYPE_FALSE.equals(expr1.evaluatedAbstractType)) {
                    this.evaluatedAbstractType = AbstractIdentType.TYPE_FALSE;
                    return;
                }
                expr2.abstractEvaluate();
                this.evaluatedAbstractType = AbstractEvaluator.getLogicAndOpType(expr1.evaluatedAbstractType, expr2.evaluatedAbstractType);
            }
            case OR -> {
                if (AbstractIdentType.TYPE_TRUE.equals(expr1.evaluatedAbstractType)) {
                    this.evaluatedAbstractType = AbstractIdentType.TYPE_TRUE;
                    return;
                }
                expr2.abstractEvaluate();
                this.evaluatedAbstractType = AbstractEvaluator.getLogicOrOpType(expr1.evaluatedAbstractType, expr2.evaluatedAbstractType);
            }
        }
    }
}
