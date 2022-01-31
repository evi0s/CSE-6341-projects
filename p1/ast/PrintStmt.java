package ast;

import java.io.PrintStream;
import java.util.Map;

public class PrintStmt extends Stmt {
    public final Expr expr;

    public PrintStmt(Expr e, Location loc) {
        super(loc);
        expr = e;
    }

    public void print(PrintStream ps) {
        ps.print("print ");
        expr.print(ps);
        ps.print(";");
    }

    @Override
    public void check(Map<String, Type> identTable) {
        this.expr.check(identTable);
    }
}
