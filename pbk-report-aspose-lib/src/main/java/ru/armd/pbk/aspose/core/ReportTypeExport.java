package ru.armd.pbk.aspose.core;

/**
 * Информация для выгрузки отчета
 */
public class ReportTypeExport {

   public static class Format {
	  String id;
	  String name;

	  public Format(String id, String name) {
		 this.id = id;
		 this.name = name;
	  }

	  public String getId() {
		 return id;
	  }

	  public void setId(String id) {
		 this.id = id;
	  }

	  public String getName() {
		 return name;
	  }

	  public void setName(String name) {
		 this.name = name;
	  }
   }
}
