package ru.armd.pbk.views.nsi.dvr;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории видеорегистраторов.
 */
public class DvrHistoryView
	extends BaseVersionView {

   private String dvrNumber;

   private String dvrModel;
   private String description;
   private Long deptId;

   public String getDvrNumber() {
	  return dvrNumber;
   }

   public void setDvrNumber(String dvrNumber) {
	  this.dvrNumber = dvrNumber;
   }

   public String getDvrModel() {
	  return dvrModel;
   }

   public void setDvrModel(String dvrModel) {
	  this.dvrModel = dvrModel;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }
}
