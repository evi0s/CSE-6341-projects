package com.evi0s.cse6341.p1.parser;

import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

import com.evi0s.cse6341.p1.interpreter.Interpreter;

%%

%public
%class Lexer
%cup
%implements sym
%char
%line
%column

%{
    StringBuffer string = new StringBuffer();
    public Lexer(java.io.Reader in, ComplexSymbolFactory sf){
	    this(in);
	    symbolFactory = sf;
    }
    ComplexSymbolFactory symbolFactory;

    private Symbol symbol(String name, int sym) {
        return symbolFactory.newSymbol(name, sym, new Location(yyline + 1, yycolumn + 1, yyline + 1), new Location(yyline + 1, yycolumn + yylength(), yycolumn + 1));
    }

    private Symbol symbol(String name, int sym, Object val) {
        Location left = new Location(yyline + 1, yycolumn + 1, yyline + 1);
        Location right = new Location(yyline + 1, yycolumn + yylength(), yycolumn + 1);
        return symbolFactory.newSymbol(name, sym, left, right, val);
    }
%}

%eofval{
     return symbolFactory.newSymbol("EOF", EOF, new Location(yyline + 1, yycolumn + 1, (int) yychar), new Location(yyline + 1, yycolumn + 1, (int) yychar + 1));
%eofval}

Ident = [a-zA-Z$_] [a-zA-Z0-9$_]*
IntLiteral = 0 | [1-9][0-9]*
FloatLiteral = [0-9]+ \. [0-9]*
new_line = \r|\n|\r\n;
white_space = {new_line} | [ \t\f]

%%

<YYINITIAL>{
/* keywords */
"int"             { return symbol("int",         INT); }
"float"           { return symbol("float",       FLOAT); }
"print"           { return symbol("print",       PRINT); }

/* literals */
{IntLiteral} { return symbol("Intconst", INTCONST, Long.valueOf(Long.parseLong(yytext()))); }
{FloatLiteral} { return symbol("Floatconst", FLOATCONST, Double.valueOf(Double.parseDouble(yytext()))); }

/* names */
{Ident}           { return symbol("ID", IDENT, yytext()); }

/* separators */
";"               { return symbol(";",  SEMICOLON); }
"("               { return symbol("(",  LPAREN); }
")"               { return symbol(")",  RPAREN); }
"="               { return symbol("=",  ASSIGN); }
"+"               { return symbol("+",  PLUS); }

"/*" [^*] ~"*/" | "/*" "*"+ "/"
                  { /* ignore comments */ }

{white_space}     { /* ignore */ }

}

/* error fallback */
[^]               { Interpreter.fatalError("Illegal character <" + yytext() + ">", Interpreter.EXIT_PARSING_ERROR); }
