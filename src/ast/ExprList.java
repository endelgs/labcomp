package ast;

import java.util.*;

public class ExprList {

    public ExprList() {
        v = new ArrayList<Expr>();
    }

    public void addElement( Expr expr ) {
        v.add(expr);
    }
    public Expr getElement(int i){
      return v.get(i);
    }
    public ArrayList<Expr> getList(){
      return v;
    }
    public boolean compareSize(ExprList compare){
      return v.size() == compare.getList().size();
    }
    public boolean compareItems(ExprList compare){
      for(int i = 0; i < v.size(); i++){
        if(v.get(i).getType().getName() != compare.getElement(i).getType().getName())
          return false;
      }
      return true;
    }
    public Iterator<Expr> elements() {
        return v.iterator();
    }
    public int getSize(){
      return v.size();
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
