package ast;

import ast.errors.DuplicateVarDeclarationError;

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
