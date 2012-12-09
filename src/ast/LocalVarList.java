package ast;

import java.util.*;

public class LocalVarList extends Statement{

    public LocalVarList() {
       localList = new ArrayList<Variable>();
    }

    public void addElement(Variable v) {
       localList.add(v);
    }

    public Iterator<Variable> elements() {
        return localList.iterator();
    }

    public int getSize() {
        return localList.size();
    }

    private ArrayList<Variable> localList;

  @Override
  public void genC(PW pw) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
