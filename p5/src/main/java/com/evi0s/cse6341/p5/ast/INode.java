package com.evi0s.cse6341.p5.ast;

import java.io.PrintStream;

public interface INode {
    void print(PrintStream out);
    void check();
    void evaluate();
    void abstractEvaluate();
}
