package ast;


abstract class MessageSend  extends Expr  {

  public MessageSend(ClassDec variable, MethodDec method, ExprList exprList ) {
    this.exprList = exprList;
    this.variable = variable;
    this.method = method;
  }

  public ExprList getExprList() {
    return exprList;
  }

  public void setExprList(ExprList exprList) {
    this.exprList = exprList;
  }

  public ClassDec getVariable() {
    return variable;
  }

  public void setVariable(ClassDec variable) {
    this.variable = variable;
  }

  public MethodDec getMethod() {
    return method;
  }

  public void setMethod(MethodDec method) {
    this.method = method;
  }

  
  private ExprList exprList;
  private ClassDec variable;
  private MethodDec method;
}

