package ru.armd.pbk.aspose.expression.jexl;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import ru.armd.pbk.aspose.expression.BooleanExpression;
import ru.armd.pbk.aspose.expression.ExpressionContext;

public class JexlBooleanExpression
	implements BooleanExpression {

   private final Expression jexlExpression;

   JexlBooleanExpression(Expression jexlExpression) {
	  this.jexlExpression = jexlExpression;
   }

   @Override
   public boolean evaluate(ExpressionContext context) {
	  if (!(context instanceof JexlExpressionContext)) {
		 throw new IllegalArgumentException("context is not instance of JexlExpressionContext");
	  }
	  JexlContext jexlContext = ((JexlExpressionContext) context).getJexlContext();
	  Object expressionResult = jexlExpression.evaluate(jexlContext);

	  return (expressionResult instanceof Boolean) ? (Boolean) expressionResult : false;
   }
}
