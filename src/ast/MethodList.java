package ast;

import java.util.*;

public class MethodList {

    public MethodList() {
       methodList = new ArrayList<MethodDec>();
    }

    public void addElement(MethodDec method) {
       methodList.add( method );
    }

    public Iterator<MethodDec> elements() {
    	return this.methodList.iterator();
    }

    public int getSize() {
        return methodList.size();
    }

    private ArrayList<MethodDec> methodList;

}
