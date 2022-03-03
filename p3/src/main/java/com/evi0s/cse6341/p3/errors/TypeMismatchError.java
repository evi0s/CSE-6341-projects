package com.evi0s.cse6341.p3.errors;

import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.misc.IdentType;
import com.evi0s.cse6341.p3.misc.ScopeTag;


public class TypeMismatchError extends Error {
    public TypeMismatchError(String message) {
        super(message);
    }

    public TypeMismatchError(String message, Location location) {
        super(message + " @ " + location);
    }

    public TypeMismatchError(String tag, String message, Location location) {
        super("[" + tag + "] " + message + " @ " + location);
    }

    public TypeMismatchError(String tag, String ident, IdentType srcIdentType, IdentType dstIdentType, Location location) {
        super("[" + tag + "] " + "Type mismatch: ident `" + ident + "': expect " + srcIdentType + ", got " + dstIdentType + " @ " + location);
    }

    public TypeMismatchError(String tag, IdentType srcIdentType, IdentType dstIdentType, Location location) {
        super("[" + tag + "] " + "Type mismatch: expect " + srcIdentType + ", got " + dstIdentType + " @ " + location);
    }

    public TypeMismatchError(String tag, String ident, ScopeTag scopeTag, IdentType srcType, IdentType dstType, Location location) {
        super("[" + tag + "] " + "Type mismatch: ident `" + ident + "' declared in scope: " + scopeTag + ", expect " + srcType + ", got " + dstType + " @ " + location);
    }
}
