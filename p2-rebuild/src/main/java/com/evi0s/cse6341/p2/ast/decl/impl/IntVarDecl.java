package com.evi0s.cse6341.p2.ast.decl.impl;


import com.evi0s.cse6341.p2.ast.decl.VarDecl;
import com.evi0s.cse6341.p2.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p2.misc.IdentMap;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.ScopeStack;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;

public class IntVarDecl extends VarDecl {
    private final String TAG = "IntVarDecl";

    public final Type type;

    public IntVarDecl(String i, Location loc) {
        super(i, loc);
        this.type = Type.TYPE_INT;
    }

    public void print(PrintStream ps) {
        ps.print("int " + ident);
    }

    @Override
    public Type getType () {
        return this.type;
    }

    @Override
    public void check() throws DuplicateVarDeclarationError {
        IdentMap currentScopeIdentMap = ScopeStack.getInstance().getCurrentScopeIdentMap();

        // duplicate check
        if (currentScopeIdentMap.containsKey(ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.ident, this.loc);
        }

        currentScopeIdentMap.put(ident, Type.TYPE_INT);
    }
}
