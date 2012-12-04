package krakatoa.comp;


import krakatoa.lexer.Lexer;
import krakatoa.ast.expressions.ParenthesisExpr;
import krakatoa.ast.Id;
import krakatoa.ast.Variable;
import krakatoa.ast.expressions.NullExpr;
import krakatoa.ast.ClassDec;
import krakatoa.ast.types.Type;
import krakatoa.ast.Program;
import krakatoa.ast.statements.Statement;
import krakatoa.ast.expressions.Expr;
import krakatoa.ast.expressions.BooleanExpr;
import krakatoa.ast.expressions.ExprList;
import krakatoa.ast.expressions.UnaryExpr;
import krakatoa.ast.expressions.SignalExpr;
import krakatoa.ast.expressions.NumberExpr;
import krakatoa.ast.expressions.CompositeExpr;
import krakatoa.ast.expressions.LiteralStringExpr;
import java.io.*;
import java.util.*;
import krakatoa.ast.Id;
import krakatoa.ast.Member;
import krakatoa.ast.ObjectCreation;
import krakatoa.lexer.Symbol;



public class Compiler {

      // compile must receive an input with an character less than
      // p_input.lenght
   public Program compile( char []input, PrintWriter outError ) {

       error = new CompilerError( new PrintWriter(outError) );
       symbolTable = new SymbolTable();
       lexer = new Lexer(input, error);
       error.setLexer(lexer);


       Program program = null;
       try {
          lexer.nextToken();
          if ( lexer.token == Symbol.EOF )
             error.show("Unexpected end of file");
          program = program();
          if ( lexer.token != Symbol.EOF ) {
             program = null;
             error.show("End of file expected");
          }
       } catch ( Exception e ) {
              // the below statement prints the stack of called methods.
              // of course, it should be removed if the compiler were
              // a production compiler.

              //e.printStackTrace();
          program = null;
       }

       return program;
   }

   // OK
   private Program program() {
     // Program ::=  ClassDec { ClassDec }

      ArrayList<ClassDec> classes = new ArrayList<ClassDec>();
      classes.add(classDec());
      
      while ( lexer.token == Symbol.CLASS )
         classes.add(classDec());
      return new Program(classes);
   }

   private ClassDec classDec() {
      // Note que os metodos desta classe nao correspondem exatamente as regras
      // da gramatica. Este metodo classDec, por exemplo, implementa
      // a producao ClassDec (veja abaixo) e partes de outras producoes.

      /* ClassDec ::=   ``class'' Id [ ``extends''  Id ]
                        "{"   MemberList "}"
         MemberList ::= { Member }
         Member ::= InstVarDec | MethodDec
         InstVarDec ::= [ "static"  ] "private"  Type  IdList  ";"
         MethodDec ::= Qualifier ReturnType Id "("[ FormalParamDec ]  ")"
                       "{"  StatementList "}"
         Qualifier} ::=  [ "static"  ] ( "private" |  "public" )
      */
      if ( lexer.token != Symbol.CLASS )
         error.show("'class' expected");
      lexer.nextToken();
      if ( lexer.token != Symbol.IDENT )
         error.show(CompilerError.ident_expected);
      String className = lexer.getStringValue();
      ClassDec cDec = new ClassDec(className);
      
      lexer.nextToken();
      if ( lexer.token == Symbol.EXTENDS ) {
         lexer.nextToken();
         if ( lexer.token != Symbol.IDENT )
            error.show(CompilerError.ident_expected);
         
         // setando a superclasse da classe atual
         String superclassName = lexer.getStringValue();
         cDec.setSuperclass(new Id(superclassName));
         
         lexer.nextToken();
      }
      if ( lexer.token != Symbol.LEFTCURBRACKET )
         error.show("{ expected", true);
      lexer.nextToken();
      
      // ATE AQUI NAO TEM O QUE MEXER
      ArrayList<Member> memberList = new ArrayList<Member>();
      boolean isStatic;

      while ( lexer.token == Symbol.PRIVATE ||
              lexer.token == Symbol.PUBLIC ||
              lexer.token == Symbol.STATIC) {
        isStatic = false;
        if(lexer.token == Symbol.STATIC){
         isStatic = true;
         lexer.nextToken();
        }
        if(lexer.token == )
         
      }
      if ( lexer.token != Symbol.RIGHTCURBRACKET )
         error.show("public/private or \"}\" expected");
      lexer.nextToken();
      return null;
   }

