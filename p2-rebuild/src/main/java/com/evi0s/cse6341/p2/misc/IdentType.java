package com.evi0s.cse6341.p2.misc;

public enum IdentType {
    TYPE_INT("int"),
    TYPE_FLOAT("float");

    private final String type;
    IdentType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
