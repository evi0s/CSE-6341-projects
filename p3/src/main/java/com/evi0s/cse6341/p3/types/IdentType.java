package com.evi0s.cse6341.p3.misc;

public enum IdentType {
    TYPE_INT("int"),
    TYPE_FLOAT("float"),
    TYPE_BOOLEAN("boolean");

    private final String type;
    IdentType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
