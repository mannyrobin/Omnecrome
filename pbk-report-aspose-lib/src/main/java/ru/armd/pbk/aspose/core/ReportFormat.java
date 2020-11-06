package ru.armd.pbk.aspose.core;

/**
 * Формат выходного файла отчёта.
 */
public enum ReportFormat {

   /**
	* Документ Microsoft Word в формате .doc
	*/
   MSWORD("application/msword", "doc", "Microsoft Word (.doc)"),

   /**
	* Документ в формате PDF.
	*/
   PDF("application/pdf", "pdf", "PDF"),

   CSV("text/csv", "csv", "CSV"),

   /**
	* Документ в формате HTML.
	*/
   HTML("text/html", "html", "HTML"),

   /**
	* Документ в формате .xls.
	*/
   XLS("application/vnd.ms-excel", "xls", "Microsoft Excel (.xls)"),

   /**
	* Документ в формате .xlsx.
	*/
   XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx", "Microsoft Excel (.xlsx)");


   private String mimeType;
   private String fileExtension;
   private String description;

   private ReportFormat(String mimeType, String fileExtension, String description) {
	  this.mimeType = mimeType;
	  this.fileExtension = fileExtension;
	  this.description = description;
   }

   /**
	* @return MIME-тип, соответствующий формату.
	*/
   public String getMimeType() {
	  return mimeType;
   }

   /**
	* @param dotted Если <code>true</code>, то добавляет точку перед расширением.
	* @return Расширение файла, используемое Aspose при сохранении документа.
	*/
   public String getFileExtension(boolean dotted) {
	  return dotted ? "." + fileExtension : fileExtension;
   }

   /**
	* @return Пользовательское описание формата.
	*/
   public String getDescription() {
	  return description;
   }
}
