package com.evi0s.cse6341.p4.utils;

import com.evi0s.cse6341.p4.ast.impl.UnitList;
import com.evi0s.cse6341.p4.ast.stmt.Stmt;
import com.evi0s.cse6341.p4.ast.stmt.impl.BlockStmt;

public class BlockNormalizer {
    public static BlockStmt normalize(Stmt stmt) {
        if (stmt instanceof BlockStmt) {
            return (BlockStmt) stmt;
        }

        return new BlockStmt(new UnitList(stmt, stmt.loc), stmt.loc);
    }
}
