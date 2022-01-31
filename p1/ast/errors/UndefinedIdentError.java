package ast.errors;

import ast.Location;

public class UndefinedIdentError extends Error {
    public UndefinedIdentError(String ident) {
        super("Undefined identifier " + ident);
    }

    public UndefinedIdentError(String tag, String ident, Location loc) {
        super("[" + tag + "] " + "Undefined identifier `" + ident + "' @ " + loc);
    }
}
