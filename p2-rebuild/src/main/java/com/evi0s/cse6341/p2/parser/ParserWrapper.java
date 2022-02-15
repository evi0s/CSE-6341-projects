package com.evi0s.cse6341.p2.parser;

import java.io.Reader;

import java_cup.runtime.*;

import com.evi0s.cse6341.p2.interpreter.Interpreter;
import com.evi0s.cse6341.p2.ast.impl.Program;

public class ParserWrapper {
    public static Program parse(Reader reader) throws Exception {
        ComplexSymbolFactory csf = new ComplexSymbolFactory();
        Lexer scanner = new Lexer(reader, csf);
        Parser parser = new Parser(scanner, csf);
        return (Program) parser.parse().value;
    }
}
