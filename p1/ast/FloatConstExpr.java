package ast;

import java.io.PrintStream;
import java.util.Map;

public class FloatConstExpr extends Expr {
    public final Double fval;

    public FloatConstExpr(Double f, Location loc) {
        super(loc);
        fval = f;
    }

    public void print(PrintStream ps) {
        ps.print(fval);
    }

    @Override
    public void check(Map<String, Type> table) {
        this.type = Type.TYPE_FLOAT;
    }
}
