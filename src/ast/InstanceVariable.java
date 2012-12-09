package ast;

public class InstanceVariable extends Variable {

    public InstanceVariable( String name, Type type ,boolean isStatic) {
        super(name, type);
        this.isStatic = isStatic;
    }
    private boolean isStatic;
}