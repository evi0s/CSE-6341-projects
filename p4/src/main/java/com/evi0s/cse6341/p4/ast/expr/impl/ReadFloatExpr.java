package com.evi0s.cse6341.p4.ast.expr.impl;

import com.evi0s.cse6341.p4.ast.expr.Expr;
import com.evi0s.cse6341.p4.errors.InterpreterRuntimeError;
import com.evi0s.cse6341.p4.types.AbstractIdentType;
import com.evi0s.cse6341.p4.utils.InputScanner;
import com.evi0s.cse6341.p4.misc.Location;
import com.evi0s.cse6341.p4.types.IdentType;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class ReadFloatExpr extends Expr {

    private final String TAG = "ReadFloatExpr";

    public ReadFloatExpr(Location loc) {
        super(loc);
    }

    public void print(PrintStream ps) {
        ps.print("readfloat");
    }

    @Override
    public void check() {
       this.identType = IdentType.TYPE_FLOAT;
    }

    @Override
    public void evaluate() {
        try {
            this.evaluatedValue = InputScanner.getDouble();
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
        this.evaluatedAbstractType = AbstractIdentType.TYPE_ANY_FLOAT;
    }
}
