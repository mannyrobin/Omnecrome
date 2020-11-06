package ru.armd.pbk.aspose.expression;

import java.util.Properties;

public interface ExpressionFactory {

   BooleanExpression createBooleanExpression(String expression);

   ExpressionContext createExpressionContext(Properties properties);

}
