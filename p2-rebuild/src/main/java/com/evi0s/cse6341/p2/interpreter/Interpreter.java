package com.evi0s.cse6341.p2.interpreter;

import com.evi0s.cse6341.p2.ast.impl.Program;
import com.evi0s.cse6341.p2.misc.IdentTable;
import com.evi0s.cse6341.p2.parser.ParserWrapper;
import com.evi0s.cse6341.p2.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p2.errors.TypeMismatchError;
import com.evi0s.cse6341.p2.errors.UndefinedIdentError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Interpreter {
    public static final String TAG = "Interpreter";

    // Process return codes
    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_PARSING_ERROR = 1;
    public static final int EXIT_STATIC_CHECKING_ERROR = 2;

    public static void main(String[] args) {
        String filename = args[0];
        Program astRoot = null;
        BufferedReader reader;
        IdentTable.initializeInstance();

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

        // TODO: type checking. If the program does not typecheck,
        // call fatalError with return code EXIT_STATIC_CHECKING_ERROR
        try {
            astRoot.check(IdentTable.getInstance().getIndentTable());
        } catch (TypeMismatchError | DuplicateVarDeclarationError | UndefinedIdentError e) {
            Interpreter.fatalError(e.getLocalizedMessage(), EXIT_STATIC_CHECKING_ERROR);
        }

        System.exit(Interpreter.EXIT_SUCCESS);
    }


    public static void fatalError(String message, int processReturnCode) {
        System.out.println(message);
        System.exit(processReturnCode);
    }
}
