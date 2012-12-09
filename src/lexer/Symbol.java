package lexer;

public enum Symbol {




    AND("&&"),
    ASSIGN("="),
    BOOLEAN("boolean"),
    BREAK("break"),
    CLASS("class"),
    COLON(":"),
    COMMA(","),
    DIV("/"),
    DOT("."),
    ELSE("else"),
    EOF("~eof"),
    EQ("=="),
    EXTENDS("extends"),
    FALSE("false"),
    FINAL("final"),
    GE(">="),
    GT(">"),
    IDENT("~ident"),
    IF("if"),
    INT("int"),
    LE("<="),
    LEFTCURBRACKET("{"),
    LEFTPAR("("),
    LITERALSTRING("~literalString"),
    LT("<"),
    MINUS("-"),
    MULT("*"),
    NEQ("!="),
    NEW("new"),
    NOT("!"),
    NULL("null"),
    NUMBER("~number"),
    OR("||"),
    PLUS("+"),
    STATIC("static"),
    PRIVATE("private"),
    PUBLIC("public"),
    READ("read"),
    RETURN("return"),
    RIGHTCURBRACKET("}"),
    RIGHTPAR(")"),
    SEMICOLON(";"),
    STRING("String"),
    SUPER("super"),
    THIS("this"),
    TRUE("true"),
    VOID("void"),
    WHILE("while"),
    WRITE("write");

/*
   public final static int

      EOF = 0,
      IDENT = 1,
      NUMBER =  2,
      PLUS = 3,
      MINUS = 4,
      MULT = 5,
      DIV = 6,
      TRUE = 7,
      FALSE = 8,
      FINAL = 9,
      VOID = 10,
      NULL = 11,
      LT = 12,
      LE = 13,
      GT = 14,
      GE = 15,
      NEQ = 16,
      EQ = 17,
      ASSIGN = 18,
      LEFTPAR = 19,
      RIGHTPAR = 20,
      SEMICOLON = 21,
      COLON = 22,
      DOT = 23,
      IF = 24,
      ELSE = 25,
      WHILE = 26,
      READ = 27,
      WRITE = 28,
      BREAK = 29,
      INT = 30,
      BOOLEAN = 31,
      RETURN = 32,
      CLASS = 33,
      EXTENDS = 34,
      SUPER = 35,
      THIS = 36,
      NEW = 37,
      PUBLIC = 38,
      PRIVATE = 39,
      COMMA = 40,
      LITERALSTRING = 41,
      STRING = 42,
      AND = 43,
      OR = 44,
      LEFTCURBRACKET = 45,
      RIGHTCURBRACKET = 46,
      NOT = 47,
      LastSymbol = 48;

*/
	Symbol(String name) {
		this.name = name;
	}

	@Override public String toString() {
		return name;
	}


	private String name;



}