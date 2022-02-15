package com.evi0s.cse6341.p2.errors;

import com.evi0s.cse6341.p2.misc.Location;

public class UndefinedIdentError extends Error {
    public UndefinedIdentError(String ident) {
        super("Undefined identifier " + ident);
    }

    public UndefinedIdentError(String tag, String ident, Location loc) {
        super("[" + tag + "] " + "Undefined identifier `" + ident + "' @ " + loc);
    }
}
