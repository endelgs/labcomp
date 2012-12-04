package krakatoa.ast.types;

public class UndefinedType extends Type {
    // variables that are not declared have this type
    
   public UndefinedType() { }
   
   public String getCname() {
      return "int";
   }
   
}
