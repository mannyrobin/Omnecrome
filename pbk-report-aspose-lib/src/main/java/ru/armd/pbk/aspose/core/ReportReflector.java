package ru.armd.pbk.aspose.core;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Рефлектор для отображения объектов в хеш-таблицу.
 * Используется при генерации отчётов из шаблонов.
 */
public class ReportReflector {

   private boolean ignoreNull;

   /**
	* @param ignoreNull Если <code>true</code>, то <code>null</code>-значения игнорируются.
	*                   Иначе значение по соответствующему ключу заменяется пустой строкой.
	*                   По умолчанию <code>false</code>.
	*/
   public void setIgnoreNull(boolean ignoreNull) {
	  this.ignoreNull = ignoreNull;
   }

   /**
	* @return Если <code>true</code>, то <code>null</code>-значения игнорируются.
	* Иначе значение по соответствующему ключу заменяется пустой строкой.
	*/
   public boolean isIgnoreNull() {
	  return ignoreNull;
   }

   /**
	* Отображает объект в хеш-таблицу {@link Properties}.
	* Для получения свойств объекта используются public getter-методы, то есть методы,
	* начинающиеся со слова get и не имеющие параметров.<br><br>
	* Полученное getter-методом значение помещается в таблицу по ключу, образованному
	* путём отбрасывания от названия метода слова get и приведения первого символа
	* полученного слова к нижнему регистру. Например, методу getUserName() соответствует
	* ключ userName.
	*
	* @param object Объект для отображения.
	* @return Соответствующая объекту хеш-таблца {@link Properties}.
	*/
   public Properties reflectObject(Object object) {
	  if (object == null) {
		 return null;
	  }

	  Method[] methods = object.getClass().getDeclaredMethods();
	  if (methods == null) {
		 return null;
	  }

	  Properties properties = new Properties();
	  for (Method method : methods) {
		 String methodName = method.getName();
		 if (!methodName.startsWith("get")) {
			continue;
		 }

		 // Пропускаем методы с непустым списком параметров.
		 if (method.getParameterTypes() != null && method.getParameterTypes().length > 0) {
			continue;
		 }

		 String key = methodName.substring(3);
		 if (key.isEmpty()) {
			continue;
		 }

		 key = key.substring(0, 1).toLowerCase() + key.substring(1);

		 Object returnedValue;
		 try {
			returnedValue = method.invoke(object);
		 } catch (Throwable e) {
			continue;
		 }

		 if (returnedValue == null && ignoreNull) {
			continue;
		 }

		 String value = returnedValue == null ? "" : returnedValue.toString();
		 properties.put(key, value);
	  }

	  return properties;
   }
}
