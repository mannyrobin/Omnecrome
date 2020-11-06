package ru.armd.pbk.dto.nsi;

import ru.armd.pbk.core.dto.BaseDTO;

/**
 * ДТО "Смены подразделения".
 */
public class ShiftDepartmentDTO
	extends BaseDTO {

   private Long shiftId;
   private Long departmentId;
   private Integer reserveCount;
   private Boolean isWorkDay;

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public Integer getReserveCount() {
	  return reserveCount;
   }

   public void setReserveCount(Integer reserveCount) {
	  this.reserveCount = reserveCount;
   }

   public Boolean getIsWorkDay() {
	  return isWorkDay;
   }

   public void setIsWorkDay(Boolean isWorkDay) {
	  this.isWorkDay = isWorkDay;
   }

}
