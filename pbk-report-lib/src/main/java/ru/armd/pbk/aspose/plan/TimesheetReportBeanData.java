package ru.armd.pbk.aspose.plan;

import ru.armd.pbk.aspose.core.IReportDataBean;
import ru.armd.pbk.views.plan.TimesheetReportView;

import java.util.Date;
import java.util.List;

/**
 * Данные для размещения на форме табеля.
 */
public class TimesheetReportBeanData
	implements IReportDataBean {

   private Date dateFrom;
   private Date dateTo;
   private List<TimesheetReportView> views;
   private String departmentName;

   public TimesheetReportBeanData(Date dateFrom, Date dateTo, String departmentName, List<TimesheetReportView> views) {
	  this.dateFrom = dateFrom;
	  this.dateTo = dateTo;
	  this.views = views;
	  this.departmentName = departmentName;
   }

   public Date getDateFrom() {
	  return dateFrom;
   }

   public Date getDateTo() {
	  return dateTo;
   }

   public List<TimesheetReportView> getViews() {
	  return views;
   }

   public String getDepartmentName() {
	  return departmentName;
   }

}
