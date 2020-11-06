package ru.armd.pbk.aspose.aspose;

import com.aspose.words.IMailMergeDataSource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Источник для работы с MailMerge.
 */
public class MailMergeDataSource<T>
	implements IMailMergeDataSource {
   private List<T> items;
   private int mRecordIndex;
   private String tableName;
   private Map<String, FieldFormatter> formatters = new HashMap<>();
   private EmptyRegionsHandlerType emptyRegionsHandlerType;

   public MailMergeDataSource(String tableName, List<T> items) {
	  this.tableName = tableName;
	  this.items = items;
	  mRecordIndex = -1;
   }

   public void setFieldFormatter(String fieldName, FieldFormatter formatter) {
	  if (fieldName == null || formatter == null) {
		 return;
	  }

	  formatters.put(fieldName, formatter);
   }

   @Override
   public String getTableName() {
	  return tableName;
   }

   @Override
   public boolean moveNext()
	   throws Exception {
	  if (!isEof()) {
		 mRecordIndex++;
	  }

	  return (!isEof());
   }

   private boolean isEof() {
	  return (mRecordIndex >= items.size());
   }

   @Override
   public boolean getValue(String fieldName, Object[] fieldValue)
	   throws Exception {
	  T item = items.get(mRecordIndex);

	  fieldValue[0] = null;
	  Class<?> itemClass = item.getClass();
	  Field field = null;
	  try {
		 field = itemClass.getDeclaredField(fieldName);
	  } catch (Exception ignore) {
	  }
	  if (field != null) {
		 field.setAccessible(true);
		 Object val = field.get(item);

		 FieldFormatter formatter = formatters.get(fieldName);
		 if (formatter != null) {
			try {
			   val = formatter.format(val);
			} catch (Exception e) {
			}
		 }

		 fieldValue[0] = val;
		 return true;
	  }
	  return false;
   }

   @Override
   public IMailMergeDataSource getChildDataSource(String s)
	   throws Exception {
	  return null;
   }

   /**
	* @return Тип обработчика таблицы, используемый в случае, если она будет пустой.
	*/
   public EmptyRegionsHandlerType getEmptyRegionsHandlerType() {
	  return emptyRegionsHandlerType;
   }

   /**
	* Тип обработчика таблицы, используемый в случае, если она будет пустой.
	* По умолчанию используется {@link EmptyRegionsHandlerType#REMOVE_TABLE}.
	*
	* @param emptyRegionsHandlerType
	*/
   public void setEmptyRegionsHandlerType(EmptyRegionsHandlerType emptyRegionsHandlerType) {
	  this.emptyRegionsHandlerType = emptyRegionsHandlerType;
   }
}
