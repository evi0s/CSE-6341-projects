package com.evi0s.cse6341.p4.types;

public enum BlockType {
    TYPE_IF_THEN("if-then"),
    TYPE_IF_ELSE("if-else"),
    TYPE_WHILE("while"),
    TYPE_PLAIN("plain"),
    TYPE_GLOBAL("global"),
    TYPE_MERGED_IF("merged-if");

    private final String type;

    BlockType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
