package ast;

import java.util.ArrayList;
import lexer.Symbol;

public class Method {

    public Method( String name, Type type , Symbol qualifier, boolean isStatic) {
        this.name = name;
        this.type = type;
        this.qualifier = qualifier;
        this.isStatic = isStatic;
    }

    public String getName() { return name; }

    public Type getType() {
        return type;
    }

  public Symbol getQualifier() {
    return qualifier;
  }

  public void setQualifier(Symbol qualifier) {
    this.qualifier = qualifier;
  }

  public boolean isIsStatic() {
    return isStatic;
  }

  public void setIsStatic(boolean isStatic) {
    this.isStatic = isStatic;
  }

  public ArrayList<Statement> getStatementList() {
    return statementList;
  }

  public void setStatementList(ArrayList<Statement> statementList) {
    this.statementList = statementList;
  }

  public ParamList getParamList() {
    return paramList;
  }

  public void setParamList(ParamList paramList) {
    this.paramList = paramList;
  }

    private String name;
    private Type type;
    private Symbol qualifier;
    private boolean isStatic = false;
    private ArrayList<Statement> statementList;
    private ParamList paramList;
}