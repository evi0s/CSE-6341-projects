package com.evi0s.cse6341.p2.misc;

import java.util.HashMap;

public class IdentMap extends HashMap<String, Type> {

    public ScopeTag scopeTag;

    public IdentMap (ScopeTag scopeTag) {
        this.scopeTag = scopeTag;
    }

    public IdentMap () {
        this.scopeTag = new ScopeTag();
    }

    public IdentMap (ScopeTag scopeTag, HashMap<String, Type> map) {
        this.scopeTag = scopeTag;
        this.putAll(map);
    }

    public Type getTypeByIdent(String ident) {
        return this.get(ident);
    }

    public void putTypeByIdent(String ident, Type type) {
        this.put(ident, type);
    }
}