   private ArrayList<Member> member(){
     Member member;
         Symbol qualifier;
         
         if(lexer.token == Symbol.STATIC){
            lexer.nextToken();
          }
         switch( lexer.token ) {
            
            case PRIVATE:
               lexer.nextToken();
               qualifier = Symbol.PRIVATE;
               break;
            case PUBLIC:
               lexer.nextToken();
               qualifier = Symbol.PUBLIC;
               break;
            default :
               error.show("private, or public expected");
               qualifier = Symbol.PUBLIC;
         }
         Type t = type();
         if ( lexer.token != Symbol.IDENT )
            error.show("Identifier expected");
         String name = lexer.getStringValue();
         lexer.nextToken();
         if ( lexer.token == Symbol.LEFTPAR )
            methodDec(t, name, qualifier,isStatic);
         else if ( qualifier != Symbol.PRIVATE )
            error.show("Attempt to declare a public instance variable");
         else
            instanceVarDec(t, name);
   }
   private void instanceVarDec( Type type, String name ) {
      //   InstVarDec ::= [ "static"  ] "private"  Type  IdList  ";"


      while ( lexer.token == Symbol.COMMA ) {
         lexer.nextToken();
         if ( lexer.token != Symbol.IDENT )
            error.show("Identifier expected");
         String variableName = lexer.getStringValue();
         lexer.nextToken();
      }
      if ( lexer.token != Symbol.SEMICOLON )
         error.show(CompilerError.semicolon_expected);
      lexer.nextToken();
   }



   private void methodDec(Type type, String name, Symbol qualifier ,boolean isStatic) {
      /*   MethodDec ::= Qualifier ReturnType Id "("[ FormalParamDec ]  ")"
                       "{"  StatementList "}"
      */

      lexer.nextToken();
      if ( lexer.token != Symbol.RIGHTPAR )
         formalParamDec();
      if ( lexer.token != Symbol.RIGHTPAR )
         error.show(") expected");

      lexer.nextToken();
      if ( lexer.token != Symbol.LEFTCURBRACKET )
         error.show("{ expected");

      lexer.nextToken();
      statementList();
      if ( lexer.token != Symbol.RIGHTCURBRACKET )
         error.show("} expected");

      lexer.nextToken();

   }




   private void localDec( Type type ) {
      // LocalDec ::= Type IdList ";"

      if ( lexer.token != Symbol.IDENT )
         error.show("Identifier expected");
      Variable v = new Variable(lexer.getStringValue(), type);
      lexer.nextToken();
      while ( lexer.token == Symbol.COMMA ) {
         lexer.nextToken();
         if ( lexer.token != Symbol.IDENT )
            error.show("Identifier expected");
         v = new Variable(lexer.getStringValue(), type);
         lexer.nextToken();
      }
   }




   private void formalParamDec() {
      //  FormalParamDec ::= ParamDec { "," ParamDec }

      paramDec();
      while ( lexer.token == Symbol.COMMA ) {
         lexer.nextToken();
         paramDec();
      }
   }

   private void paramDec() {
      // ParamDec ::= Type Id

      type();
      if ( lexer.token != Symbol.IDENT )
         error.show("Identifier expected");
      lexer.nextToken();
   }


    private Type type() {
        // Type ::= BasicType | Id
        Type result;

        switch ( lexer.token ) {
            case VOID :
               result = Type.voidType;
            break;
            case INT :
               result = Type.intType;
               break;
            case BOOLEAN :
               result = Type.booleanType;
               break;
            case STRING :
               result = Type.stringType;
               break;
            case IDENT :
               //# corrija: fa�a uma busca na TS para buscar a classe
               // IDENT deve ser uma classe.
               result = null;
               break;
            default :
              error.show("Type expected");
              result = Type.undefinedType;
        }
        lexer.nextToken();
        return result;
    }




