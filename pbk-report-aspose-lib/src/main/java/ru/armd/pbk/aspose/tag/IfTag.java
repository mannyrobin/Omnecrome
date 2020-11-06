package ru.armd.pbk.aspose.tag;

import ru.armd.pbk.aspose.expression.BooleanExpression;
import ru.armd.pbk.aspose.expression.ExpressionContext;
import ru.armd.pbk.aspose.expression.ExpressionProvider;

public class IfTag {

   private final String bookmarkName;
   private final BooleanExpression expression;

   public IfTag(String bookmarkName, String expression) {
	  this.bookmarkName = bookmarkName;
	  this.expression = ExpressionProvider.createBooleanExpression(expression);
   }

   public String getBookmarkName() {
	  return bookmarkName;
   }

   public boolean evaluateExpression(ExpressionContext context) {
	  Object expressionResult = expression.evaluate(context);

	  return (expressionResult instanceof Boolean) ? (Boolean) expressionResult : false;
   }
}
