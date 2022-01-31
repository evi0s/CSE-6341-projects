package ast;

import java.io.PrintStream;
import java.util.Map;

public abstract class Unit extends ASTNode {
    public Unit(Location loc) {
        super(loc);
    }

    public abstract void print(PrintStream ps);
    public abstract void check(Map<String, Type> table);
}
