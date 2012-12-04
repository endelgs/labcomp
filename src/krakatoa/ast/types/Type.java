package krakatoa.ast.types;

import krakatoa.ast.types.VoidType;
import krakatoa.ast.types.BooleanType;
import krakatoa.ast.types.IntType;
import krakatoa.ast.types.StringType;
import krakatoa.ast.types.UndefinedType;

abstract public class Type {
  public static Type booleanType = new BooleanType();
  public static Type intType = new IntType();
  public static Type stringType = new StringType();
  public static Type voidType = new VoidType();
  public static Type undefinedType = new UndefinedType();
  
  /*
    public Type( String name ) {
        this.name = name;
    }

    public static Type booleanType = new BooleanType();
    public static Type intType = new IntType();
    public static Type stringType = new StringType();
    public static Type voidType = new VoidType();
    public static Type undefinedType = new UndefinedType();

    public String getName() {
        return name;
    }

    abstract public String getCname();

    private String name;
    * */
}
