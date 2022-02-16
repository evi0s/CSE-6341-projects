package com.evi0s.cse6341.p2.misc;

import java.io.Serializable;

public class IdentInfo implements Serializable {
    public String identName;
    public IdentType identType;
    public ScopeTag scopeTag;

    public IdentInfo(String identName, IdentType identType, ScopeTag scopeTag) {
        this.identName = identName;
        this.identType = identType;
        this.scopeTag = scopeTag;
    }

    public IdentInfo(String identName, IdentType identType) {
        this.identName = identName;
        this.identType = identType;
        this.scopeTag = new ScopeTag(BlockType.TYPE_PLAIN);
    }

    public String getIdentName() {
        return identName;
    }

    public IdentType getIdentType() {
        return identType;
    }

    public ScopeTag getScopeTag() {
        return scopeTag;
    }

    @Override
    public String toString() {
        return "IdentInfo{" +
                "identName='" + identName + '\'' +
                ", identType=" + identType +
                ", scopeTag=" + scopeTag +
                '}';
    }
}
