package krakatoa.ast.statements;

import krakatoa.ast.PW;
import krakatoa.ast.statements.MessageSend;
import krakatoa.ast.statements.Statement;

public class MessageSendStatement extends Statement { 


   public void genC( PW pw ) {
      pw.printIdent("");
      // messageSend.genC(pw);
      pw.println(";");
   }

   private MessageSend  messageSend;

}


