package com.evi0s.cse6341.p2.errors;

import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.ScopeTag;

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

    public DuplicateVarDeclarationError(String tag, String ident, ScopeTag scope, Location loc) {
        super("[" + tag + "] " + "Identifier `" + ident + "' already declared in scope: " + scope + " @ " + loc);
    }
}
