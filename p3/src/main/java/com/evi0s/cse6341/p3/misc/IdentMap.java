package com.evi0s.cse6341.p3.misc;

import java.util.HashMap;

public class IdentMap extends HashMap<String, IdentType> {

    public IdentType getTypeByIdent(String ident) {
        return this.get(ident);
    }

    public void putTypeByIdent(String ident, IdentType type) {
        this.put(ident, type);
    }
}
