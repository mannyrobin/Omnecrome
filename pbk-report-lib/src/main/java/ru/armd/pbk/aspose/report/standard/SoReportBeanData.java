package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.aspose.core.IReportDataBean;
import ru.armd.pbk.views.report.SoView;

import java.util.List;

/**
 * Данные для размещения на печатной форме стандартного отчёта (тип отчёта и
 * данные для отчёта).
 */
public class SoReportBeanData
	implements IReportDataBean {

   private Integer soNumber;
   private List<SoView> soViews;
   private SoView total;

   /**
	* Конструктор по типу отчёта и данным для отчёта.
	*
	* @param soNumber тип отчёта (1-18)
	* @param soViews  данные для отчёта
	*/
   public SoReportBeanData(Integer soNumber, List<SoView> soViews) {
	  this.soNumber = soNumber;
	  this.soViews = soViews;
   }

   /**
	* Конструктор по типу отчета, данным для отчета и кол-ву записей.
	*
	* @param soNumber тип отчёта (1-18)
	* @param soViews  данные для отчёта
	* @param total    кол-во записей
	*/
   public SoReportBeanData(Integer soNumber, List<SoView> soViews, SoView total) {
	  this(soNumber, soViews);
	  this.total = total;
   }

   public Integer getSoNumber() {
	  return soNumber;
   }

   public void setSoNumber(Integer soNumber) {
	  this.soNumber = soNumber;
   }

   public List<SoView> getSoViews() {
	  return soViews;
   }

   public void setSoViews(List<SoView> soViews) {
	  this.soViews = soViews;
   }

   public SoView getTotal() {
	  return total;
   }

   public void setTotal(SoView total) {
	  this.total = total;
   }

}
