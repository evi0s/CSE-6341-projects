package com.evi0s.cse6341.p3.ast.decl.impl;


import com.evi0s.cse6341.p3.ast.decl.VarDecl;
import com.evi0s.cse6341.p3.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p3.misc.IdentMap;
import com.evi0s.cse6341.p3.misc.Location;
import com.evi0s.cse6341.p3.misc.ScopeStack;
import com.evi0s.cse6341.p3.misc.IdentType;

import java.io.PrintStream;

public class IntVarDecl extends VarDecl {
    private final String TAG = "IntVarDecl";

    public final IdentType identType;

    public IntVarDecl(String i, Location loc) {
        super(i, loc);
        this.identType = IdentType.TYPE_INT;
    }

    public void print(PrintStream ps) {
        ps.print("int " + ident);
    }

    @Override
    public IdentType getType () {
        return this.identType;
    }

    @Override
    public void check() throws DuplicateVarDeclarationError {
        IdentMap currentScopeIdentMap = ScopeStack.getInstance().getCurrentScopeIdentMap();

        // duplicate check
        if (currentScopeIdentMap.containsKey(ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.ident, this.loc);
        }

        currentScopeIdentMap.put(ident, IdentType.TYPE_INT);
    }

    @Override
    public void evaluate() {

    }
}
