package ru.armd.pbk.domain;

import java.util.Date;

/**
 * Домен смены для стандартного отчета №2.
 */
public class So2ShiftHours {

   private Date date;
   private Long employeeId;
   private Long absenceShiftId;
   private Double shiftHours;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public Long getAbsenceShiftId() {
	  return absenceShiftId;
   }

   public void setAbsenceShiftId(Long absenceShiftId) {
	  this.absenceShiftId = absenceShiftId;
   }

   public Double getShiftHours() {
	  return shiftHours;
   }

   public void setShiftHours(Double shiftHours) {
	  this.shiftHours = shiftHours;
   }

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }
}
