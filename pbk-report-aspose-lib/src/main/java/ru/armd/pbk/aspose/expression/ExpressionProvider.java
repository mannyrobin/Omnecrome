package ru.armd.pbk.aspose.expression;

import ru.armd.pbk.aspose.expression.jexl.JexlExpressionFactory;

import java.util.Properties;

public class ExpressionProvider {

   private static ExpressionFactory expressionFactory = new JexlExpressionFactory();

   public static BooleanExpression createBooleanExpression(String expression) {
	  return expressionFactory.createBooleanExpression(expression);
   }

   public static ExpressionContext createExpressionContext(Properties properties) {
	  return expressionFactory.createExpressionContext(properties);
   }

   public static void setExpressionFactory(ExpressionFactory factory) {
	  expressionFactory = factory;
   }
}
