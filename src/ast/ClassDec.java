package ast;

public class ClassDec extends Type {
   public ClassDec( String name ) {
      super(name);
   }
   
   public String getCname() {
      return getName();
   }
   public ClassDec getSuperclass() {
    return superclass;
  }

  public void setSuperclass(ClassDec superclass) {
    this.superclass = superclass;
  }

  public InstanceVariableList getInstanceVariableList() {
    return instanceVariableList;
  }
  public void setInstanceVariableList(InstanceVariableList instanceVariableList) {
    this.instanceVariableList = instanceVariableList;
  }
  public MethodList getPublicMethodList() {
    return publicMethodList;
  }

  public void setPublicMethodList(MethodList publicMethodList) {
    this.publicMethodList = publicMethodList;
  }

  public MethodList getPrivateMethodList() {
    return privateMethodList;
  }

  public void setPrivateMethodList(MethodList privateMethodList) {
    this.privateMethodList = privateMethodList;
  }
  public MethodDec getMethod(String name){
    MethodDec methodDec;
    while(publicMethodList.elements().hasNext()){
       methodDec = publicMethodList.elements().next();
      if(methodDec.getName() == name){
        return methodDec;
      }
    }
    return null;
  }
  private String name;
  private ClassDec superclass;
  private InstanceVariableList instanceVariableList;
  private MethodList publicMethodList, privateMethodList;
 // m�todos p�blicos get e set para obter e iniciar as vari�veis acima,
 // entre outros m�todos

  
}
