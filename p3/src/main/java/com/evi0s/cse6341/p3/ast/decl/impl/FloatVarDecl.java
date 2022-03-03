package com.evi0s.cse6341.p3.ast.decl.impl;


import com.evi0s.cse6341.p3.ast.decl.VarDecl;
import com.evi0s.cse6341.p3.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p3.misc.IdentMap;
import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.misc.ScopeStack;
import com.evi0s.cse6341.p3.misc.IdentType;

import java.io.PrintStream;

public class FloatVarDecl extends VarDecl {
    private final String TAG = "FloatVarDecl";

    public final IdentType identType;

    public FloatVarDecl(String i, Location loc) {
        super(i, loc);
        this.identType = IdentType.TYPE_FLOAT;
    }

    public void print(PrintStream ps) {
        ps.print("float " + ident);
    }

    @Override
    public IdentType getType() {
        return this.identType;
    }

    @Override
    public void check() {
        IdentMap currentScopeIdentMap = ScopeStack.getInstance().getCurrentScopeIdentMap();

        // duplicate check
        if (currentScopeIdentMap.containsKey(ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.ident, this.loc);
        }

        currentScopeIdentMap.put(ident, IdentType.TYPE_FLOAT);
    }

    @Override
    public void evaluate() {

    }
}