    private void compositeStatement() {

        lexer.nextToken();
        statementList();
        if ( lexer.token != Symbol.RIGHTCURBRACKET )
          error.show("} expected");
        else
          lexer.nextToken();
    }



   private void statementList() {
       // CompStatement ::= "{" { Statement } "}"
       Symbol tk;
          // statements always begin with an identifier, if, read, write, ...
       while ( (tk = lexer.token) != Symbol.RIGHTCURBRACKET &&
               tk != Symbol.ELSE )
          statement();
   }



   private void statement() {
     /*
        Statement ::= Assignment ``;'' | IfStat |WhileStat
            |  MessageSend ``;''  | ReturnStat ``;''
            |  ReadStat ``;'' | WriteStat ``;'' | ``break'' ``;''
            | ``;'' | CompStatement | LocalDec
     */

      switch (lexer.token) {
         case THIS :
         case IDENT :
         case SUPER :
            assignmentMessageSendLocalVarDecStatement();
            break;
         case INT :
             lexer.nextToken();
             localDec(Type.intType);
             break;
          case BOOLEAN :
             lexer.nextToken();
             localDec(Type.booleanType);
             break;
          case STRING:
             lexer.nextToken();
             localDec(Type.stringType);
             break;
         case RETURN :
            returnStatement();
            break;
         case READ :
            readStatement();
            break;
         case WRITE :
            writeStatement();
            break;
         case IF :
            ifStatement();
            break;
         case BREAK :
            breakStatement();
            break;
         case WHILE :
            whileStatement();
            break;
         case SEMICOLON :
            nullStatement();
            break;
         case LEFTCURBRACKET :
            compositeStatement();
            break;
         default :
            error.show("Statement expected");
      }
   }


   private ExprList getRealParameters() {
      ExprList anExprList = null;

      if ( lexer.token != Symbol.LEFTPAR )
         error.show("( expected");
      lexer.nextToken();
      if ( startExpr(lexer.token) )
         anExprList = exprList();
      if ( lexer.token != Symbol.RIGHTPAR )
         error.show(") expected");
      lexer.nextToken();
      return anExprList;
   }


   private void whileStatement() {

      lexer.nextToken();
      if ( lexer.token != Symbol.LEFTPAR )
         error.show("( expected");
      lexer.nextToken();
      expr();
      if ( lexer.token != Symbol.RIGHTPAR )
         error.show(") expected");
      lexer.nextToken();
      statement();
   }

   private void ifStatement() {

      lexer.nextToken();
      if ( lexer.token != Symbol.LEFTPAR )
         error.show("( expected");
      lexer.nextToken();
      expr();
      if ( lexer.token != Symbol.RIGHTPAR )
         error.show(") expected");
      lexer.nextToken();
      statement();
      if ( lexer.token == Symbol.ELSE ) {
         lexer.nextToken();
         statement();
      }
   }

   private void returnStatement() {

      lexer.nextToken();
      expr();
      if ( lexer.token != Symbol.SEMICOLON )
         error.show(CompilerError.semicolon_expected);
      lexer.nextToken();
   }


   private void readStatement() {
      lexer.nextToken();
      if ( lexer.token != Symbol.LEFTPAR )
         error.show("( expected");
      lexer.nextToken();
      while ( true ) {
         if ( lexer.token == Symbol.THIS ) {
            lexer.nextToken();
            if ( lexer.token != Symbol.DOT )
               error.show(". expected");
            lexer.nextToken();
         }
         if ( lexer.token != Symbol.IDENT )
            error.show(CompilerError.ident_expected);

         String name = (String ) lexer.getStringValue();
         lexer.nextToken();
         if ( lexer.token == Symbol.COMMA )
            lexer.nextToken();
         else
            break;
      }

      if ( lexer.token != Symbol.RIGHTPAR )
         error.show(") expected");
      lexer.nextToken();
      if ( lexer.token != Symbol.SEMICOLON )
         error.show(CompilerError.semicolon_expected);
      lexer.nextToken();
   }


