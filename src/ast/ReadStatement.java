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
public class ReadStatement extends Statement{
  public ReadStatement(){
    variableList = new ArrayList<Variable>();
  }
  @Override
  public void genC(PW pw) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public ArrayList<Variable> getVariableList() {
    return variableList;
  }

  public void setVariableList(ArrayList<Variable> variableList) {
    this.variableList = variableList;
  }
  
  private ArrayList<Variable> variableList;
}
