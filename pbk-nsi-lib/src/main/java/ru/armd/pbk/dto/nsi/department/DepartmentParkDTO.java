package ru.armd.pbk.dto.nsi.department;

import ru.armd.pbk.core.dto.BaseDTO;

/**
 * ДТО парка депортамента.
 */
public class DepartmentParkDTO
	extends BaseDTO {

   private Long departmentId;
   private Long parkId;

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public Long getParkId() {
	  return parkId;
   }

   public void setParkId(Long parkId) {
	  this.parkId = parkId;
   }

}