   private void writeStatement() {

      lexer.nextToken();
      if ( lexer.token != Symbol.LEFTPAR )
         error.show("( expected");
      lexer.nextToken();
      exprList();
      if ( lexer.token != Symbol.RIGHTPAR )
          error.show(") expected");
      lexer.nextToken();
      if ( lexer.token != Symbol.SEMICOLON )
         error.show(CompilerError.semicolon_expected);
      lexer.nextToken();
   }


   private void breakStatement() {
      lexer.nextToken();
      if ( lexer.token != Symbol.SEMICOLON )
         error.show(CompilerError.semicolon_expected);
      lexer.nextToken();
   }


   private void nullStatement() {
      lexer.nextToken();
   }


   private ExprList exprList() {
      // ExpressionList ::= Expression { "," Expression }


      ExprList anExprList = new ExprList();
      anExprList.addElement(expr());
      while ( lexer.token == Symbol.COMMA ) {
         lexer.nextToken();
         anExprList.addElement(expr());
      }
      return anExprList;
   }



   private Expr expr() {

      Expr left = simpleExpr();
      Symbol op = lexer.token;
      if ( op == Symbol.EQ || op == Symbol.NEQ ||
           op == Symbol.LE || op == Symbol.LT ||
           op == Symbol.GE || op == Symbol.GT  ) {
         lexer.nextToken();
         Expr right = simpleExpr();
         left = new CompositeExpr( left, op, right );
      }
      return left;
   }


   private Expr simpleExpr() {
      Symbol op;

      Expr left = term();
      while ( (op = lexer.token) == Symbol.MINUS || op == Symbol.PLUS ||
              op == Symbol.OR ) {
         lexer.nextToken();
         Expr right = term();
         left = new CompositeExpr( left, op, right );
      }
      return left;
   }


   private Expr term() {
      Symbol op;

      Expr left = signalFactor();
      while ( (op = lexer.token) == Symbol.DIV || op == Symbol.MULT ||
              op == Symbol.AND ) {
         lexer.nextToken();
         Expr right = signalFactor();
         left = new CompositeExpr( left, op, right );
      }
      return left;
   }


   private Expr signalFactor() {
      Symbol op;
      if ( (op = lexer.token) == Symbol.PLUS || op == Symbol.MINUS ) {
         lexer.nextToken();
         return new SignalExpr(op, factor());
      }
      else
         return factor();
   }



