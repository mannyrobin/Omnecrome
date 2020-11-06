package ru.armd.pbk.aspose.nsi;

import ru.armd.pbk.aspose.core.IReportDataBean;
import ru.armd.pbk.core.views.IReportView;

import java.util.List;

/**
 * Данные для формирования отчетов НСИ.
 */
public class NsiReportBeanData
	implements IReportDataBean {

   private List<IReportView> data;
   private String caption;

   /**
	* Конструтор данных для формирования отчетов НСИ.
	*
	* @param data    - данные.
	* @param caption - заголовок.
	*/
   public NsiReportBeanData(List<IReportView> data, String caption) {
	  this.data = data;
	  this.caption = caption;
   }

   public List<IReportView> getData() {
	  return data;
   }

   public void setData(List<IReportView> data) {
	  this.data = data;
   }

   public String getCaption() {
	  return caption;
   }

   public void setCaption(String caption) {
	  this.caption = caption;
   }

}
