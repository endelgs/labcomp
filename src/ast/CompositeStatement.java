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
public class CompositeStatement {
  public CompositeStatement(ArrayList<Statement> statementList){
    this.statementList = statementList;
  }
  private ArrayList<Statement> statementList;
}