    private Expr factor() {
      /*
        Factor ::= BasicValue | RightValue | MessageSend  | "(" Expression ")"
           | "!" Factor | "null" | ObjectCreation
        BasicValue ::= IntValue | BooleanValue | StringValue
        BooleanValue ::= "true" | "false"
        RightValue ::= "this" [ "." Id ] | Id
        MessageSend ::= ReceiverMessage "." Id "("  [ ExpressionList ] ")"
        ReceiverMessage ::=  "super" | Id | "this" | "this" "."  Id
        ObjectCreation ::= ``new" Id ``("  ``)"
      */

      Expr e;
      Variable aVariable;
      ClassDec aClass;
      //MethodDec aMethod;
      Id anInstanceVariable;

      switch ( lexer.token ) {
         case NOT :
           lexer.nextToken();
           e = expr();
           return new UnaryExpr( e, Symbol.NOT );
         case LITERALSTRING :
           String literalString = lexer.getLiteralStringValue();
           lexer.nextToken();
           return new LiteralStringExpr(literalString);
         case TRUE :
           lexer.nextToken();
           return BooleanExpr.True;
         case FALSE :
           lexer.nextToken();
           return BooleanExpr.False;
         case LEFTPAR :
            lexer.nextToken();
            e = expr();
            if ( lexer.token != Symbol.RIGHTPAR )
               error.show(") expected");
            lexer.nextToken();
            return new ParenthesisExpr(e);
         case NULL :
            lexer.nextToken();
            return new NullExpr();
         case NUMBER :
            return number();
         case NEW :
            // object creation
            lexer.nextToken();
            if ( lexer.token != Symbol.IDENT )
               error.show("Identifier expected");

            String className = lexer.getStringValue();
            /*
               // encontre a classe className in symbol table
            ClassDec aClass = symbolTable.getInGlobal(className);
            if ( aClass == null ) ...
            */
            aClass = symbolTable.getInGlobal(className);
            // Caso a classe nao exista, mostra erro
            if ( aClass == null )
              error.show("Class "+className+" not found");

            lexer.nextToken();
            if ( lexer.token != Symbol.LEFTPAR )
               error.show("( expected");
            lexer.nextToken();
            if ( lexer.token != Symbol.RIGHTPAR )
               error.show(") expected");
            lexer.nextToken();
            /* return an object representing the creation of an object
              something as
            return new Cria_um_objeto(aClass);
            � importante n�o utilizar className, uma string e sim aClass, um objeto.
            */
            return null;//new ObjectCreation(aClass);
         default :
            String variableName, methodName;
            ExprList exprList;
               /* there are seven cases to consider :
                     super.m()
                     this.x
                     this
                     this.m()
                     this.x.m();
                     x
                     x.m()

                  in which x is either a variable or
                  an instance variable and m is a method
               */
            switch ( lexer.token ) {
               case SUPER :
                    // expression of the kind "super.m()"
                  lexer.nextToken();
                  if ( lexer.token != Symbol.DOT )
                     error.show(". expected");
                  lexer.nextToken();
                  if ( lexer.token != Symbol.IDENT )
                     error.show(CompilerError.ident_expected);
                  methodName = lexer.getStringValue();
                  lexer.nextToken();
                  exprList = getRealParameters();
                  //#  corrija
                  /*
                    deve existir uma variavel de instancia currentClass.
                    aClass = currentClass.getSuperclass();
                    if ( aClass == null )
                       ...
                    aMethod = aClass.getMethod(methodName);
                    if ( aMethod == null )
                       ...

                    return new MessageSendToSuper(
                         aClass, aMethod, exprList);
                  */
                  
                  break;
               case THIS :
                  lexer.nextToken();
                  if ( lexer.token != Symbol.DOT ) {
                     // expression of the kind "this"
                     //# corrija
                     /*
                        Verifique se nao estamos em um metodo estatico
                        o construtor da classe ThisExpr deve tomar a classe corrente
                        como parametro. Por que ?
                        return new ThisExpr(currentClass);
                     */
                  }
                  else {
                     lexer.nextToken();
                     if ( lexer.token != Symbol.IDENT )
                        error.show(CompilerError.ident_expected);
                        // it may be method name or an instance variable
                     String ident = lexer.getStringValue();
                     lexer.nextToken();
                     switch ( lexer.token ) {
                        case LEFTPAR :
                           // expression of the kind "this.m()"
                           //# corrija
                           exprList = getRealParameters();
                           /*
                           procure o m�todo ident na classe corrente:
                               aMethod = currentClass.searchMethod(ident);
                               if ( aMethod == null )
                                  ...
                           confira se aMethod pode aceitar os par�metros de exprList.
                           return new MessageSendToSelf( aMethod, exprList );
                           */
                           break;
                        case DOT :
                           // expression of the kind "this.x.m()"
                           //# corrija
                           lexer.nextToken();
                           if ( lexer.token != Symbol.IDENT )
                              error.show(CompilerError.ident_expected);
                           methodName = lexer.getStringValue();
                           lexer.nextToken();
                           exprList = getRealParameters();
                           /*
                             em this.x.m(), x est� em ident e m em methodName
                             procure por x na lista de vari�veis de inst�ncia da classe corrente:
                                anInstanceVariable = currentClass.searchInstanceVariable(ident);
                                if ( anInstanceVariable == null )
                                   ...
                             pegue a classe declarada de x, o tipo de x:
                                if ( ! (anInstanceVariable.getType() instanceof ClassDec) )
                                    ... // tipo de x n�o � uma classe, erro
                             confira se a classe de x possui m�todo m:
                                aClass = (ClassDec ) anInstanceVariable.getType();
                                aMethod = aClass.searchMethod(methodName);
                                if ( aMethod == null )
                                   ...

                           return new MessageSendToVariable( anInstanceVariable, aMethod, exprList );

                           */
                        default :
                           // expression of the kind "this.x"
                           //# corrija
                           /*
                              procure x na lista de vari�veis de inst�ncia da classe corrente:
                                 anInstanceVariable = currentClass.searchInstanceVariable(ident);
                                 if ( anInstanceVariable == null )
                                    ...
                           return new VariableExpr( anInstanceVariable )
                           */
                     }

                  }
                  break;
               case IDENT :
                  variableName = lexer.getStringValue();
                  lexer.nextToken();
                  if ( lexer.token != Symbol.DOT ) {
                     // expression of the kind "x"
                     //# corrija
                     /*
                       if ( (aVariable = symbolTable.get...(variableName)) == null )
                           ...
                       return new VariableExpr(aVariable);
                     */
                  }
                  else {
                       // expression of the kind "x.m()"
                     lexer.nextToken();  // eat the dot
                     switch ( lexer.token ) {
                        case IDENT :
                           methodName = lexer.getStringValue();
                           lexer.nextToken();
                           exprList = getRealParameters();
                           //#  corrija
                           /*

                            if ( (aVariable = symbolTable.getInLocal(variableName)) != null ) {
                               // x is a variable
                               Type t = aVariable.getType();
                               teste se t � do tipo ClassDec nesta linha
                               aClass = (ClassDec ) t;
                               verifique se a classe aClass possui um m�todo chamado methodName
                               que pode receber como par�metros as express�es de exprList.
                               Algo como (apenas o in�cio):
                                  aMethod = aClass.searchMethod(methodName);
                                  ...
                               return new MessageSendToVariable(
                                 aVariable, aMethod, exprList);

                            }
                            else {
                                // em "x.m()", x is not a variable. Should be a class name
                            	if ( (aClass = symbolTable.getInGlobal(variableName)) == null )
                          	       ...
                                nesta linha, verifique se methodName � um m�todo est�tico da
                                classe aClass que pode aceitar como par�metros as express�es de exprList.
                                Algo como (apenas o in�cio):
                                   aStaticMethod = aClass.searchStaticMethod(methodName);
                                   ...
                                return new MessageSendStatic(aClass, aStaticMethod, exprList);
                            }


                           */

                           break;
                        default :
                           error.show(CompilerError.ident_expected);
                     }
                  }
                  break;
               default :
                  error.show(CompilerError.ident_expected);
               }
               return null;
         }

   }


