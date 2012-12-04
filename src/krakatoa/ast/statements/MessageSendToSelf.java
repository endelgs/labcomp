package krakatoa.ast.statements;

import krakatoa.ast.PW;
import krakatoa.ast.statements.MessageSend;
import krakatoa.ast.types.Type;


public class MessageSendToSelf extends MessageSend {
    
    public Type getType() { 
        return null;
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
    }
    
    
}