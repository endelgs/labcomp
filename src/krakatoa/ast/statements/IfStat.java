/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krakatoa.ast.statements;

import krakatoa.ast.expressions.Expression;
import krakatoa.ast.statements.Statement;

/**
 *
 * @author endel
 */
public class IfStat {
  Expression expr;
  Statement ifStatement;
  Statement elseStatement;
}
