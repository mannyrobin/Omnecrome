package ru.armd.pbk.aspose.core;

import ru.armd.pbk.aspose.aspose.MailMergeDataSet;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface IReportProcessor {
   IReportType getReportType();

   void setReportType(IReportType reportType);

   void setTemplateName(String fileName);

   String getTemplateName();

   IReportDataBean getDataBean();

   void setDataBean(IReportDataBean dataBean);

   /**
	* TODO Usage of Properties is incorrect for replace-based Processor - order of replaces might be significant!
	*/
   public void setTemplateData(Properties values);

   /**
	* Используется для возможности вставки таблиц значений
	*/
   public void setDataSet(MailMergeDataSet dataSet);

   public void setRootFolder(String folder)
	   throws Exception;

   public void preprocessTemplate()
	   throws Exception;

   /**
	* Генерирует документы на основании шаблона в заданных форматах. Замечание: документ .doc генерируется в любом случае в силу специфики реализации. Однако если список форматов не включает ReportFormat.MSWORD, он не попадет в результат.
	*
	* @param filename Имя файла сгенерированного документа.
	* @param formats  Форматы для генерации документов. По умолчанию ReportFormat.MSWORD
	* @return Соответствие между форматами документов и файлами, в которые эти документы сохранены.
	* @throws Exception
	*/
   public Map<ReportFormat, File> processTemplate(String filename, ReportFormat... formats)
	   throws Exception;

   File merge(List<File> reports, String mergedReportName, ReportFormat fromat);

   /**
	* Получить объект с данными для построения имени файла итогового отчёта.
	*
	* @return Объект с данными о сущностях для использования в имени отчёта.
	*/
   IReportNameData getResultReportNameData(List<ReportData> reportData);

   void deleteReportFiles();

   void cleanup();

   public Map<String, ICallback> getCallbacks();

   public void setCallbacks(Map<String, ICallback> callbacks);
}
