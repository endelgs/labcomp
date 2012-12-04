package krakatoa.ast.statements;

import krakatoa.ast.expressions.Expression;
import java.util.ArrayList;
import krakatoa.ast.expressions.Expr;
import krakatoa.ast.Id;
import krakatoa.ast.ReceiverMessage;


abstract class MessageSend  extends Expr  {
  private ReceiverMessage receiverMessage;
  private Id id;
  private ArrayList<Expression> exprList;
}

