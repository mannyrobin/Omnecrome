package ru.armd.pbk.views.plan;

import java.util.Date;

/**
 * Список бригад.
 */
public class BrigadeGraphPlanVenuesView {
   private Long id;
   private Long deptId;
   private Long cityVenueId;
   private Date planDate;
   private Integer gkuopCount;
   private Integer mgtCount;
   private Long shiftId;
   private boolean isAgree;
   private boolean isNotFull;
   private boolean isHaveFreeControllers;

   public Long getCityVenueId() {
	  return cityVenueId;
   }

   public void setCityVenueId(Long cityVenueId) {
	  this.cityVenueId = cityVenueId;
   }

   public Date getPlanDate() {
	  return planDate;
   }

   public void setPlanDate(Date planDate) {
	  this.planDate = planDate;
   }

   public Integer getGkuopCount() {
	  return gkuopCount;
   }

   public void setGkuopCount(Integer gkuopCount) {
	  this.gkuopCount = gkuopCount;
   }

   public Integer getMgtCount() {
	  return mgtCount;
   }

   public void setMgtCount(Integer mgtCount) {
	  this.mgtCount = mgtCount;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public boolean getIsAgree() {
	  return isAgree;
   }

   public void setIsAgree(boolean isAgree) {
	  this.isAgree = isAgree;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public boolean getIsNotFull() {
	  return isNotFull;
   }

   public void setIsNotFull(boolean isNotFull) {
	  this.isNotFull = isNotFull;
   }

   public boolean getIsHaveFreeControllers() {
	  return isHaveFreeControllers;
   }

   public void setIsHaveFreeControllers(boolean isHaveFreeControllers) {
	  this.isHaveFreeControllers = isHaveFreeControllers;
   }

   /**
	* Копировать бригаду.
	*
	* @return
	*/
   public BrigadeGraphPlanVenuesView cloneBrigade() {
	  BrigadeGraphPlanVenuesView brg = new BrigadeGraphPlanVenuesView();
	  brg.id = this.id;
	  brg.deptId = this.deptId;
	  brg.cityVenueId = this.cityVenueId;
	  brg.planDate = this.planDate;
	  brg.gkuopCount = this.gkuopCount;
	  brg.mgtCount = this.mgtCount;
	  brg.shiftId = this.shiftId;
	  brg.isAgree = this.isAgree;
	  brg.isNotFull = this.isNotFull;
	  brg.isHaveFreeControllers = this.isHaveFreeControllers;
	  return brg;
   }
}