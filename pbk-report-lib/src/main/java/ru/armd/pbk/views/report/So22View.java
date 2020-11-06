package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Сверка с ГКУ ОП".
 */
public class So22View
	extends SoView {

   @JsonSerialize(using = SoDateSerializer.class)
   private Date workDateId;
   private String deptName;
   private String gkuopName;
   private String shiftName;
   private String mgtHead;
   private String gkuopHead;

   public String getDeptName() {
	  return deptName;
   }

   public void setDeptName(String deptName) {
	  this.deptName = deptName;
   }

   public Date getWorkDateId() {
	  return workDateId;
   }

   public void setWorkDateId(Date workDateId) {
	  this.workDateId = workDateId;
   }

   public String getGkuopName() {
	  return gkuopName;
   }

   public void setGkuopName(String gkuopName) {
	  this.gkuopName = gkuopName;
   }

   public String getShiftName() {
	  return shiftName;
   }

   public void setShiftName(String shiftName) {
	  this.shiftName = shiftName;
   }

   public String getMgtHead() {
	  return mgtHead;
   }

   public void setMgtHead(String mgtHead) {
	  this.mgtHead = mgtHead;
   }

   public String getGkuopHead() {
	  return gkuopHead;
   }

   public void setGkuopHead(String gkuopHead) {
	  this.gkuopHead = gkuopHead;
   }
}
