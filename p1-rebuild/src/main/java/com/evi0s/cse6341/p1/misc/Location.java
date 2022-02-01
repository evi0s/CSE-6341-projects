package com.evi0s.cse6341.p1.misc;

public class Location {
    final int line;
    final int column;

    public Location(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public String toString() {
        return line + ":" + column;
    }
}
