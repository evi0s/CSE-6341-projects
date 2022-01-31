package ast.errors;

import ast.Type;
import ast.Location;


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

    public TypeMismatchError(String tag, String ident, Type srcType, Type dstType, Location location) {
        super("[" + tag + "] " + "Type mismatch: ident `" + ident + "': expect " + srcType + ", got " + dstType + " @ " + location);
    }

    public TypeMismatchError(String tag, Type srcType, Type dstType, Location location) {
        super("[" + tag + "] " + "Type mismatch: expect " + srcType + ", got " + dstType + " @ " + location);
    }
}
