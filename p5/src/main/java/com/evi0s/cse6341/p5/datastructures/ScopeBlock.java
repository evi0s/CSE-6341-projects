package com.evi0s.cse6341.p5.datastructures;

import com.evi0s.cse6341.p5.types.BlockType;

public class ScopeBlock {
    public ScopeTag scopeTag;
    public IdentMap identMap;
    public IdentState identState;
    public AbstractState abstractState;

    public ScopeBlock(ScopeTag scopeTag, IdentMap identMap) {
        this.scopeTag = scopeTag;
        this.identMap = identMap;
        this.identState = new IdentState();
        this.abstractState = new AbstractState();
    }

    public ScopeBlock(ScopeTag scopeTag, IdentMap identMap, IdentState identState) {
        this.scopeTag = scopeTag;
        this.identMap = identMap;
        this.identState = identState;
        this.abstractState = new AbstractState();
    }

    public ScopeBlock(ScopeTag scopeTag, IdentMap identMap, AbstractState abstractState) {
        this.scopeTag = scopeTag;
        this.identMap = identMap;
        this.identState = new IdentState();
        this.abstractState = abstractState;
    }

    public ScopeBlock(ScopeTag scopeTag, IdentMap identMap, IdentState identState, AbstractState abstractState) {
        this.scopeTag = scopeTag;
        this.identMap = identMap;
        this.identState = identState;
        this.abstractState = abstractState;
    }

    public ScopeBlock(ScopeTag scopeTag) {
        this.scopeTag = scopeTag;
        this.identMap = new IdentMap();
        this.identState = new IdentState();
        this.abstractState = new AbstractState();
    }

    public ScopeBlock(IdentMap identMap) {
        this.scopeTag = new ScopeTag(BlockType.TYPE_PLAIN);
        this.identMap = identMap;
        this.identState = new IdentState();
        this.abstractState = new AbstractState();
    }

    public ScopeBlock() {
        this.scopeTag = new ScopeTag(BlockType.TYPE_PLAIN);
        this.identMap = new IdentMap();
        this.identState = new IdentState();
        this.abstractState = new AbstractState();
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

    public AbstractState getAbstractState() {
        return abstractState;
    }

    public void setIdentMap(IdentMap identMap) {
        this.identMap = identMap;
    }

    public void setIdentState(IdentState identState) {
        this.identState = identState;
    }

    public void setAbstractState(AbstractState abstractState) {
        this.abstractState = abstractState;
    }

    public static ScopeBlock cloneFromExisting(ScopeBlock scopeBlock) {
        return new ScopeBlock(scopeBlock.getScopeTag(),
                IdentMap.cloneFromExisting(scopeBlock.getIdentMap()),
                AbstractState.cloneFromExisting(scopeBlock.getAbstractState()));
    }
}
