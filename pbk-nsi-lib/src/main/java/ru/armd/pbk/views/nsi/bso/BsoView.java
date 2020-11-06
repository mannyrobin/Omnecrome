package ru.armd.pbk.views.nsi.bso;

import ru.armd.pbk.core.views.BaseGridView;

import static ru.armd.pbk.utils.StringUtils.binaryToMarkSymbol;

/**
 * View для БСО.
 */
public class BsoView
	extends BaseGridView {

   private Long id;

   private Long departmentId;

   private String departmentName;

   private String bsoTypeName;

   private String bsoNumber;

   private String employeeName;

   private String isPrinted;

   private String isTrashed;

   private String isBound;

   private String isUsed;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
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

   public String getBsoNumber() {
	  return bsoNumber;
   }

   public void setBsoNumber(String bsoNumber) {
	  this.bsoNumber = bsoNumber;
   }

   public String getEmployeeName() {
	  return employeeName;
   }

   public void setEmployeeName(String employeeName) {
	  this.employeeName = employeeName;
   }

   public String getIsPrinted() {
	  return isPrinted;
   }

   public void setIsPrinted(String isPrinted) {
	  this.isPrinted = binaryToMarkSymbol(isPrinted);
   }

   public String getIsTrashed() {
	  return isTrashed;
   }

   public void setIsTrashed(String isTrashed) {
	  this.isTrashed = binaryToMarkSymbol(isTrashed);
   }

   public String getIsBound() {
	  return isBound;
   }

   public void setIsBound(String isBound) {
	  this.isBound = binaryToMarkSymbol(isBound);
   }

   public String getIsUsed() {
	  return isUsed;
   }

   public void setIsUsed(String isUsed) {
	  this.isUsed = binaryToMarkSymbol(isUsed);
   }
}
