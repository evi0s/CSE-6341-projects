package com.evi0s.cse6341.p2.ast;

import com.evi0s.cse6341.p2.misc.Type;

import java.io.PrintStream;
import java.util.Map;

public interface INode {
    void print(PrintStream out);
    void check(Map<String, Type> identTable);
}
