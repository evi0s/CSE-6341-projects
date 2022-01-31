package ast;

import ast.errors.DuplicateVarDeclarationError;

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
    public void check(Map<String, Type> table) throws DuplicateVarDeclarationError {
        HashMap<String, Type> globalTable = IdentTable.getInstance().getIndentTable();

        // duplicate check
        if (globalTable.containsKey(ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.ident, this.loc);
        }

        globalTable.put(ident, Type.TYPE_INT);
    }
}
