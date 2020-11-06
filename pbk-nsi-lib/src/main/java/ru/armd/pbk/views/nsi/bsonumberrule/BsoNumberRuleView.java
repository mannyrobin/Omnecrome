package ru.armd.pbk.views.nsi.bsonumberrule;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View для правил формирования номеров БСО.
 */
public class BsoNumberRuleView
	extends BaseGridView {

   private Long id;

   private String departmentName;

   private String bsoTypeName;

   private String increment;

   private String branchCode;

   private String deptCode;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getDepartmentName() {
	  return departmentName;
   }

   public void setDepartmentName(String departmentName) {
	  this.departmentName = departmentName;
   }

   public String getBsoTypeName() {
	  return bsoTypeName;
   }

   public void setBsoTypeName(String bsoTypeName) {
	  this.bsoTypeName = bsoTypeName;
   }

   public String getIncrement() {
	  return increment;
   }

   public void setIncrement(String increment) {
	  this.increment = increment;
   }

   public String getBranchCode() {
	  return branchCode;
   }

   public void setBranchCode(String branchCode) {
	  this.branchCode = branchCode;
   }

   public String getDeptCode() {
	  return deptCode;
   }

   public void setDeptCode(String deptCode) {
	  this.deptCode = deptCode;
   }
}
