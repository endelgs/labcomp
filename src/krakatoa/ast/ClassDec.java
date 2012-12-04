package krakatoa.ast;

import krakatoa.ast.types.Type;
import java.util.ArrayList;

public class ClassDec extends Type {
   public ClassDec( String name ) {
      //super(name);
   }
   
   /*public String getCname() {
      //return getName();
   }*/

  public Id getSuperclass() {
    return superclass;
  }

  public void setSuperclass(Id superclass) {
    this.superclass = superclass;
  }
  private Id name;

  public Id getName() {
    return name;
  }

  public void setName(Id name) {
    this.name = name;
  }

  public ArrayList<Member> getInstanceVariableList() {
    return instanceVariableList;
  }

  public void setInstanceVariableList(ArrayList<Member> instanceVariableList) {
    this.instanceVariableList = instanceVariableList;
  }

  public ArrayList<Member> getPublicMethodList() {
    return publicMethodList;
  }

  public void setPublicMethodList(ArrayList<Member> publicMethodList) {
    this.publicMethodList = publicMethodList;
  }

  public ArrayList<Member> getPrivateMethodList() {
    return privateMethodList;
  }

  public void setPrivateMethodList(ArrayList<Member> privateMethodList) {
    this.privateMethodList = privateMethodList;
  }

  public boolean isIsStatic() {
    return isStatic;
  }

  public void setIsStatic(boolean isStatic) {
    this.isStatic = isStatic;
  }
  public void addMember()
  private Id superclass;
  private ArrayList<Member> instanceVariableList;
  private ArrayList<Member> publicMethodList, privateMethodList;
  private boolean isStatic = false;
  // metodos publicos get e set para obter e iniciar as variaveis acima,
  // entre outros metodos
}
