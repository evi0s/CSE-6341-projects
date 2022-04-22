package com.evi0s.cse6341.p5.interpreter;

import com.evi0s.cse6341.p5.ast.impl.Program;
import com.evi0s.cse6341.p5.datastructures.ScopeStack;
import com.evi0s.cse6341.p5.utils.AbstractEvaluator;
import com.evi0s.cse6341.p5.utils.OutputPrinter;
import com.evi0s.cse6341.p5.parser.ParserWrapper;
import com.evi0s.cse6341.p5.errors.AbstractEvaluationError;
import com.evi0s.cse6341.p5.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p5.errors.TypeMismatchError;
import com.evi0s.cse6341.p5.errors.UndefinedIdentError;

import java.io.*;

public class Interpreter {
    public static final String TAG = "Interpreter";

    // Process return codes
    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_PARSING_ERROR = 1;
    public static final int EXIT_STATIC_CHECKING_ERROR = 2;
    public static final int EXIT_UNINITIALIZED_VAR_ERROR = 3;
    public static final int EXIT_DIV_BY_ZERO_ERROR = 4;
    public static final int EXIT_FAILED_STDIN_READ = 5;
    public static final int EXIT_DEAD_CODE = 6;

    public static final int EXIT_RUNTIME_ERROR = -1;

    public static void main(String[] args) {
        String filename = args[0];
        Program astRoot = null;
        BufferedReader reader;

        // initialize the scope stack
        ScopeStack.initializeInstance();
        ScopeStack.getInstance().initializeScopeStack();

        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            astRoot = ParserWrapper.parse(reader);
        } catch (Exception ex) {
            Interpreter.fatalError("Uncaught parsing error: " + ex, EXIT_PARSING_ERROR);
        }

        if (astRoot == null) {
            Interpreter.fatalError("Parsing error: AST is null", EXIT_PARSING_ERROR);
            return;
        }

        // for debugging
        astRoot.print(System.out);

        // static type checking
        try {
            astRoot.check();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Interpreter.fatalError(e.getLocalizedMessage(), EXIT_RUNTIME_ERROR);
        } catch (TypeMismatchError | DuplicateVarDeclarationError | UndefinedIdentError e) {
            Interpreter.fatalError(e.getLocalizedMessage(), EXIT_STATIC_CHECKING_ERROR);
        }

        // reset the scope stack
        ScopeStack.resetInstance();
        ScopeStack.getInstance().initializeScopeStack();

        // initialize the printer
        OutputPrinter.initializeInstance();

        // abstract evaluation
        AbstractEvaluator.initialize();
        try {
            astRoot.abstractEvaluate();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Interpreter.fatalError(e.getLocalizedMessage(), EXIT_RUNTIME_ERROR);
        } catch (AbstractEvaluationError e) {
            switch (e.type) {
                case DIV_BY_ZERO       -> Interpreter.fatalError(e.getLocalizedMessage(), EXIT_DIV_BY_ZERO_ERROR);
                case UNINITIALIZED_VAR -> Interpreter.fatalError(e.getLocalizedMessage(), EXIT_UNINITIALIZED_VAR_ERROR);
                case DEAD_CODE         -> Interpreter.fatalError(e.getLocalizedMessage(), EXIT_DEAD_CODE);
            }
        }

        // comment out the following lines to disable the evaluation
        // // reset the scope stack
        // ScopeStack.resetInstance();
        // ScopeStack.getInstance().initializeScopeStack();

        // // initialize the scanner
        // InputScanner.initializeInstance();

        // // run the program
        // try {
        //     astRoot.evaluate();
        // } catch (IllegalStateException e) {
        //     e.printStackTrace();
        //     Interpreter.fatalError(e.getLocalizedMessage(), EXIT_RUNTIME_ERROR);
        // } catch (NullPointerException e) {
        //     Interpreter.fatalError("[" + TAG + "] fatal error: null pointer after static check.", EXIT_RUNTIME_ERROR);
        // } catch (InterpreterRuntimeError e) {
        //     switch (e.type) {
        //         case DIV_BY_ZERO       -> Interpreter.fatalError(e.getLocalizedMessage(), EXIT_DIV_BY_ZERO_ERROR);
        //         case UNINITIALIZED_VAR -> Interpreter.fatalError(e.getLocalizedMessage(), EXIT_UNINITIALIZED_VAR_ERROR);
        //         case FAILED_STDIN_READ -> Interpreter.fatalError(e.getLocalizedMessage(), EXIT_FAILED_STDIN_READ);
        //     }
        // }

        System.exit(Interpreter.EXIT_SUCCESS);
    }


    public static void fatalError(String message, int processReturnCode) {
        System.out.println(message);
        System.exit(processReturnCode);
    }
}
