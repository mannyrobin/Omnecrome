package ru.armd.pbk.aspose.plan;

import ru.armd.pbk.aspose.core.IReportDataBean;
import ru.armd.pbk.views.plan.BrigadeGraphPlanVenuesView;
import ru.armd.pbk.views.plan.BrigadeGraphVenuesView;

import java.util.Date;
import java.util.List;

/**
 * Данные для размещения на форме графика бригад.
 */
public class BrigadeGraphReportBeanData
	implements IReportDataBean {
   private Date fromDate;
   private Date toDate;
   private List<BrigadeGraphVenuesView> venues;
   private List<BrigadeGraphPlanVenuesView> planVenues;

   /**
	* Конструктор по данным для графика бригад.
	*
	* @param fromDate   дата начала
	* @param toDate     дата окончания
	* @param venues     список мест встречи
	* @param planVenues список бригад
	*/
   public BrigadeGraphReportBeanData(Date fromDate, Date toDate, List<BrigadeGraphVenuesView> venues,
									 List<BrigadeGraphPlanVenuesView> planVenues) {
	  this.fromDate = fromDate;
	  this.toDate = toDate;
	  this.venues = venues;
	  this.planVenues = planVenues;
   }

   public List<BrigadeGraphVenuesView> getVenues() {
	  return venues;
   }

   public List<BrigadeGraphPlanVenuesView> getPlanVenues() {
	  return planVenues;
   }

   public Date getFromDate() {
	  return fromDate;
   }

   public Date getToDate() {
	  return toDate;
   }
}