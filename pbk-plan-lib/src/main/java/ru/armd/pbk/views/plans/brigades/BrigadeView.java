package ru.armd.pbk.views.plans.brigades;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.util.Date;

/**
 * View бригады плана отдела.
 */
public class BrigadeView {

   private Long id;
   private Integer mgtCount;
   private Integer gkuopCount;
   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date planDate;
   private boolean isAgree;
   private boolean isNotFull;
   private boolean isEnough;
   private boolean haveFreeControlers;
   private boolean manual;

   public Date getPlanDate() {
	  return planDate;
   }

   public void setPlanDate(Date planDate) {
	  this.planDate = planDate;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Integer getMgtCount() {
	  return mgtCount;
   }

   public void setMgtCount(Integer mgtCount) {
	  this.mgtCount = mgtCount;
   }

   public Integer getGkuopCount() {
	  return gkuopCount;
   }

   public void setGkuopCount(Integer gkuopCount) {
	  this.gkuopCount = gkuopCount;
   }

   public boolean getIsAgree() {
	  return isAgree;
   }

   public void setIsAgree(boolean isAgree) {
	  this.isAgree = isAgree;
   }

   public boolean isNotFull() {
	  return isNotFull;
   }

   public void setNotFull(boolean isNotFull) {
	  this.isNotFull = isNotFull;
   }

   public boolean isEnough() {
	  return isEnough;
   }

   public void setEnough(boolean isEnough) {
	  this.isEnough = isEnough;
   }

   public boolean isHaveFreeControlers() {
	  return haveFreeControlers;
   }

   public void setHaveFreeControlers(boolean haveFreeControlers) {
	  this.haveFreeControlers = haveFreeControlers;
   }

   public boolean isManual() {
	  return manual;
   }

   public void setManual(boolean manual) {
	  this.manual = manual;
   }
}