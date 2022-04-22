package com.evi0s.cse6341.p5.ast.expr.impl;

import com.evi0s.cse6341.p5.ast.expr.Expr;
import com.evi0s.cse6341.p5.errors.InterpreterRuntimeError;
import com.evi0s.cse6341.p5.types.AbstractIdentType;
import com.evi0s.cse6341.p5.utils.InputScanner;
import com.evi0s.cse6341.p5.misc.Location;
import com.evi0s.cse6341.p5.types.IdentType;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class ReadIntExpr extends Expr {
    private final String TAG = "ReadIntExpr";

    public ReadIntExpr(Location loc) {
        super(loc);
    }

    public void print(PrintStream ps) {
        ps.print("readint");
    }

    @Override
    public void check() {
        this.identType = IdentType.TYPE_INT;
    }

    @Override
    public void evaluate() {
        try {
            this.evaluatedValue = InputScanner.getLong();
        } catch (NoSuchElementException e) {
            if (e instanceof InputMismatchException) {
                throw new InterpreterRuntimeError(this.TAG, InterpreterRuntimeError.ErrorType.FAILED_STDIN_READ,
                        "failed to read from stdin: input type mismatch.", this.loc);
            }
            throw new InterpreterRuntimeError(this.TAG, InterpreterRuntimeError.ErrorType.FAILED_STDIN_READ,
                    "failed to read from stdin: no more input.", this.loc);
        }
    }

    @Override
    public void abstractEvaluate () {
        this.evaluatedAbstractType = AbstractIdentType.TYPE_ANY_INT;
    }
}
