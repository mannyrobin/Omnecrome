package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * View для связки "филиал" - "сводные данные по контролёрам" (для использования в отчёте "Сводные данные по работе контролеров за период").
 */
public class So23EmplSummariesView
	extends SoView {

   private So23EmployeeView employee;
   private So23EmployeeView personalNumber;
   private List<So23DaySummariesView> daysSummaries;
   private So23DaysTotalSummariesView daysTotalSummaries;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date dateStart;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date dateEnd;
 
   /**
	* Конструктор по филиалу и его сводным данным по контролёрам по датам + даты начала и конца периода.
	*
	* @param employee           сотрудник
	* @param personalNumber     табельный номер
	* @param daysSummaries      сводные данные по датам
	* @param daysTotalSummaries сводные данные итого
	* @param dateStart          дата начала периода
	* @param dateEnd            дата конца периода
	* @param emplSumByShifts	смена
	*/
   public So23EmplSummariesView(So23EmployeeView employee,
		                        So23EmployeeView personalNumber,
									 List<So23DaySummariesView> daysSummaries,
									 So23DaysTotalSummariesView daysTotalSummaries,
									 Date dateStart,
									 Date dateEnd) {
	  if (employee != null) {
		 this.setCnt(employee.getCnt());
		 this.setId(employee.getId());
	  }
	  this.employee = employee;
	  this.personalNumber = personalNumber;
	  this.daysSummaries = daysSummaries;
	  this.daysTotalSummaries = daysTotalSummaries != null ? daysTotalSummaries : new So23DaysTotalSummariesView();
	  this.dateStart = dateStart;
	  this.dateEnd = dateEnd;
   }

   public So23EmployeeView getEmployee() {
	  return employee;
   }

   public void setEmployee(So23EmployeeView employee) {
	  this.employee = employee;
   }

   public So23EmployeeView getPersonalNumber() {
	  return personalNumber;
   }

  
   public List<So23DaySummariesView> getDaysSummaries() {
	  return daysSummaries;
   }
   
    public So23DaysTotalSummariesView getDaysTotalSummaries() {
	  return daysTotalSummaries;
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
