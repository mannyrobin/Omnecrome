package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта
 * "Количество бригад по местам встречи".
 */
public class So3View
	extends SoView {

   @JsonSerialize(using = SoDateSerializer.class)
   private Date date;
   private Long deptId;
   private Long shiftId;
   private String county;
   private String district;
   private String venue;
   private String brigadeType;
   private int brigadeCount;
   private boolean isAgree;

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public boolean isAgree() {
	  return isAgree;
   }

   public void setAgree(boolean isAgree) {
	  this.isAgree = isAgree;
   }

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public String getCounty() {
	  return county;
   }

   public void setCounty(String county) {
	  this.county = county;
   }

   public String getDistrict() {
	  return district;
   }

   public void setDistrict(String district) {
	  this.district = district;
   }

   public String getVenue() {
	  return venue;
   }

   public void setVenue(String venue) {
	  this.venue = venue;
   }

   public String getBrigadeType() {
	  return brigadeType;
   }

   public void setBrigadeType(String brigadeType) {
	  this.brigadeType = brigadeType;
   }

   public int getBrigadeCount() {
	  return brigadeCount;
   }

   public void setBrigadeCount(int brigadeCount) {
	  this.brigadeCount = brigadeCount;
   }
}
