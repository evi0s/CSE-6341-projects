package com.evi0s.cse6341.p2.misc;

import java.util.Stack;

public class ScopeStack {
    private static ScopeStack instance = null;
    private final Stack<IdentMap> stack;

    public ScopeStack () {
        this.stack = new Stack<IdentMap>();
    }

    public ScopeStack(Stack<IdentMap> stack) {
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

    public Stack<IdentMap> getScopeStack () {
        return this.stack;
    }

    public void initializeScopeStack () {
        if (this.stack != null && this.stack.isEmpty()) {
            this.stack.push(new IdentMap(new ScopeTag("root")));
        }
    }

    public IdentMap getCurrentScopeIdentMap () throws IllegalStateException {
        if (this.stack != null && !this.stack.isEmpty()) {
            return this.stack.peek();
        }
        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");

    }
}
