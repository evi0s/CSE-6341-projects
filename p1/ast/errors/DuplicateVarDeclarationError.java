package ast.errors;

import ast.Location;

public class DuplicateVarDeclarationError extends Error {
    public DuplicateVarDeclarationError(String ident) {
        super("Identifier `" + ident + "' already declared");
    }

    public DuplicateVarDeclarationError(String tag, String ident) {
        super("[" + tag + "] " + "Identifier `" + ident + "' already declared");
    }

    public DuplicateVarDeclarationError(String tag, String ident, Location loc) {
        super("[" + tag + "] " + "Identifier `" + ident + "' already declared @ " + loc);
    }
}
