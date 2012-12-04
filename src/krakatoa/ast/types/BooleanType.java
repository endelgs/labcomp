package krakatoa.ast.types;

public class BooleanType extends Type {

   public BooleanType() { 
     //super("bool"); 
   }

   //@Override
  public String getCname() {
      return "int";
   }

}
