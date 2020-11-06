package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен "Смены подразделения".
 * Данный домен служит для транспортировки информации
 * о резерве смены каждого подразделения по отдельности.
 */
public class ShiftDepartment
	extends BaseDomain {

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
