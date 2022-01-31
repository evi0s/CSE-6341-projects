package ast;

import java.io.PrintStream;
import java.util.Map;

public abstract class Expr extends ASTNode {
    protected Type type;
    public Expr(Location loc) {
        super(loc);
        this.type = null;
    }

    public abstract void print(PrintStream ps);
    public abstract void check(Map<String, Type> identTable);
}
