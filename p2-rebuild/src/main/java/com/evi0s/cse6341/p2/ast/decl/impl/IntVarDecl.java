package com.evi0s.cse6341.p2.ast.decl.impl;


import com.evi0s.cse6341.p2.ast.decl.VarDecl;
import com.evi0s.cse6341.p2.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p2.misc.IdentMap;
import com.evi0s.cse6341.p2.misc.IdentTable;
import com.evi0s.cse6341.p2.misc.Location;
import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

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
    public void check(IdentMap table) throws DuplicateVarDeclarationError {
        HashMap<String, Type> globalTable = IdentTable.getInstance().getIndentTable();

        // duplicate check
        if (globalTable.containsKey(ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.ident, this.loc);
        }

        globalTable.put(ident, Type.TYPE_INT);
    }
}
