package ru.armd.pbk.views.nsi.employee;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * View для отображения режима работы сотрудника.
 */
public class EmployeeCalendarView {

   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date startDate;

   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date endDate;
   private Integer shiftType;
   private BigDecimal workPlanHours;
   private BigDecimal workFactHours;

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

   public Integer getShiftType() {
	  return shiftType;
   }

   public void setShiftType(Integer shiftType) {
	  this.shiftType = shiftType;
   }

   public BigDecimal getWorkPlanHours() {
	  return workPlanHours;
   }

   public void setWorkPlanHours(BigDecimal workPlanHours) {
	  this.workPlanHours = workPlanHours;
   }

   public BigDecimal getWorkFactHours() {
	  return workFactHours;
   }

   public void setWorkFactHours(BigDecimal workFactHours) {
	  this.workFactHours = workFactHours;
   }

}
