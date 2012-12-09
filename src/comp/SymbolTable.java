package comp;

import java.util.*;

import ast.*;

public class SymbolTable {

    public SymbolTable() {
        globalTable = new Hashtable<String, ClassDec>();
        localTable  = new Hashtable<String, Variable>();
    }

    public Object putInGlobal( String key, ClassDec value ) {
       return globalTable.put(key, value);
    }

    public ClassDec getInGlobal( String key ) {
       return (ClassDec ) globalTable.get(key);
    }

    public Variable putInLocal( String key, Variable value ) {
       return (Variable ) localTable.put(key, value);
    }

    public Variable getInLocal( String key ) {
       return (Variable ) localTable.get(key);
    }

    public Object get( String key ) {
        // returns the object corresponding to the key.
        Object result;
        if ( (result = localTable.get(key)) != null ) {
              // found local identifier
            return result;
        }
        else {
              // global identifier, if it is in globalTable
            return globalTable.get(key);
        }
    }

    public void removeLocalIdent() {
           // remove all local identifiers from the table
         localTable.clear();
    }


    private Hashtable<String, ClassDec> globalTable;
    private Hashtable<String, Variable> localTable;
}
