package ast;

import java.io.PrintStream;
import java.util.Map;

public class IntConstExpr extends Expr {
    public final Long ival;

    public IntConstExpr(Long i, Location loc) {
        super(loc);
        ival = i;
    }

    public void print(PrintStream ps) {
        ps.print(ival);
    }

    @Override
    public void check(Map<String, Type> table) {
        this.type = Type.TYPE_INT;
    }
}
