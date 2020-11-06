package ru.armd.pbk.views.nsi.department;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории подразделения.
 */
public class DepartmentHistoryView
	extends BaseVersionView {

   private String name;

   private String fullName;
   private String description;
   private Boolean forPlanUse;
   
   private String planCount;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getPlanCount() {
		  return planCount;
   }

   public void setPlanCount(String planCount) {
		  this.planCount = planCount;
   }
	   
   public String getFullName() {
	  return fullName;
   }

   public void setFullName(String fullName) {
	  this.fullName = fullName;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Boolean getForPlanUse() {
	  return forPlanUse;
   }

   public void setForPlanUse(Boolean forPlanUse) {
	  this.forPlanUse = forPlanUse;
   }
}
