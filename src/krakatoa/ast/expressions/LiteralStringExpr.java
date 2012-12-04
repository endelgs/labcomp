package krakatoa.ast.expressions;

import krakatoa.ast.PW;
import krakatoa.ast.types.Type;

public class LiteralStringExpr extends Expr {
    
    public LiteralStringExpr( String literalString ) { 
        this.literalString = literalString;
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
        pw.print(literalString);
    }
    
    public Type getType() {
        return Type.stringType;
    }
    
    private String literalString;
}
