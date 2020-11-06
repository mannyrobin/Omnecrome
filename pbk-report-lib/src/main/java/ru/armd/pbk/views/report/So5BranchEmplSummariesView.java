package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * View для связки "филиал" - "сводные данные по контролёрам" (для использования в отчёте "Посменная
 * численность контролёров ГУП "Мосгортранс" и среднее значение численности за период").
 */
public class So5BranchEmplSummariesView
	extends SoView {

   private So5BranchView branch;
   private int planCount;
   private int factCount;
   private List<So5DaySummariesView> daysSummaries;
   private So5DaysTotalSummariesView daysTotalSummaries;
   private Double average;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date dateStart;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date dateEnd;

   /**
	* Конструктор по филиалу и его сводным данным по контролёрам по датам + даты начала и конца периода.
	*
	* @param branch             филиал
	* @param planCount          плановая численность контролёров
	* @param factCount          фактическая численность контролёров
	* @param daysSummaries      сводные данные по датам
	* @param daysTotalSummaries сводные данные итого
	* @param average            среднее значение
	* @param dateStart          дата начала периода
	* @param dateEnd            дата конца периода
	*/
   public So5BranchEmplSummariesView(So5BranchView branch,
									 int planCount,
									 int factCount,
									 List<So5DaySummariesView> daysSummaries,
									 So5DaysTotalSummariesView daysTotalSummaries,
									 Double average,
									 Date dateStart,
									 Date dateEnd) {
	  if (branch != null) {
		 this.setCnt(branch.getCnt());
		 this.setId(branch.getId());
	  }
	  this.branch = branch;
	  this.planCount = planCount;
	  this.factCount = factCount;
	  this.daysSummaries = daysSummaries;
	  this.daysTotalSummaries = daysTotalSummaries != null ? daysTotalSummaries : new So5DaysTotalSummariesView();
	  this.average = average;
	  this.dateStart = dateStart;
	  this.dateEnd = dateEnd;
   }

   public So5BranchView getBranch() {
	  return branch;
   }

   public void setBranch(So5BranchView branch) {
	  this.branch = branch;
   }

   public int getPlanCount() {
	  return planCount;
   }

   public int getFactCount() {
	  return factCount;
   }

   public List<So5DaySummariesView> getDaysSummaries() {
	  return daysSummaries;
   }

   public So5DaysTotalSummariesView getDaysTotalSummaries() {
	  return daysTotalSummaries;
   }

   public Double getAverage() {
	  return average;
   }

   public Date getDateStart() {
	  return dateStart;
   }

   public void setDateStart(Date dateStart) {
	  this.dateStart = dateStart;
   }

   public Date getDateEnd() {
	  return dateEnd;
   }

   public void setDateEnd(Date dateEnd) {
	  this.dateEnd = dateEnd;
   }
}
