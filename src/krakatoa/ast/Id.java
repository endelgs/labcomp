/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krakatoa.ast;

import krakatoa.ast.types.Type;

/**
 *
 * @author endel
 */
public class Id extends Type{
  private String name;
  public Id(String name){
    this.name = name;
  }
}
