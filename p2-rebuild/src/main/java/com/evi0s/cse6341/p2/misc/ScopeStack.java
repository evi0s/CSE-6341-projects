package com.evi0s.cse6341.p2.misc;

import java.util.ArrayDeque;
import java.util.Deque;

public class ScopeStack {
    private static ScopeStack instance = null;
    private final Deque<IdentMap> stack;

    public ScopeStack () {
        this.stack = new ArrayDeque<IdentMap>();
    }

    public ScopeStack(Deque<IdentMap> stack) {
        this.stack = stack;
    }

    public synchronized static ScopeStack getInstance () {
        if (instance == null) {
            instance = new ScopeStack();
        }
        return instance;
    }

    public static synchronized void initializeInstance () {
        if (instance != null) {
            instance = new ScopeStack();
        }
    }

    public static synchronized void resetInstance () {
        instance = new ScopeStack();
    }

    public static synchronized void destroyInstance () {
        instance = null;
    }

    public Deque<IdentMap> getScopeStack () {
        return this.stack;
    }

    public void initializeScopeStack () {
        if (this.stack != null && this.stack.isEmpty()) {
            this.stack.push(new IdentMap(new ScopeTag(BlockType.TYPE_GLOBAL)));
        }
    }

    public IdentMap getCurrentScopeIdentMap () throws IllegalStateException {
        if (this.stack != null && !this.stack.isEmpty()) {
            return this.stack.peek();
        }
        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public void push (IdentMap map) throws IllegalStateException {
        if (this.stack != null) {
            this.stack.push(map);
            return;
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public IdentMap pop () throws IllegalStateException {
        if (this.stack != null) {
            return this.stack.pop();
        }
        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public IdentInfo findIdentByName (String ident) throws IllegalStateException {
        if (this.stack != null) {
            for (IdentMap map : this.stack) {
                if (map.containsKey(ident)) {
                    return new IdentInfo(ident, map.get(ident), map.scopeTag);
                }
            }
            return null;
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }
}
