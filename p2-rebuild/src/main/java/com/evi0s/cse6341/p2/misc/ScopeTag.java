package com.evi0s.cse6341.p2.misc;

public class ScopeTag {
    public String type;
    public int hash;

    public ScopeTag(String type) {
        this.type = type;
        this.hash = this.hashCode();
    }

    public ScopeTag() {
        this.type = "plain";
        this.hash = this.hashCode();
    }

    public String toString() {
        return type + ":" + Integer.toHexString(hash);
    }
}
