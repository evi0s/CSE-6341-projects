package ast;

import ast.errors.UndefinedIdentError;

import java.io.PrintStream;
import java.util.Map;

public class IdentExpr extends Expr {

    private final String TAG = "IdentExpr";

    public final String ident;

    public IdentExpr(String i, Location loc) {
        super(loc);
        ident = i;
    }

    public void print(PrintStream ps) {
        ps.print(ident);
    }

    @Override
    public void check(Map<String, Type> identTable) throws UndefinedIdentError {
        if (identTable.get(this.ident) == null) {
            throw new UndefinedIdentError(this.TAG, this.ident, this.loc);
        }
        this.type = identTable.get(this.ident);
    }
}
