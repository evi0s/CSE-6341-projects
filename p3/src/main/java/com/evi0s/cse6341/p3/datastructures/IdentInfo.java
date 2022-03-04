package com.evi0s.cse6341.p3.datastructures;

import com.evi0s.cse6341.p3.types.BlockType;
import com.evi0s.cse6341.p3.types.IdentType;

import java.io.Serializable;

public class IdentInfo implements Serializable {
    public String identName;
    public IdentType identType;
    public ScopeTag scopeTag;
    public Number value;

    public IdentInfo(String identName, IdentType identType, ScopeTag scopeTag) {
        this.identName = identName;
        this.identType = identType;
        this.scopeTag = scopeTag;
        this.value = null;
    }

    public IdentInfo(String identName, IdentType identType, Number value, ScopeTag scopeTag) {
        this.identName = identName;
        this.identType = identType;
        this.scopeTag = scopeTag;
        this.value = value;
    }

    public IdentInfo(String identName, IdentType identType) {
        this.identName = identName;
        this.identType = identType;
        this.scopeTag = new ScopeTag(BlockType.TYPE_PLAIN);
        this.value = null;
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

    public Number getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IdentInfo{" +
                "identName='" + identName + '\'' +
                ", identType=" + identType +
                ", scopeTag=" + scopeTag +
                ", value=" + value +
                '}';
    }
}
