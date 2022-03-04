package com.evi0s.cse6341.p3.ast.expr.impl;

import com.evi0s.cse6341.p3.ast.expr.Expr;
import com.evi0s.cse6341.p3.errors.InterpreterRuntimeError;
import com.evi0s.cse6341.p3.misc.InputScanner;
import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.types.IdentType;

import java.io.PrintStream;
import java.util.InputMismatchException;

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
        } catch (InputMismatchException e) {
            throw new InterpreterRuntimeError(this.TAG, InterpreterRuntimeError.ErrorType.FAILED_STDIN_READ,
                    "failed to read from stdin: input type mismatch", this.loc);
        }
    }
}
