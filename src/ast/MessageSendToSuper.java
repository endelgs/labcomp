package ast;

public class MessageSendToSuper extends MessageSend { 

  public MessageSendToSuper(ClassDec variable, MethodDec method, ExprList exprList) {
    super(variable, method, exprList);
  }

    public Type getType() { 
        return null;
    }

    public void genC( PW pw, boolean putParenthesis ) {
        
    }
    
}