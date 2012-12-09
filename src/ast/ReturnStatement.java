/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 *  @author endel
 */
public class ReturnStatement extends Statement{

  @Override
  public void genC(PW pw) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public Expr getExpr() {
    return expr;
  }

  public void setExpr(Expr expr) {
    this.expr = expr;
  }
  
  private Expr expr;
}
