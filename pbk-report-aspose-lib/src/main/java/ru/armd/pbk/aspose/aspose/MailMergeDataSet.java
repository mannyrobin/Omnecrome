package ru.armd.pbk.aspose.aspose;

import com.aspose.words.IMailMergeDataSource;
import com.aspose.words.IMailMergeDataSourceRoot;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class MailMergeDataSet
	implements IMailMergeDataSourceRoot {
   private HashMap<String, MailMergeDataSource<?>> dataSources = new HashMap<>();

   public MailMergeDataSet() {
   }

   public void addDataSource(String tableName, MailMergeDataSource<?> dataSource) {
	  dataSources.put(tableName, dataSource);
   }

   public boolean hasKey(String tableName) {
	  return dataSources.containsKey(tableName);
   }

   /**
	* @return Неизменяемое множество имён таблиц, содержащихся в DataSet.
	*/
   public Set<String> getTablesNames() {
	  if (dataSources == null) {
		 return null;
	  }

	  return Collections.unmodifiableSet(dataSources.keySet());
   }

   /**
	* @return Неизменяемую коллекцию таблиц из данного множества таблиц.
	*/
   public Collection<MailMergeDataSource<?>> getDataSources() {
	  if (dataSources == null) {
		 return null;
	  }

	  return Collections.unmodifiableCollection(dataSources.values());
   }

   @Override
   public IMailMergeDataSource getDataSource(String tableName)
	   throws Exception {
	  return dataSources.get(tableName);
   }
}
