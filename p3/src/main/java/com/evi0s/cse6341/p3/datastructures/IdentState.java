package com.evi0s.cse6341.p3.datastructures;

import java.util.HashMap;

public class IdentState extends HashMap<String, Number> {

    public IdentState() {
        super();
    }

    public IdentState(IdentState state) {
        super(state);
    }

    public IdentState(HashMap<String, Number> map) {
        super(map);
    }

    public Double getDouble(String ident) {
        if (super.get(ident) != null && super.get(ident) instanceof Double) {
            return (Double) super.get(ident);
        }
        return null;
    }

    public Long getLong(String ident) {
        if (super.get(ident) != null && super.get(ident) instanceof Long) {
            return (Long) super.get(ident);
        }
        return null;
    }

    public Double setDouble(String ident, Double value) {
        return (Double) super.put(ident, value);
    }

    public Long setLong(String ident, Long value) {
        return (Long) super.put(ident, value);
    }
}
