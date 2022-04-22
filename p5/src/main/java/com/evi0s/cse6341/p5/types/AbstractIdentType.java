package com.evi0s.cse6341.p5.types;

public enum AbstractIdentType {
    TYPE_NEG("negative"),
    TYPE_ZERO("zero"),
    TYPE_POS("positive"),
    TYPE_ANY_INT("any int"),
    TYPE_ANY_FLOAT("any float"),
    TYPE_UNDEFINED("undefined"),
    TYPE_TRUE("true"),
    TYPE_FALSE("false"),
    TYPE_ANY_BOOL("any bool");

    private final String type;
    AbstractIdentType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
