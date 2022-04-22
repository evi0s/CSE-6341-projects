package com.evi0s.cse6341.p5.ast.expr.impl;

import com.evi0s.cse6341.p5.ast.expr.Expr;
import com.evi0s.cse6341.p5.errors.AbstractEvaluationError;
import com.evi0s.cse6341.p5.errors.InterpreterRuntimeError;
import com.evi0s.cse6341.p5.errors.TypeMismatchError;
import com.evi0s.cse6341.p5.misc.Location;
import com.evi0s.cse6341.p5.utils.AbstractEvaluator;

import java.io.PrintStream;

public class BinaryExpr extends Expr {

    private final String TAG = "BinaryExpr";

    public static final int PLUS = 1;
    public static final int MINUS = 2;
    public static final int TIMES = 3;
    public static final int DIV = 4;
    public final Expr expr1, expr2;
    public final int op;

    public BinaryExpr(Expr e1, int oper, Expr e2, Location loc) {
        super(loc);
        expr1 = e1;
        expr2 = e2;
        op = oper;
    }

    public String getOperationString (int operation) {
        return switch (operation) {
            case PLUS -> "+";
            case MINUS -> "-";
            case TIMES -> "*";
            case DIV -> "/";
            default -> "";
        };
    }

    public void print(PrintStream ps) {
        ps.print("(");
        expr1.print(ps);
        ps.print(getOperationString(op));
        expr2.print(ps);
        ps.print(")");
    }

    @Override
    public void check() throws TypeMismatchError {
        expr1.check();
        expr2.check();

        if (expr1.identType != expr2.identType) {
            throw new TypeMismatchError(this.TAG, expr1.identType, expr2.identType, this.loc);
        }

        this.identType = expr1.identType;
    }

    @Override
    public void evaluate() {
        expr1.evaluate();
        expr2.evaluate();

        boolean isDouble = expr1.evaluatedValue instanceof Double && expr2.evaluatedValue instanceof Double;
        boolean isLong   = expr1.evaluatedValue instanceof Long   && expr2.evaluatedValue instanceof Long;

        switch (op) {
            case PLUS -> {
                if (isLong) {
                    this.evaluatedValue = (Long) expr1.evaluatedValue + (Long) expr2.evaluatedValue;
                } else if (isDouble) {
                    this.evaluatedValue = (Double) expr1.evaluatedValue + (Double) expr2.evaluatedValue;
                }
            }
            case MINUS -> {
                if (isLong) {
                    this.evaluatedValue = (Long) expr1.evaluatedValue - (Long) expr2.evaluatedValue;
                } else if (isDouble) {
                    this.evaluatedValue = (Double) expr1.evaluatedValue - (Double) expr2.evaluatedValue;
                }
            }
            case TIMES -> {
                if (isLong) {
                    this.evaluatedValue = (Long) expr1.evaluatedValue * (Long) expr2.evaluatedValue;
                } else if (isDouble) {
                    this.evaluatedValue = (Double) expr1.evaluatedValue * (Double) expr2.evaluatedValue;
                }
            }
            case DIV -> {
                if ((isLong && (Long) expr2.evaluatedValue == 0) ||
                        (isDouble && (Double) expr2.evaluatedValue == 0)) {
                    throw new InterpreterRuntimeError(this.TAG, InterpreterRuntimeError.ErrorType.DIV_BY_ZERO,
                            "division by zero, evaluation on value: " + expr1.evaluatedValue + " divide by " + expr2.evaluatedValue + ".", this.loc);
                }

                if (isLong) {
                    this.evaluatedValue = (Long) expr1.evaluatedValue / (Long) expr2.evaluatedValue;
                } else if (isDouble) {
                    this.evaluatedValue = (Double) expr1.evaluatedValue / (Double) expr2.evaluatedValue;
                }
            }
        }
    }

    @Override
    public void abstractEvaluate() {
        expr1.abstractEvaluate();
        expr2.abstractEvaluate();

        switch (op) {
            case PLUS ->
                this.evaluatedAbstractType = AbstractEvaluator.getAddOpType(expr1.identType, expr1.evaluatedAbstractType, expr2.evaluatedAbstractType);
            case MINUS ->
                this.evaluatedAbstractType = AbstractEvaluator.getSubOpType(expr1.identType, expr1.evaluatedAbstractType, expr2.evaluatedAbstractType);
            case TIMES ->
                this.evaluatedAbstractType = AbstractEvaluator.getMulOpType(expr1.evaluatedAbstractType, expr2.evaluatedAbstractType);
            case DIV -> {
                try {
                    this.evaluatedAbstractType = AbstractEvaluator.getDivOpType(expr1.identType, expr1.evaluatedAbstractType, expr2.evaluatedAbstractType);
                } catch (AbstractEvaluationError e) {
                    throw new AbstractEvaluationError(this.TAG, e.type,
                            "division by zero, abstract evaluation on value: " + expr1.evaluatedAbstractType + " divide by " + expr2.evaluatedAbstractType + ".", this.loc);
                }
            }
        }
    }
}
