/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;

/**
 *
 *  @author endel
 */
public class IfStatement extends Statement{
  public IfStatement(){
    
  }
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

  public Statement getIfStatements() {
    return ifStatements;
  }

  public void setIfStatements(Statement ifStatements) {
    this.ifStatements = ifStatements;
  }

  public Statement getElseStatements() {
    return elseStatements;
  }

  public void setElseStatements(Statement elseStatements) {
    this.elseStatements = elseStatements;
  }

  
  private Expr expr;
  private Statement ifStatements;
  private Statement elseStatements;
}
