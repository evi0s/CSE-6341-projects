package ast;

import ast.errors.DuplicateVarDeclarationError;
import ast.errors.TypeMismatchError;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Decl extends Unit {

    private final String TAG = "Decl";

    public final VarDecl varDecl;
    public final Expr expr;

    public Decl(VarDecl d, Location loc) {
        super(loc);
        varDecl = d;
        expr = null;
    }

    public Decl(VarDecl d, Expr e, Location loc) {
        super(loc);
        varDecl = d;
        expr = e;
    }

    public void print(PrintStream ps) {
        varDecl.print(ps);
        if (expr != null) {
            ps.print(" = ");
            expr.print(ps);
        }
        ps.print(";");
    }

    @Override
    public void check(Map<String, Type> identTable) throws DuplicateVarDeclarationError, TypeMismatchError {
        HashMap<String, Type> globalTable = IdentTable.getInstance().getIndentTable();

        // check if the variable is already declared
        if (globalTable.containsKey(this.varDecl.ident)) {
            throw new DuplicateVarDeclarationError(this.TAG, this.varDecl.ident, this.loc);
        }

        if (this.expr != null) {
            // check if the assigned expression has undefined variables
            this.expr.check(identTable);

            // type check
            if (!this.varDecl.getType().equals(this.expr.type)) {
                throw new TypeMismatchError(this.TAG, this.varDecl.ident, this.varDecl.getType(), this.expr.type, this.loc);
            }
        }

        // add the identifier to the table
        this.varDecl.check(identTable);
    }
}
