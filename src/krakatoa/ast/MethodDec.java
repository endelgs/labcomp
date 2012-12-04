/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krakatoa.ast;

import krakatoa.ast.statements.Statement;
import krakatoa.ast.types.ReturnType;
import java.util.ArrayList;

/**
 *
 * @author endel
 */
public class MethodDec {
  private Qualifier qualifier;
  private ReturnType returnType;
  private Id id;
  private FormalParamDec formalParamDec;
  private ArrayList<Statement> statementList;
}
