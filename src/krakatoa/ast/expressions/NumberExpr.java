package krakatoa.ast.expressions;

import krakatoa.ast.PW;
import krakatoa.ast.types.Type;

public class NumberExpr extends Expr {
    
    public NumberExpr( int value ) { 
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    public void genC( PW pw, boolean putParenthesis ) {
        pw.printIdent(value + "");
    }
    
    public Type getType() {
        return Type.intType;
    }
    
    private int value;
}
