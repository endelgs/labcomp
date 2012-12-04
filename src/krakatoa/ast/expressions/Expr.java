package krakatoa.ast.expressions;

import krakatoa.ast.PW;
import krakatoa.ast.types.Type;

abstract public class Expr {
    abstract public void genC( PW pw, boolean putParenthesis );
      // new method: the type of the expression
    abstract public Type getType();
}