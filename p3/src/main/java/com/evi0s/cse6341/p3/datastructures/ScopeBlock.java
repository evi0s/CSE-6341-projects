package com.evi0s.cse6341.p3.misc;

import com.evi0s.cse6341.p3.types.BlockType;

public class ScopeBlock {
    public ScopeTag scopeTag;
    public IdentMap identMap;
    public IdentState identState;

    public ScopeBlock(ScopeTag scopeTag, IdentMap identMap) {
        this.scopeTag = scopeTag;
        this.identMap = identMap;
        this.identState = new IdentState();
    }

    public ScopeBlock(ScopeTag scopeTag, IdentMap identMap, IdentState identState) {
        this.scopeTag = scopeTag;
        this.identMap = identMap;
        this.identState = identState;
    }

    public ScopeBlock(ScopeTag scopeTag) {
        this.scopeTag = scopeTag;
        this.identMap = new IdentMap();
        this.identState = new IdentState();
    }

    public ScopeBlock(IdentMap identMap) {
        this.scopeTag = new ScopeTag(BlockType.TYPE_PLAIN);
        this.identMap = identMap;
        this.identState = new IdentState();
    }

    public ScopeBlock() {
        this.scopeTag = new ScopeTag(BlockType.TYPE_PLAIN);
        this.identMap = new IdentMap();
        this.identState = new IdentState();
    }

    public ScopeTag getScopeTag() {
        return scopeTag;
    }

    public IdentMap getIdentMap() {
        return identMap;
    }

    public IdentState getIdentState() {
        return identState;
    }

    public void setIdentMap(IdentMap identMap) {
        this.identMap = identMap;
    }

    public void setIdentState(IdentState identState) {
        this.identState = identState;
    }
}
