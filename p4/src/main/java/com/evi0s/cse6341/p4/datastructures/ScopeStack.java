package com.evi0s.cse6341.p4.datastructures;

import com.evi0s.cse6341.p4.types.AbstractIdentType;
import com.evi0s.cse6341.p4.types.BlockType;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class ScopeStack {
    private static ScopeStack instance = null;
    private final Deque<ScopeBlock> stack;

    public ScopeStack () {
        this.stack = new ArrayDeque<>();
    }

    public ScopeStack(Deque<ScopeBlock> stack) {
        this.stack = stack;
    }

    public synchronized static ScopeStack getInstance () {
        if (instance == null) {
            instance = new ScopeStack();
        }
        return instance;
    }

    public static synchronized void initializeInstance () {
        if (instance == null) {
            instance = new ScopeStack();
        }
    }

    public static synchronized void setInstance (ScopeStack instance) {
        ScopeStack.instance = instance;
    }

    public static synchronized void resetInstance () {
        instance = new ScopeStack();
    }

    public static synchronized void destroyInstance () {
        instance = null;
    }

    public Deque<ScopeBlock> getScopeStack () {
        return this.stack;
    }

    public void initializeScopeStack () {
        if (this.stack != null && this.stack.isEmpty()) {
            this.stack.push(new ScopeBlock(new ScopeTag(BlockType.TYPE_GLOBAL)));
        }
    }

    public ScopeBlock getCurrentScopeBlock () throws IllegalStateException {
        if (this.stack != null && !this.stack.isEmpty()) {
            return this.stack.peek();
        }
        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public void setCurrentScopeBlock(ScopeBlock scopeBlock) throws IllegalStateException {
        if (this.stack != null && !this.stack.isEmpty()) {
            // drop the first block
            this.stack.pop();
            // push the new block
            this.stack.push(scopeBlock);
        }
        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public IdentMap getCurrentScopeIdentMap () throws IllegalStateException {
        if (this.stack != null && !this.stack.isEmpty()) {
            return this.stack.peek().getIdentMap();
        }
        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public ScopeTag getCurrentScopeTag () throws IllegalStateException {
        if (this.stack != null && !this.stack.isEmpty()) {
            return this.stack.peek().getScopeTag();
        }
        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public void push (ScopeBlock block) throws IllegalStateException {
        if (this.stack != null) {
            this.stack.push(block);
            return;
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public ScopeBlock pop () throws IllegalStateException {
        if (this.stack != null) {
            return this.stack.pop();
        }
        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public IdentInfo findIdentByName (String ident) throws IllegalStateException {
        if (this.stack != null) {
            for (ScopeBlock block : this.stack) {
                IdentMap identMap = block.getIdentMap();
                if (identMap.containsKey(ident)) {
                    IdentState identState = block.getIdentState();
                    return new IdentInfo(ident, identMap.get(ident), identState.get(ident), block.scopeTag);
                }
            }
            return null;
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public void putNewIdentValueByName (String ident, Number value) throws IllegalStateException, NullPointerException {
        if (this.stack != null) {
            this.getCurrentScopeBlock().getIdentState().put(ident, value);
            return;
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public void setIdentValueByName (String ident, Number value) throws IllegalStateException, NullPointerException {
        if (this.stack != null) {
            for (ScopeBlock block : this.stack) {
                IdentMap identMap = block.getIdentMap();
                if (identMap.containsKey(ident)) {
                    // if (block.getIdentState().get(ident) == null) {
                    //     throw new NullPointerException("Uninitialized ident: " + ident);
                    // }
                    block.getIdentState().put(ident, value);
                    return;
                }
            }

            throw new NullPointerException("Identifier `" + ident + "' not found.");
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public Number getIdentValueByName (String ident) throws IllegalStateException, NullPointerException {
        if (this.stack != null) {
            for (ScopeBlock block : this.stack) {
                IdentMap identMap = block.getIdentMap();
                if (identMap.containsKey(ident)) {
                    return block.getIdentState().get(ident);
                }
            }

            throw new NullPointerException("Identifier `" + ident + "' not found.");
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public void putNewIdentAbstractTypeByName (String ident, AbstractIdentType abstractType) throws IllegalStateException, NullPointerException {
        if (this.stack != null) {
            this.getCurrentScopeBlock().getAbstractState().put(ident, abstractType);
            return;
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public void setIdentAbstractTypeByName (String ident, AbstractIdentType abstractType) throws IllegalStateException, NullPointerException {
        if (this.stack != null) {
            for (ScopeBlock block : this.stack) {
                IdentMap identMap = block.getIdentMap();
                if (identMap.containsKey(ident)) {
                    // if (block.getIdentState().get(ident) == null) {
                    //     throw new NullPointerException("Uninitialized ident: " + ident);
                    // }
                    block.getAbstractState().put(ident, abstractType);
                    return;
                }
            }

            throw new NullPointerException("Identifier `" + ident + "' not found.");
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public AbstractIdentType getIdentAbstractTypeByName (String ident) throws IllegalStateException, NullPointerException {
        if (this.stack != null) {
            for (ScopeBlock block : this.stack) {
                IdentMap identMap = block.getIdentMap();
                if (identMap.containsKey(ident)) {
                    return block.getAbstractState().get(ident);
                }
            }

            throw new NullPointerException("Identifier `" + ident + "' not found.");
        }

        throw new IllegalStateException(ScopeStack.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    public static ScopeStack cloneFromExistStack(ScopeStack existStack) {
        ScopeStack newStack = new ScopeStack();
        for (Iterator<ScopeBlock> it = existStack.getScopeStack().descendingIterator(); it.hasNext();) {
            newStack.getScopeStack().push(ScopeBlock.cloneFromExisting(it.next()));
        }
        return newStack;
    }
}
