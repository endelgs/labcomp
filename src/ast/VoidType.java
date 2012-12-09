package ast;

public class VoidType extends Type {
    
    public VoidType() {
        super("void");
    }
    
   public String getCname() {
      return "void";
   }

}