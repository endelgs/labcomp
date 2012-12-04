package krakatoa.ast.expressions;

import java.util.*;
import krakatoa.ast.PW;

public class ExprList {

    public ExprList() {
        v = new ArrayList<Expr>();
    }

    public void addElement( Expr expr ) {
        v.add(expr);
    }

    public void genC( PW pw ) {

        int size = v.size();
        for ( Expr e : v ) {
        	e.genC(pw, false);
            if ( --size > 0 )
                pw.print(", ");
        }
    }

    private ArrayList<Expr> v;

}
