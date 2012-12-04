package krakatoa.ast.expressions;

import krakatoa.ast.PW;
import krakatoa.ast.types.Type;
import krakatoa.lexer.Symbol;

public class UnaryExpr extends Expr {

    public UnaryExpr( Expr expr, Symbol op ) {
        this.expr = expr;
        this.op = op;
    }

    @Override
	public void genC( PW pw, boolean putParenthesis ) {
        switch ( op ) {
            case PLUS :
              pw.print("+");
              break;
            case MINUS :
              pw.print("-");
              break;
            case NOT :
              pw.print("!");
              break;
        }
        expr.genC(pw, false);
    }

    @Override
	public Type getType() {
        return expr.getType();
    }

    private Expr expr;
    private Symbol op;
}
