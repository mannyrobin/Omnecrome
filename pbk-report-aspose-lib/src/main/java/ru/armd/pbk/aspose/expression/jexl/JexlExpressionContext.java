package ru.armd.pbk.aspose.expression.jexl;

import org.apache.commons.jexl2.JexlContext;
import ru.armd.pbk.aspose.expression.ExpressionContext;

public class JexlExpressionContext
	implements ExpressionContext {

   private final JexlContext jexlContext;

   JexlExpressionContext(JexlContext jexlContext) {
	  this.jexlContext = jexlContext;
   }

   JexlContext getJexlContext() {
	  return jexlContext;
   }
}
