package ru.armd.pbk.aspose.expression.jexl;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import ru.armd.pbk.aspose.expression.BooleanExpression;
import ru.armd.pbk.aspose.expression.ExpressionContext;
import ru.armd.pbk.aspose.expression.ExpressionFactory;

import java.util.Properties;

public class JexlExpressionFactory
	implements ExpressionFactory {

   private static final boolean JEXL_ENGINE_SILENT = false;
   private static final boolean JEXL_ENGINE_STRICT = true;

   private JexlEngine jexlEngine = null;

   @Override
   public BooleanExpression createBooleanExpression(String expression) {
	  Expression jexlExpression = getJexlEngine().createExpression(expression);
	  return new JexlBooleanExpression(jexlExpression);
   }

   @Override
   public ExpressionContext createExpressionContext(Properties properties) {
	  JexlContext jexlContext = new MapContext();
	  if (properties != null) {
		 for (String key : properties.stringPropertyNames()) {
			if (!key.contains(" ")) {
			   jexlContext.set(key, properties.get(key));
			}
		 }
	  }
	  return new JexlExpressionContext(jexlContext);
   }

   private JexlEngine getJexlEngine() {
	  if (jexlEngine == null) {
		 jexlEngine = new JexlEngine();
		 jexlEngine.setSilent(JEXL_ENGINE_SILENT);
		 jexlEngine.setStrict(JEXL_ENGINE_STRICT);
	  }
	  return jexlEngine;
   }
}
