package com.evi0s.cse6341.p3.misc;

public class ScopeTag {
    public BlockType type;
    public int hash;

    public ScopeTag(BlockType type) {
        this.type = type;
        this.hash = this.hashCode();
    }

    public ScopeTag() {
        this.type = BlockType.TYPE_PLAIN;
        this.hash = this.hashCode();
    }

    public String toString() {
        return type + ":" + Integer.toHexString(hash);
    }
}
