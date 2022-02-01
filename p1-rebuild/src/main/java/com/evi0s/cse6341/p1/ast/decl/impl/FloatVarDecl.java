package com.evi0s.cse6341.p1.ast.decl.impl;


import com.evi0s.cse6341.p1.ast.decl.VarDecl;
import com.evi0s.cse6341.p1.errors.DuplicateVarDeclarationError;
import com.evi0s.cse6341.p1.misc.IdentTable;
import com.evi0s.cse6341.p1.misc.Location;
import com.evi0s.cse6341.p1.misc.Type;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class FloatVarDecl extends VarDecl {
    private final String TAG = "FloatVarDecl";

    public final Type type;

    public FloatVarDecl(String i, Location loc) {
        super(i, loc);
        this.type = Type.TYPE_FLOAT;
    }

    public void print(PrintStream ps) {
        ps.print("float " + ident);
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void check(Map<String, Type> table) {
        HashMap<String, Type> globalTable = IdentTable.getInstance().getIndentTable();

        // duplicate check
        if (globalTable.containsKey(ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.ident, this.loc);
        }

        globalTable.put(ident, Type.TYPE_FLOAT);
    }
}
