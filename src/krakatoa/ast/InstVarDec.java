package krakatoa.ast;

import krakatoa.ast.types.Type;
import java.util.*;

public class InstVarDec {

    public InstVarDec() {
       idList = new ArrayList<Id>();
    }

    public void addElement(Id instanceVariable) { 
       idList.add( instanceVariable );
    }

    public Iterator<Id> elements() {
    	return this.idList.iterator();
    }

    public int getSize() {
        return idList.size();
    }
    private Type type;
    private ArrayList<Id> idList;

}
