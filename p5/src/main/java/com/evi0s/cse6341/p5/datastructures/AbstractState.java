package com.evi0s.cse6341.p5.datastructures;

import com.evi0s.cse6341.p5.types.AbstractIdentType;

import java.util.HashMap;

public class AbstractState extends HashMap<String, AbstractIdentType> {
    public AbstractState() {
        super();
    }

    public AbstractState(AbstractState state) {
        super(state);
    }

    public void putAll(AbstractState state) {
        super.putAll(state);
    }

    public static AbstractState cloneFromExisting(AbstractState state) {
        return new AbstractState(state);
    }
}