   private NumberExpr number() {

      NumberExpr e = null;

          // the number value is stored in lexer.getToken().value as an object of Integer.
          // Method intValue returns that value as an value of type int.
      int value = lexer.getNumberValue();
      lexer.nextToken();
      return new NumberExpr( value );
   }


   private Statement assignmentMessageSendLocalVarDecStatement() {
	     /*
	       Assignment ::= LeftValue "=" Expression
	       LeftValue} ::= [ "this" "." ] Id
	       MessageSend ::= ReceiverMessage "." Id "("  [ ExpressionList ] ")"
	       ReceiverMessage ::=  "super" | Id | "this" | "this" "."  Id
	       LocalDec ::= Type IdList ";"
	     */


	      // an assignment, a message send or a local variable declaration
	      /*
	      ##########################################################################
	      ##########################################################################
	                          IMPORTANTE:
	           a implementa��o deste m�todo � muit�ssimo parecido com o do m�todo
	      factor. Neste m�todo, factor, coloquei **muito** mais partes implementadas
	      do que neste m�todo assignmentMessageSendLocalVarDecStatement. A grande
	      diferen�a entre os dois m�todos � que factor analisa uma express�o e
	      assignmentMessageSendLocalVarDecStatement analisa uma instru��o. Isto �, um
	      envio de mensagem "x.m()" em factor deve retornar um valor e em
	      assignmentMessageSendLocalVarDecStatement n�o deve retornar nada.
	           Resumindo: fa�a factor primeiro e depois copie e cole grande parte do
	      que voc� fez para este m�todo.

	      ##########################################################################
	      ##########################################################################

	      */


	      String methodName, variableName;
	      ExprList exprList;
	      Statement result = null;
	      /*
	         there are eight possibilities:
	            this.id()
	            this.id = expr
	            this.id.id()
	            super.id()
	            id = expr
	            id.id()

	            Id IdList  // Id is a type
	      */

	      switch ( lexer.token ) {
	         case THIS :
	            lexer.nextToken();
	            if ( lexer.token != Symbol.DOT )
	               error.show(". expected");
	            lexer.nextToken();
	            if ( lexer.token != Symbol.IDENT )
	               error.show(CompilerError.ident_expected);
	            String ident = lexer.getStringValue();
	            lexer.nextToken();
	            switch ( lexer.token ) {
	               case ASSIGN :
	                     // this.id = expr
	                  lexer.nextToken();
	                  Expr anExpr = expr();
	                  //# corrija
	                  /* result = new AssignmentStatement( pointer to instance variable,
	                        anExpr); */
	                  break;
	               case DOT :
	                     // this.id.id()
	                  lexer.nextToken();
	                  if ( lexer.token != Symbol.IDENT )
	                     error.show(CompilerError.ident_expected);
	                  methodName = lexer.getStringValue();
	                  lexer.nextToken();
	                  exprList = getRealParameters();
	                  //# corrija
	                  /* result = new MessageSendStatement(
	                        new MessageSendToVariable( pointer to variable,
	                           pointer to method, exprList) );  */
	                  break;
	               case LEFTPAR :
	                     // this.id()
	                  exprList = getRealParameters();
	                  //# corrija
	                  /* result = new MessageSendStatement(
	                       new MessageSendToSelf( pointer to method, exprList ) ); */
	                  break;
	               default :
	                  error.show(CompilerError.ident_expected);
	            }
	            break;
	         case SUPER :
	               // super.id()
	            lexer.nextToken();
	            if ( lexer.token != Symbol.DOT )
	               error.show(". expected");
	            lexer.nextToken();
	            if ( lexer.token != Symbol.IDENT )
	               error.show(CompilerError.ident_expected);
	            methodName = lexer.getStringValue();
	            lexer.nextToken();
	            exprList = getRealParameters();
	            //# corrija
	            // result = new MessageSendStatement(
	            //     new MessageSendToSuper( pointer to class, pointer to method, exprList) );
	            break;
	         case IDENT :
	            variableName = lexer.getStringValue();
	            lexer.nextToken();
	            switch ( lexer.token ) {
	               case ASSIGN :
	                     // id = expr
	                  lexer.nextToken();
	                  Expr anExpr = expr();
	                  //# corrija
	                  /* result = new AssignmentStatement( pointer to variable,
	                        anExpr ); */
	                  break;
	               case IDENT :
	                    // id id;
	                    // variableName id

	                    // variableName must be the name of a class
	                    // replace null in the statement below by
	                    // a point to the class named variableName.
	                    // A search in the symbol table is necessary.
	                  localDec(null);
	                  break;
	               case DOT :
	                     // id.id()
	                  lexer.nextToken();
	                  methodName = lexer.getStringValue();
	                  lexer.nextToken();
	                  exprList = getRealParameters();
	                  //# corrija
	                  /* result = new MessageSendStatement(
	                         new MessageSendToVariable( pointer to variable,
	                               pointer to method, exprList ) ); */
	                  break;
	               default :
	                  error.show(". or = expected");
	            }
	            break;
	         default :
	            error.show("'this', 'super', basic type or identifier expected");
	      }
	      if ( lexer.token != Symbol.SEMICOLON )
	         error.show(CompilerError.semicolon_expected);
	      lexer.nextToken();
	      return result;
	   }



   private boolean startExpr( Symbol token ) {

      return
             lexer.token == Symbol.FALSE ||
             lexer.token == Symbol.TRUE ||
             lexer.token == Symbol.NOT ||
             lexer.token == Symbol.THIS ||
             lexer.token == Symbol.NUMBER ||
             lexer.token == Symbol.SUPER ||
             lexer.token == Symbol.LEFTPAR ||
             lexer.token == Symbol.NULL ||
             lexer.token == Symbol.IDENT ||
             lexer.token == Symbol.LITERALSTRING;

   }


   private SymbolTable symbolTable;
   private Lexer lexer;
   private CompilerError error;

}
