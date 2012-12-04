package krakatoa.ast.expressions;

import krakatoa.ast.PW;
import krakatoa.ast.types.Type;

public class NullExpr extends Expr {
    
   public void genC( PW pw, boolean putParenthesis ) {
      pw.printIdent("NULL");
   }
   
   public Type getType() {
      //# corrija
      return null;
   }
}