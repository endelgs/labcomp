package krakatoa.ast.expressions;

import krakatoa.ast.PW;
import krakatoa.ast.types.Type;

public class BooleanExpr extends Expr {

    public BooleanExpr( boolean value ) {
        this.value = value;
    }

    @Override
	public void genC( PW pw, boolean putParenthesis ) {
       pw.print( value ? "1" : "0" );
    }

    @Override
	public Type getType() {
        return Type.booleanType;
    }

    public static BooleanExpr True  = new BooleanExpr(true);
    public static BooleanExpr False = new BooleanExpr(false);

    private boolean value;
}
