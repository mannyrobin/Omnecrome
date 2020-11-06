package ru.armd.pbk.aspose.plan;

import ru.armd.pbk.aspose.core.IReportDataBean;
import ru.armd.pbk.views.plans.routes.PlanRouteView;

import java.util.Date;
import java.util.List;

/**
 * Created by Yakov Volkov.
 */
public class RouteRateReportBeanData
	implements IReportDataBean {

   List<PlanRouteView> planRouteViews;

   private Date startDate;
   private Date endDate;

   public RouteRateReportBeanData(List<PlanRouteView> planRouteViews, Date startDate, Date endDate) {
	  this.planRouteViews = planRouteViews;
	  this.startDate = startDate;
	  this.endDate = endDate;
   }

   public List<PlanRouteView> getPlanRouteViews() {
	  return planRouteViews;
   }

   public void setPlanRouteViews(List<PlanRouteView> planRouteViews) {
	  this.planRouteViews = planRouteViews;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }
}
