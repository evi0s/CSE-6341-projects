package ast;

import java.io.PrintStream;
import java.util.Map;

public class Program extends ASTNode {
    public final UnitList unitList;
    public Program(UnitList ul, Location loc) {
        super(loc);
        unitList = ul;
    }
    public void print(PrintStream ps) {
	unitList.print(ps);
    }

    @Override
    public void check(Map<String, Type> identTable) {
        this.unitList.check(identTable);
    }
}
