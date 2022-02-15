package com.evi0s.cse6341.p2.misc;


import java.util.HashMap;

public class IdentTable {
    private final HashMap<String, Type> indentTable;
    private static IdentTable instance = null;

    public IdentTable() {
        indentTable = new HashMap<String, Type>();
    }

    public IdentTable(HashMap<String, Type> indentTable) {
        this.indentTable = indentTable;
    }

    public HashMap<String, Type> getIndentTable () {
        return this.indentTable;
    }

    public static synchronized IdentTable getInstance() {
        if (instance == null) {
            instance = new IdentTable();
        }
        return instance;
    }

    public static synchronized void initializeInstance () {
        if (instance != null) {
            instance = new IdentTable();
        }
    }
}
