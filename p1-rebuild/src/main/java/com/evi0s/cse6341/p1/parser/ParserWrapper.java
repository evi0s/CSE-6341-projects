package com.evi0s.cse6341.p1.parser;

import java.io.Reader;
import java_cup.runtime.ComplexSymbolFactory;

import com.evi0s.cse6341.p1.ast.impl.Program;
import com.evi0s.cse6341.p1.parser.Parser;


public class ParserWrapper {
    public static Program parse(Reader reader) throws Exception {
        ComplexSymbolFactory csf = new ComplexSymbolFactory();
        Lexer scanner = new Lexer(reader, csf);
        Parser parser = new Parser(scanner, csf);
        return (Program) parser.parse().value;
    }
}
