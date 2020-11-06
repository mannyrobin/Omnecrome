package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.BaseGridView;

public class ShiftDepartmentView
	extends BaseGridView {

   private String departmentName;
   private Long id;
   private Integer reserveCount;
   private String day;

   public String getDepartmentName() {
	  return departmentName;
   }

   public void setDepartmentName(String departmentName) {
	  this.departmentName = departmentName;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Integer getReserveCount() {
	  return reserveCount;
   }

   public void setReserveCount(Integer reserveCount) {
	  this.reserveCount = reserveCount;
   }

   public String getDay() {
	  return day;
   }

   public void setDay(String day) {
	  this.day = day;
   }

}
