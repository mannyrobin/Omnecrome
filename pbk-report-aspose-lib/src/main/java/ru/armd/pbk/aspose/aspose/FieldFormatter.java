package ru.armd.pbk.aspose.aspose;

/**
 * Преобразователь для подстановки значений поля MailMergeDataSource в документ.
 */
public abstract class FieldFormatter {

   public Object format(Object value)
	   throws Exception {
	  try {
		 return doFormat(value);
	  } catch (Throwable e) {
		 throw new Exception("Возникло исключение при преобразовании значения поля MailMergeDataSource");
	  }
   }

   protected abstract Object doFormat(Object value);
}
