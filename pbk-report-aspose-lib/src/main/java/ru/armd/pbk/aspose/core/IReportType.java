package ru.armd.pbk.aspose.core;

/**
 * Тип отчета.
 */
public interface IReportType {

   AbstractReportProcessor instantiateProcessor();

   /**
	* @return Описание отчёта.
	*/
   String getDescription();

   /**
	* @return Имя шаблона отчёта.
	*/
   String getTemplateName();

   /**
	* @return формат, в который выгружается отчет.
	*/
   ReportFormat getReportFormat();

   /**
	* @return формат, в который выгружается отчет.
	*/
   void setReportFormat(ReportFormat format);

   String getFileName(Long id);

   String getFileName(Long id, ReportFormat format);

   String getFileName(String suffix);

   String getZipName();

   ReportFormat getDefaultReportFormat();

   String getFileNamePattern();

   String getName();

   IReportType getReportTypeByName(String name);

   Boolean getSkipTemplate();
}
